package org.aarondeveloper.dealerpos.presentation.screens.introduccion

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.presentation.component.Cargando
import org.aarondeveloper.dealerpos.presentation.component.ModalBodyV1Screen
import org.aarondeveloper.dealerpos.ui.theme.ButtonBackground
import org.aarondeveloper.dealerpos.ui.theme.PrimaryText
import org.aarondeveloper.dealerpos.ui.theme.SecondaryText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun IntroScreen(
    viewModel: IntroViewModel = koinViewModel(),
    onAutenticado: () -> Unit,
    onIniciarSesionClick: () -> Unit,
    onRegistrateClick: () -> Unit
) {
    LaunchedEffect(Unit){
        viewModel.verificarAuteticacion(onAutenticado)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    IntroBodyScreen(
        uiState = uiState,
        onIniciarSesionClick,
        onRegistrateClick,
        onCerrarModalClick = viewModel::onCerrarModalClick
    )
}

@Composable
fun IntroBodyScreen(
    uiState: UiState,
    onIniciarSesionClick: () -> Unit,
    onRegistrateClick: () -> Unit,
    onCerrarModalClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.background),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 80.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            TextWithSmoothAnimation(uiState.language!!.bienvenidos_a)
            TextWithSmoothAnimation(uiState.language.nombreapp)

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = uiState.language.descripcion,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                color = SecondaryText,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )


            Spacer(modifier = Modifier.height(65.dp))

            Image(
                painter = painterResource(resource = Res.drawable.intro_car),
                contentDescription = "Car Image",
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1.5f)
                    .align(Alignment.End)
                    .offset(x = 25.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onIniciarSesionClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(2.dp, ButtonBackground, RoundedCornerShape(50.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        text = uiState.language.iniciar_sesion,
                        color = ButtonBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Text(
                    text = uiState.language.registrate,
                    color = PrimaryText,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onRegistrateClick()
                        }
                )
            }
        }

        if (uiState.cargando) {
            Cargando()
        }

        if (uiState.modal) {
            when (uiState.tipoError) {
                "T1" -> {
                    ModalBodyV1Screen(
                        Res.drawable.conexion_error,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.direct_right,
                        uiState.language!!.confirmado,
                        onButtonClick = onCerrarModalClick
                    )
                }
            }
        }
    }
}



@Composable
fun TextWithSmoothAnimation(text: String) {
    val infiniteTransition = rememberInfiniteTransition()

    val opacityValues = text.mapIndexed { index, _ ->
        infiniteTransition.animateFloat(
            initialValue = 0.5f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1000,
                    delayMillis = index * 300,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        ).value
    }

    val offsetValues = text.mapIndexed { index, _ ->
        infiniteTransition.animateFloat(
            initialValue = 10f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1000,
                    delayMillis = index * 300,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        ).value
    }

    Row(
        modifier = Modifier
            .padding(start = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        text.forEachIndexed { index, char ->
            Text(
                text = char.toString(),
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = PrimaryText.copy(alpha = opacityValues[index]),
                modifier = Modifier
                    .offset(y = offsetValues[index].dp)
                    .alpha(opacityValues[index])
            )
        }
    }
}



@Preview
@Composable
fun IntroScreenPreview() {

}

package org.aarondeveloper.dealerpos.presentation.screens.informacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.librery.getAppVersion
import org.aarondeveloper.dealerpos.presentation.component.Cargando
import org.aarondeveloper.dealerpos.presentation.component.ModalBodyV1Screen
import org.aarondeveloper.dealerpos.ui.theme.AaronDeveloperColor
import org.aarondeveloper.dealerpos.ui.theme.SecondaryText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun InformacionScreen(
    viewModel: InformacionViewModel = koinViewModel(),
    onNavBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    InformacionBodyScreen(
        uiState = uiState,
        onNavBackClick = onNavBackClick,
        onCerrarModalClick = viewModel::onCerrarModalClick
    )

}


@Composable
fun InformacionBodyScreen(
    uiState: UiState,
    onNavBackClick: () -> Unit,
    onCerrarModalClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.background_principal_solido),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, top = 40.dp, end = 16.dp)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.language!!.informacion,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Image(
                    painter = painterResource(resource = Res.drawable.back),
                    contentDescription = "Back Icon",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable { onNavBackClick() }
                        .padding(8.dp)
                        .width(48.dp)
                        .height(48.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp)
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
        ) {
            Image(
                painter = painterResource(resource = Res.drawable.background_trasversal),
                contentDescription = "Background Transversal",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(resource = Res.drawable.dilerpos),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 20.dp)
                )

                Text(
                    text = uiState.language!!.nombreapp,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondaryText,
                    modifier = Modifier.padding(bottom = 20.dp)
                )


                Text(
                    text = uiState.language.descripcion,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = SecondaryText,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                )

                Text(
                    text = uiState.language.version,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = getAppVersion(),
                    fontSize = 16.sp,
                    color = SecondaryText,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                Text(
                    text = uiState.language.redes_sociales,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondaryText,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(resource = Res.drawable.facebook), contentDescription = "Facebook", modifier = Modifier.size(40.dp))
                    Image(painter = painterResource(resource = Res.drawable.whatsapp), contentDescription = "WhatsApp", modifier = Modifier.size(40.dp))
                    Image(painter = painterResource(resource = Res.drawable.instagram), contentDescription = "Instagram", modifier = Modifier.size(40.dp))
                    Image(painter = painterResource(resource = Res.drawable.twitter), contentDescription = "Twitter", modifier = Modifier.size(40.dp))
                    Image(painter = painterResource(resource = Res.drawable.github), contentDescription = "GitHub", modifier = Modifier.size(40.dp))
                }

                Text(
                    text = buildAnnotatedString {
                        append(uiState.language.desarollado_por + " ❤ ")
                        withStyle(style = SpanStyle(color = AaronDeveloperColor)) {
                            append("AaronDeveloper")
                        }
                    },
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = SecondaryText,
                    modifier = Modifier.padding(top = 16.dp)
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


@Preview
@Composable
fun InformacionScreenPreview() {

}

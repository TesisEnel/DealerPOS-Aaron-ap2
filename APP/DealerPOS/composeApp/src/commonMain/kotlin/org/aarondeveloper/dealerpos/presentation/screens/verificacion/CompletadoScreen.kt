package org.aarondeveloper.dealerpos.presentation.screens.verificacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.ui.theme.ButtonBackground
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun CompletadoScreen(
    viewModel: VerificacionesViewModel = koinViewModel(),
    onFinalizarClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CompletadoBodyScreen(
        uiState = uiState,
        onFinalizarClick
    )

}

@Composable
fun CompletadoBodyScreen(
    uiState: UiState,
    onFinalizarClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.background),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(resource = Res.drawable.verificate),
                contentDescription = "Validation Icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(top = 20.dp, bottom = 16.dp)
            )

            Text(
                text = uiState.language!!.cuenta_verificada,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onFinalizarClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(2.dp, ButtonBackground, RoundedCornerShape(50.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = uiState.language.finalizar + " (3/3)",
                    color = ButtonBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Preview
@Composable
fun CompletadoScreenPreview() {
    CompletadoScreen(
        onFinalizarClick = {}
    )
}

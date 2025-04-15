package org.aarondeveloper.dealerpos.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.presentation.component.Cargando
import org.aarondeveloper.dealerpos.presentation.component.Input
import org.aarondeveloper.dealerpos.presentation.component.ModalBodyV1Screen
import org.aarondeveloper.dealerpos.presentation.component.ModalBodyV2Screen
import org.aarondeveloper.dealerpos.ui.theme.ButtonBackground
import org.aarondeveloper.dealerpos.ui.theme.MarronEnd
import org.aarondeveloper.dealerpos.ui.theme.MoradoStart
import org.aarondeveloper.dealerpos.ui.theme.PrimaryText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onAutenticado: () -> Unit,
    onVerificarse: () -> Unit,
    onNavOlvidasteContrasenaClick: () -> Unit
) {

    LaunchedEffect(Unit){
        viewModel.verificarAuteticacion(onAutenticado)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LoginBodyScreen(
        uiState = uiState,
        onAutenticado = onAutenticado,
        onVerificarse = onVerificarse,
        onNavOlvidasteContrasenaClick = onNavOlvidasteContrasenaClick,
        onEmailChange = viewModel::onEmailChange,
        onContrasenaChange = viewModel::onContrasenaChange,
        onOlvideContrasenaClick = viewModel::onOlvideContrasenaClick,
        onCerrarModalClick = viewModel::onCerrarModalClick,
        onIniciarSesionClick = viewModel::onIniciarSesionClick,
        onVerificarCuentaClick = viewModel::onVerificarCuentaClick
    )
}

@Composable
fun LoginBodyScreen(
    uiState: UiState,
    onAutenticado: () -> Unit,
    onVerificarse: () -> Unit,
    onNavOlvidasteContrasenaClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onContrasenaChange: (String) -> Unit,
    onOlvideContrasenaClick: (() -> Unit) -> Unit,
    onCerrarModalClick: () -> Unit,
    onIniciarSesionClick: (() -> Unit) -> Unit,
    onVerificarCuentaClick: (() -> Unit) -> Unit
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
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(resource = Res.drawable.dilerpos),
                contentDescription = "DilerPOS Logo",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = uiState.language!!.iniciar_sesion,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = uiState.language.descripcion_inicio_sesion,
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp)
            )

            Input(
                value = uiState.email ?: "",
                onValueChange = { email -> onEmailChange(email) },
                label = uiState.language.correo_electronico,
                borderRadius = 10,
                borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                borderSize = 2,
                textSize = 16,
                verticalPadding = 22,
                type = "Text",
                isPassword = false,
                isDisabled = false
            )

            Input(
                value = uiState.contrasena ?: "",
                onValueChange = { contrasena -> onContrasenaChange(contrasena) },
                label = uiState.language.contrasena,
                borderRadius = 10,
                borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                borderSize = 2,
                textSize = 16,
                verticalPadding = 22,
                type = "Text",
                isPassword = true,
                isDisabled = false
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column (
                modifier = Modifier
                    .padding(start = 40.dp, end = 40.dp)
            ) {
                Button(
                    onClick = { onIniciarSesionClick(onAutenticado) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(2.dp, ButtonBackground, RoundedCornerShape(50.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        text = uiState.language.acceder,
                        color = ButtonBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = uiState.language.olvido_contrasena,
                color = PrimaryText,
                fontSize = 14.sp,
                modifier = Modifier.clickable(onClick = { onOlvideContrasenaClick(onNavOlvidasteContrasenaClick) })
            )
        }

        if (uiState.cargando) {
            Cargando()
        }

        if (uiState.modal) {
            when (uiState.tipoError) {
                "T1" -> {
                    ModalBodyV1Screen(
                        Res.drawable.campos,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.direct_right,
                        uiState.language!!.confirmado,
                        onButtonClick = onCerrarModalClick
                    )
                }

                "T2" -> {
                    ModalBodyV1Screen(
                        Res.drawable.conexion_error,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.direct_right,
                        uiState.language!!.confirmado,
                        onButtonClick = onCerrarModalClick
                    )
                }

                "T3" -> {
                    ModalBodyV1Screen(
                        Res.drawable.noidentificado,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.direct_right,
                        uiState.language!!.confirmado,
                        onButtonClick = onCerrarModalClick
                    )
                }

                "T4" -> {
                    ModalBodyV2Screen(
                        Res.drawable.email,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.close,
                        uiState.language!!.respuesta_no,
                        onButton1Click = onCerrarModalClick,
                        Res.drawable.check,
                        uiState.language.respuesta_si,
                        onButton2Click = { onVerificarCuentaClick(onVerificarse) },
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {

}

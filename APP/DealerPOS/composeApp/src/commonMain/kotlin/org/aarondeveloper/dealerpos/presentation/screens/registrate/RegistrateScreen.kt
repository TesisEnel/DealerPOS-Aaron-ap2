package org.aarondeveloper.dealerpos.presentation.screens.registrate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.presentation.component.Cargando
import org.aarondeveloper.dealerpos.presentation.component.Input
import org.aarondeveloper.dealerpos.presentation.component.ModalBodyV1Screen
import org.aarondeveloper.dealerpos.presentation.component.Select
import org.aarondeveloper.dealerpos.presentation.component.TextArea
import org.aarondeveloper.dealerpos.ui.theme.ButtonBackground
import org.aarondeveloper.dealerpos.ui.theme.MarronEnd
import org.aarondeveloper.dealerpos.ui.theme.MoradoStart
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun RegistrateScreen(
    viewModel: RegistrateViewModel = koinViewModel(),
    onNavConfirmarClick: () -> Unit,
    onNavBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RegistrateBodyScreen(
        uiState = uiState,
        onNavConfirmarClick = onNavConfirmarClick,
        onNavBackClick = onNavBackClick,
        onNombreChange = viewModel::onNombreChange,
        onApellidoChange = viewModel::onApellidoChange,
        onEmailChange = viewModel::onEmailChange,
        onSexoChange = viewModel::onSexoChange,
        onContrasenaChange = viewModel::onContrasenaChange,
        onRepetirContrasenaChange = viewModel::onRepetirContrasenaChange,
        onTelefonoChange = viewModel::onTelefonoChange,
        onDireccionChange = viewModel::onDireccionChange,
        onImagenChange = viewModel::onImagenChange,
        onPaisChange = viewModel::onPaisChange,
        onEstadoChange = viewModel::onEstadoChange,
        onCiudadChange = viewModel::onCiudadChange,
        onConfirmarClick = viewModel::onConfirmarClick,
        onCerrarModalClick = viewModel::onCerrarModalClick
    )
}



@Composable
fun RegistrateBodyScreen(
    uiState: UiState,
    onNavConfirmarClick: () -> Unit,
    onNavBackClick: () -> Unit,
    onNombreChange: (String) -> Unit,
    onApellidoChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onSexoChange: (String) -> Unit,
    onContrasenaChange: (String) -> Unit,
    onRepetirContrasenaChange: (String) -> Unit,
    onTelefonoChange: (String) -> Unit,
    onDireccionChange: (String) -> Unit,
    onImagenChange: (String) -> Unit,
    onPaisChange: (Int) -> Unit,
    onEstadoChange: (Int) -> Unit,
    onCiudadChange: (Int) -> Unit,
    onConfirmarClick: (() -> Unit) -> Unit,
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
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )


        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(Color.Transparent)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .align(Alignment.Start)
            ) {
                Image(
                    painter = painterResource(resource = Res.drawable.back),
                    contentDescription = "Back Icon",
                    modifier = Modifier
                        .clickable {  }
                        .padding(8.dp)
                        .width(48.dp)
                        .height(48.dp)
                        .clickable {
                            onNavBackClick()
                        }
                )
            }

            Image(
                painter = painterResource(resource = Res.drawable.dilerpos),
                contentDescription = "DilerPOS Logo",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(top = 20.dp, bottom = 16.dp)
            )

            Text(
                text = uiState.language!!.registrate,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = uiState.language.complete_campos_registrarse,
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                textAlign = TextAlign.Center
            )

            Column{
                Input(
                    value = uiState.nombre ?: "",
                    onValueChange = { nombre -> onNombreChange(nombre) },
                    label = uiState.language.nombre,
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
                    value = uiState.apellido ?: "",
                    onValueChange = { apellido -> onApellidoChange(apellido) },
                    label = uiState.language.apellido,
                    borderRadius = 10,
                    borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                    borderSize = 2,
                    textSize = 16,
                    verticalPadding = 22,
                    type = "Text",
                    isPassword = false,
                    isDisabled = false
                )

                Input(
                    value = uiState.telefono ?: "",
                    onValueChange = { telefono -> onTelefonoChange(telefono) },
                    label = uiState.language.telefono,
                    borderRadius = 10,
                    borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                    borderSize = 2,
                    textSize = 16,
                    verticalPadding = 22,
                    type = "Number",
                    isPassword = false,
                    isDisabled = false
                )

                Input(
                    value = uiState.email ?: "",
                    onValueChange = { email -> onEmailChange(email) },
                    label = uiState.language.correo_electronico,
                    borderRadius = 10,
                    borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                    borderSize = 2,
                    textSize = 16,
                    verticalPadding = 22,
                    type = "Text",
                    isPassword = false,
                    isDisabled = false
                )

                Spacer(modifier = Modifier.height(10.dp))

                Select(
                    selectedOption = uiState.paisId.toString(),
                    onOptionChange = { paisId -> onPaisChange(paisId.toInt() ?: 0) },
                    titulo = uiState.language.pais,
                    options = uiState.paises.map { it.paisId.toString() to it.descripcion },
                    borderRadius = 10,
                    borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                    textSize = 16,
                    iconSize = 20,
                    verticalPadding = 20,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(10.dp))

                Select(
                    selectedOption = uiState.estadoId.toString(),
                    onOptionChange = { estadoId -> onEstadoChange(estadoId.toInt() ?: 0)  },
                    titulo = uiState.language.estado,
                    options = uiState.estados.map { it.estadoId.toString() to it.descripcion },
                    borderRadius = 10,
                    borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                    textSize = 16,
                    iconSize = 20,
                    verticalPadding = 20,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(10.dp))

                Select(
                    selectedOption = uiState.ciudadId.toString(),
                    onOptionChange = { ciudadId -> onCiudadChange(ciudadId.toInt() ?: 0)  },
                    titulo = uiState.language.ciudad,
                    options = uiState.ciudades.map { it.ciudadId.toString() to it.descripcion },
                    borderRadius = 10,
                    borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                    textSize = 16,
                    iconSize = 20,
                    verticalPadding = 20,
                    modifier = Modifier
                )

                TextArea(
                    value = uiState.direccion ?: "",
                    onValueChange = { direccion -> onDireccionChange(direccion) },
                    label = uiState.language.direccion,
                    borderRadius = 10,
                    borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                    borderSize = 2
                )

                Spacer(modifier = Modifier.height(10.dp))

                Select(
                    selectedOption = uiState.sexo ?: "",
                    onOptionChange = { sexo -> onSexoChange(sexo) },
                    titulo = uiState.language.sexo,
                    options = uiState.language.tipo_sexo,
                    borderRadius = 10,
                    borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                    textSize = 16,
                    iconSize = 20,
                    verticalPadding = 20,
                    modifier = Modifier
                )

                Input(
                    value = uiState.contrasena ?: "",
                    onValueChange = { contrasena -> onContrasenaChange(contrasena) },
                    label = uiState.language.contrasena,
                    borderRadius = 10,
                    borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                    borderSize = 2,
                    textSize = 16,
                    verticalPadding = 22,
                    type = "Text",
                    isPassword = true,
                    isDisabled = false
                )

                Input(
                    value = uiState.repetirContrasena ?: "",
                    onValueChange = { repetirContrasena -> onRepetirContrasenaChange(repetirContrasena) },
                    label = uiState.language.repetirContrasena,
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

                Button(
                    onClick = { onConfirmarClick(onNavConfirmarClick) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(2.dp, ButtonBackground, RoundedCornerShape(50.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        text = uiState.language.continuar + " (1/3)",
                        color = ButtonBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(100.dp))
            }

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
                        Res.drawable.password,
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
                    ModalBodyV1Screen(
                        Res.drawable.conexion_error,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.direct_right,
                        uiState.language!!.confirmado,
                        onButtonClick = onCerrarModalClick
                    )
                }

                "T5" -> {
                    ModalBodyV1Screen(
                        Res.drawable.noidentificado,
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
fun RegistrateScreenPreview() {

}

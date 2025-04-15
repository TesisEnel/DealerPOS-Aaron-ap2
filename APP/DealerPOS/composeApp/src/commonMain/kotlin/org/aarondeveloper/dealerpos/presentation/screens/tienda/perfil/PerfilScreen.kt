package org.aarondeveloper.dealerpos.presentation.screens.tienda.perfil


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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.librery.convertirBase64AImageBitmap
import org.aarondeveloper.dealerpos.presentation.component.Cargando
import org.aarondeveloper.dealerpos.presentation.component.Input
import org.aarondeveloper.dealerpos.presentation.component.ModalBodyV1Screen
import org.aarondeveloper.dealerpos.presentation.component.Select
import org.aarondeveloper.dealerpos.presentation.component.TextArea
import org.aarondeveloper.dealerpos.ui.theme.ButtonBackground
import org.aarondeveloper.dealerpos.ui.theme.MarronEnd
import org.aarondeveloper.dealerpos.ui.theme.MoradoStart
import org.aarondeveloper.dealerpos.ui.theme.SecondaryText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PerfilScreen(
    viewModel: PerfilViewModel = koinViewModel(),
    onNoAutenticado: () -> Unit,
    onNoPrincipal: () -> Unit,
    onInformacion: () -> Unit,
    onNavBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit){
        viewModel.verificarAuteticacion(onNoAutenticado)
    }

    PerfilBodyScreen(
        uiState = uiState,
        onNavBackClick = onNavBackClick,
        onNoPrincipal = onNoPrincipal,
        onInformacion = onInformacion,
        onNombreChange = viewModel::onNombreChange,
        onApellidoChange = viewModel::onApellidoChange,
        onSexoChange = viewModel::onSexoChange,
        onTelefonoChange = viewModel::onTelefonoChange,
        onDireccionChange = viewModel::onDireccionChange,
        onImagenChange = viewModel::onImagenChange,
        onPaisChange = viewModel::onPaisChange,
        onEstadoChange = viewModel::onEstadoChange,
        onCiudadChange = viewModel::onCiudadChange,
        onGuardarClick = viewModel::onGuardarClick,
        onCerrarModalClick = viewModel::onCerrarModalClick
    )

}


@Composable
fun PerfilBodyScreen(
    uiState: UiState,
    onNavBackClick: () -> Unit,
    onNoPrincipal: () -> Unit,
    onInformacion: () -> Unit,
    onNombreChange: (String) -> Unit,
    onApellidoChange: (String) -> Unit,
    onSexoChange: (String) -> Unit,
    onTelefonoChange: (String) -> Unit,
    onDireccionChange: (String) -> Unit,
    onImagenChange: (String) -> Unit,
    onPaisChange: (Int) -> Unit,
    onEstadoChange: (Int) -> Unit,
    onCiudadChange: (Int) -> Unit,
    onGuardarClick: () -> Unit,
    onCerrarModalClick: () -> Unit
) {
    val imageBitmap = convertirBase64AImageBitmap(uiState.imagen)

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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(resource = Res.drawable.back),
                    contentDescription = "Back Icon",
                    modifier = Modifier
                        .clickable { }
                        .padding(8.dp)
                        .width(48.dp)
                        .height(48.dp)
                        .clickable {
                            onNavBackClick()
                        }
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.language!!.perfil,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Image(
                    painter = painterResource(resource = Res.drawable.property),
                    contentDescription = "Favorite Icon",
                    modifier = Modifier
                        .clickable { onInformacion() }
                        .padding(8.dp)
                        .width(32.dp)
                        .height(32.dp)

                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center

            ) {

                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .border(3.dp, brush = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)), shape = CircleShape)
                        .padding(5.dp)
                ) {
                    if (imageBitmap != null) {
                        Image(
                            bitmap = imageBitmap,
                            contentDescription = "User Profile",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .clickable {

                                },
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        val imageResource = when (uiState.sexo) {
                            "masculino" -> painterResource(Res.drawable.chico)
                            "femenino" -> painterResource(Res.drawable.chica)
                            else -> painterResource(Res.drawable.chico)
                        }

                        Image(
                            painter = imageResource,
                            contentDescription = "User Profile",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .clickable {

                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Image(
                    painter = painterResource(resource = Res.drawable.magicpen),
                    contentDescription = "Edit Icon",
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.Center)
                        .offset(x = 55.dp, y = 20.dp)
                        .clickable {

                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = uiState.rol,
                color = SecondaryText,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 240.dp)
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
                    .padding(top = 30.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .background(Color.Transparent),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Input(
                        value = uiState.nombre,
                        onValueChange = { nombre -> onNombreChange(nombre) },
                        label = uiState.language!!.nombre,
                        borderRadius = 10,
                        borderColor = Brush.horizontalGradient(listOf(
                            MoradoStart,
                            MarronEnd
                        )),
                        borderSize = 2,
                        textSize = 16,
                        verticalPadding = 22,
                        type = "Text",
                        isPassword = false,
                        isDisabled = false
                    )

                    Input(
                        value = uiState.apellido,
                        onValueChange = { apellido -> onApellidoChange(apellido) },
                        label =  uiState.language.apellido,
                        borderRadius = 10,
                        borderColor = Brush.horizontalGradient(listOf(
                            MarronEnd,
                            MoradoStart
                        )),
                        borderSize = 2,
                        textSize = 16,
                        verticalPadding = 22,
                        type = "Text",
                        isPassword = false,
                        isDisabled = false
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Select(
                        selectedOption = uiState.sexo,
                        onOptionChange = { sexo -> onSexoChange(sexo) },
                        titulo = uiState.language.sexo,
                        options = uiState.language.tipo_sexo,
                        borderRadius = 10,
                        borderColor = Brush.horizontalGradient(listOf(
                            MoradoStart,
                            MarronEnd
                        )),
                        textSize = 16,
                        iconSize = 20,
                        verticalPadding = 20,
                        modifier = Modifier
                    )

                    Input(
                        value = uiState.telefono,
                        onValueChange = { telefono -> onTelefonoChange(telefono) },
                        label = uiState.language.telefono,
                        borderRadius = 10,
                        borderColor = Brush.horizontalGradient(listOf(
                            MarronEnd,
                            MoradoStart
                        )),
                        borderSize = 2,
                        textSize = 16,
                        verticalPadding = 22,
                        type = "Number",
                        isPassword = false,
                        isDisabled = false
                    )

                    Input(
                        value = uiState.email,
                        onValueChange = {  },
                        label = uiState.language.correo_electronico,
                        borderRadius = 10,
                        borderColor = Brush.horizontalGradient(listOf(
                            MoradoStart,
                            MarronEnd
                        )),
                        borderSize = 2,
                        textSize = 16,
                        verticalPadding = 22,
                        type = "Text",
                        isPassword = false,
                        isDisabled = true
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
                        value = uiState.direccion,
                        onValueChange = { direccion -> onDireccionChange(direccion) },
                        label = uiState.language.direccion,
                        borderRadius = 10,
                        borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                        borderSize = 2
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = uiState.language.advertencia_guardado,
                        color = Color.Gray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(bottom = 15.dp)
                    )

                    Button(
                        onClick = {
                            onGuardarClick()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(2.dp, ButtonBackground, RoundedCornerShape(50.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = uiState.language.guardar,
                            color = ButtonBackground,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(100.dp))
                }

            }
        }

        if (uiState.cargando) {
            Cargando()
        }

        if (uiState.modal) {
            when (uiState.tipoError) {
                "T1" -> {
                    ModalBodyV1Screen(
                        Res.drawable.noidentificado,
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
                        onButtonClick = onNoPrincipal
                    )
                }

                "T3" -> {
                    ModalBodyV1Screen(
                        Res.drawable.ico_check,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.direct_right,
                        uiState.language!!.confirmado,
                        onButtonClick = onCerrarModalClick
                    )
                }

                "T4" -> {
                    ModalBodyV1Screen(
                        Res.drawable.campos,
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
fun PerfilScreenPreview() {

}

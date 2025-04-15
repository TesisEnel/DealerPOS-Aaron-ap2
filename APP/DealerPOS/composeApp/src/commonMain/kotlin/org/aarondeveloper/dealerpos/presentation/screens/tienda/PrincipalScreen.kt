package org.aarondeveloper.dealerpos.presentation.screens.tienda

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.aarondeveloper.dealerpos.presentation.screens.tienda.home.HomeScreen
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.librery.convertirBase64AImageBitmap
import org.aarondeveloper.dealerpos.presentation.component.Cargando
import org.aarondeveloper.dealerpos.presentation.component.ModalBodyV1Screen
import org.aarondeveloper.dealerpos.presentation.component.NavItem
import org.aarondeveloper.dealerpos.presentation.component.NavigationBar
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
fun PrincipalScreen(
    viewModel: PrincipalViewModel = koinViewModel(),
    onNoAutenticado: () -> Unit,
    onFavoritosClick: () -> Unit,
    onHerramientasClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCarritoClick: () -> Unit,
    onPerfilClick: () -> Unit,
    onDetalleProducto: () -> Unit
) {

    LaunchedEffect(Unit){
        viewModel.verificarAuteticacion(onNoAutenticado)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PrincipalBodyScreen(
        uiState = uiState,
        onNoAutenticado = onNoAutenticado,
        onFavoritosClick = onFavoritosClick,
        onHerramientasClick = onHerramientasClick,
        onHomeClick = onHomeClick,
        onCarritoClick = onCarritoClick,
        onPerfilClick = onPerfilClick,
        onDetalleProducto = onDetalleProducto,
        onCerrarModalClick = viewModel::onCerrarModalClick,
        onCerrarSesionClick = viewModel::onCerrarSesionClick,
        onBusquedaChange = viewModel::onBusquedaChange
    )
}


@Composable
fun PrincipalBodyScreen(
    uiState: UiState,
    onNoAutenticado: () -> Unit,
    onFavoritosClick: () -> Unit,
    onHerramientasClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCarritoClick: () -> Unit,
    onPerfilClick: () -> Unit,
    onDetalleProducto: () -> Unit,
    onCerrarModalClick: () -> Unit,
    onCerrarSesionClick: (() -> Unit) -> Unit,
    onBusquedaChange: (String) -> Unit,

) {
    val imageBitmap = convertirBase64AImageBitmap(uiState.imagen.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.background_principal),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, top = 60.dp, end = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(2.dp, brush = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)), shape = CircleShape
                        )
                        .padding(4.dp)
                ) {
                    if (imageBitmap != null) {
                        Image(
                            bitmap = imageBitmap,
                            contentDescription = "User Profile",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .clickable {
                                    onPerfilClick()
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
                                    onPerfilClick()
                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = uiState.language!!.bienvenidos, fontSize = 14.sp, color = SecondaryText)
                        Text(
                            text = uiState.nombreUsuario ?: "",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = ButtonBackground,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        painter = painterResource(resource = Res.drawable.exit),
                        contentDescription = "Logout Button",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                onCerrarSesionClick(onNoAutenticado)
                            }
                    )

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.Transparent)
                    .border(
                        BorderStroke(2.dp, Brush.horizontalGradient(listOf(
                            MoradoStart,
                            MarronEnd
                        ))),
                        shape = RoundedCornerShape(25.dp)
                    )
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(resource = Res.drawable.search_icon),
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))


                BasicTextField(
                    value = uiState.busqueda ?: "",
                    onValueChange = { busqueda -> onBusquedaChange(busqueda) },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent)
                        .padding(8.dp),
                    singleLine = true,
                    textStyle = TextStyle(color = Color.Gray),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        ) {
                            if (uiState.busqueda!!.isEmpty()) {
                                Text(
                                    text = uiState.language!!.buscar_producto,
                                    color = Color.Gray,
                                    modifier = Modifier.align(Alignment.CenterStart)
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(resource = Res.drawable.settings),
                    contentDescription = "Settings Icon",
                    modifier = Modifier.size(24.dp)
                )
            }

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 210.dp)
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
        ) {
            Image(
                painter = painterResource(resource = Res.drawable.background_trasversal),
                contentDescription = "Background Transversal",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                HomeScreen(onDetalleProducto = onDetalleProducto)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp, end = 15.dp)
        ) {
            NavigationBar(
                items = listOf(
                    NavItem(uiState.language!!.favoritos, Res.drawable.btn_2, onFavoritosClick),
                    NavItem(uiState.language.herramientas, Res.drawable.more, onHerramientasClick),
                    NavItem(uiState.language.inicio, Res.drawable.btn_1, onHomeClick),
                    NavItem(uiState.language.carrito, Res.drawable.btn_3, onCarritoClick),
                    NavItem(uiState.language.perfil, Res.drawable.btn_4, onPerfilClick)
                ),
                initialSelectedItem = "Inicio",
                onItemSelected = { selectedItem -> }
            )
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
                        onButtonClick = onCerrarModalClick
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun PrincipalScreenPreview() {

}

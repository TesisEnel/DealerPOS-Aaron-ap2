package org.aarondeveloper.dealerpos.presentation.screens.tienda.home.detalles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dealerpos.composeapp.generated.resources.Res
import dealerpos.composeapp.generated.resources.advertencia
import dealerpos.composeapp.generated.resources.back
import dealerpos.composeapp.generated.resources.background_principal_solido
import dealerpos.composeapp.generated.resources.background_trasversal
import dealerpos.composeapp.generated.resources.conexion_error
import dealerpos.composeapp.generated.resources.direct_right
import dealerpos.composeapp.generated.resources.heart
import dealerpos.composeapp.generated.resources.heart_full
import dealerpos.composeapp.generated.resources.ico_check
import dealerpos.composeapp.generated.resources.noidentificado
import dealerpos.composeapp.generated.resources.product_default
import dealerpos.composeapp.generated.resources.star
import dealerpos.composeapp.generated.resources.vacio
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.AdicionalesDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.CaracteristicasDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.IconosDto
import org.aarondeveloper.dealerpos.librery.convertirBase64AImageBitmap
import org.aarondeveloper.dealerpos.presentation.component.Cargando
import org.aarondeveloper.dealerpos.presentation.component.Checked
import org.aarondeveloper.dealerpos.presentation.component.ModalBodyV1Screen
import org.aarondeveloper.dealerpos.presentation.component.Quantity
import org.aarondeveloper.dealerpos.presentation.screens.tienda.PrincipalViewModel
import org.aarondeveloper.dealerpos.presentation.screens.tienda.UiState
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
fun DetallesProductoScreen(
    viewModel: PrincipalViewModel = koinViewModel(),
    onNavBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        viewModel.getDetalleProducto()
    }

    DetallesProductoBodyScreen(
        uiState = uiState,
        onNavBackClick = onNavBackClick,
        onAgregarCarritoChange = viewModel::onAgregarCarritoChange,
        onCambiarFavoritoChange = viewModel::onCambiarFavoritoChange,
        onCerrarModalClick = viewModel::onCerrarModalClick,
        onCantidadSeleccionadaChange = viewModel::onCantidadSeleccionadaChange,
        onSeleccionarAdicionales = viewModel::onSeleccionarAdicionales
    )

}


@Composable
fun DetallesProductoBodyScreen(
    uiState: UiState,
    onNavBackClick: () -> Unit,
    onAgregarCarritoChange: () -> Unit,
    onCambiarFavoritoChange: () -> Unit,
    onCerrarModalClick: () -> Unit,
    onCantidadSeleccionadaChange: (Int) -> Unit,
    onSeleccionarAdicionales: (AdicionalesDto, Boolean) -> Unit,
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
                        .clickable { onNavBackClick() }
                        .padding(8.dp)
                        .width(48.dp)
                        .height(48.dp)
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.language!!.informacion,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Image(
                    painter = painterResource(
                        resource = if (uiState.isFavoritos!!) Res.drawable.heart_full else Res.drawable.heart
                    ),
                    contentDescription = "Favorite Icon",
                    modifier = Modifier
                        .clickable { onCambiarFavoritoChange() }
                        .padding(8.dp)
                        .width(32.dp)
                        .height(32.dp)
                )

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentAlignment = Alignment.Center
            ) {
                val imageBitmap = convertirBase64AImageBitmap(uiState.imagenProducto ?: "")
                if (imageBitmap != null) {
                    Image(
                        bitmap = imageBitmap,
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .fillMaxWidth(0.7f),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(resource = Res.drawable.product_default),
                        contentDescription = uiState.nombreProducto,
                        modifier = Modifier
                            .size(220.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 280.dp)
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
                    .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 100.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = uiState.nombreProducto ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(resource = Res.drawable.star),
                        contentDescription = "Estrellas Icon",
                        modifier = Modifier
                            .clickable { }
                            .size(24.dp)
                    )

                    Text(
                        text = "1/5",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 5.dp, end = 10.dp)
                    )
                }

                Text(
                    text = uiState.descripcionProducto ?: "",
                    fontSize = 15.sp,
                    color = SecondaryText,
                    modifier = Modifier.padding(top = 15.dp, start = 10.dp)
                )

                ListarCaracteristicas(uiState.caracteristicas ?: emptyList(), uiState.iconos ?: emptyList())

                Text(
                    text = uiState.language!!.adicionales,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                ListaAdicionales(uiState.adicionales ?: emptyList(), onSeleccionarAdicionales = onSeleccionarAdicionales)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(10.dp, bottom = 20.dp)
                    .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 50.dp))
                    .background(Color.Transparent)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 5.dp)
                        ){
                            Text(
                                text = uiState.language!!.cantidad,
                                fontSize = 12.sp,
                                color = SecondaryText
                            )

                            Spacer(modifier = Modifier.width(8.dp))


                            Quantity(
                                cantidad = uiState.cantidadSeleccionada ?: 1,
                                onIncrement = { onCantidadSeleccionadaChange(1) },
                                onDecrement = { if ((uiState.cantidadSeleccionada ?: 0) > 0) onCantidadSeleccionadaChange(-1) },
                                modifier = Modifier.padding(start = 8.dp),
                                buttonColor = Color.Transparent,
                                borderColor = Color.Gray,
                                textColor = Color.White,
                                minCantidad = 1,
                                maxCantidad = uiState.cantidadProducto ?: 0
                            )

                        }

                        Text(
                            text = "RD$ " + uiState.total,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Button(
                        onClick = {
                            onAgregarCarritoChange()
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .border(2.dp, ButtonBackground, RoundedCornerShape(10.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = "+      " + uiState.language!!.carrito,
                            color = ButtonBackground,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
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
                        onButtonClick = onCerrarModalClick
                    )
                }

                "T3" -> {
                    ModalBodyV1Screen(
                        Res.drawable.product_default,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.direct_right,
                        uiState.language!!.confirmado,
                        onButtonClick = onNavBackClick
                    )
                }

                "T4" -> {
                    ModalBodyV1Screen(
                        Res.drawable.heart_full,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.direct_right,
                        uiState.language!!.confirmado,
                        onButtonClick = onCerrarModalClick
                    )
                }

                "T5" -> {
                    ModalBodyV1Screen(
                        Res.drawable.vacio,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.direct_right,
                        uiState.language!!.confirmado,
                        onButtonClick = onCerrarModalClick
                    )
                }

                "T6" -> {
                    ModalBodyV1Screen(
                        Res.drawable.ico_check,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.direct_right,
                        uiState.language!!.confirmado,
                        onButtonClick = onNavBackClick
                    )
                }

                "T7" -> {
                    ModalBodyV1Screen(
                        Res.drawable.advertencia,
                        uiState.errorMessage ?: "",
                        400,
                        Res.drawable.direct_right,
                        uiState.language!!.confirmado,
                        onButtonClick = onNavBackClick
                    )
                }
            }
        }
    }
}




@Composable
fun ListarCaracteristicas(caracteristicas: List<CaracteristicasDto>, iconos: List<IconosDto>) {

    LazyRow(
        contentPadding = PaddingValues(bottom = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        items(caracteristicas.size) { indice ->
            val caracteristica = caracteristicas[indice]
            val icono = iconos.find { it.iconoId == caracteristica.iconoId }

            Card(
                shape = RoundedCornerShape(25.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                border = BorderStroke(2.dp, Brush.horizontalGradient(listOf(MoradoStart, MarronEnd))),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .background(Color.Transparent)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    val iconBitmap = convertirBase64AImageBitmap(icono?.imagen ?: "")
                    if (iconBitmap != null) {
                        Image(
                            bitmap = iconBitmap,
                            contentDescription = caracteristica.nombre,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 16.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(resource = Res.drawable.product_default),
                            contentDescription = caracteristica.nombre,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 16.dp)
                        )
                    }

                    Column (
                        modifier = Modifier.padding(end = 10.dp)
                    ){
                        Text(
                            text = caracteristica.nombre,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = caracteristica.descripcion,
                            fontSize = 12.sp,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun ListaAdicionales(
    adicionales: List<AdicionalesDto>, onSeleccionarAdicionales: (AdicionalesDto, Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        adicionales.forEach { adicional ->
            var isChecked by remember { mutableStateOf(false) }

            Checked(
                imagen = adicional.imagen,
                titulo = adicional.descripcion,
                descripcion = "RD$ " + adicional.precio,
                isChecked = isChecked,
                onCheckedChange = { checked ->
                    isChecked = checked
                    onSeleccionarAdicionales(adicional, checked)
                }
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}



@Preview
@Composable
fun DetallesProductoScreenPreview() {
}

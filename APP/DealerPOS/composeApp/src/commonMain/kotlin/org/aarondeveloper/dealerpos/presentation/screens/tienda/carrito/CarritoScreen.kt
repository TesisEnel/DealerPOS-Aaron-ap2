package org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.librery.Carrito
import org.aarondeveloper.dealerpos.librery.carrito
import org.aarondeveloper.dealerpos.librery.convertirBase64AImageBitmap
import org.aarondeveloper.dealerpos.librery.productos
import org.aarondeveloper.dealerpos.presentation.component.Cargando
import org.aarondeveloper.dealerpos.presentation.component.ModalBodyV1Screen
import org.aarondeveloper.dealerpos.presentation.component.Quantity
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
fun CarritoComprasScreen(
    viewModel: CarritoViewModel = koinViewModel(),
    onNoAutenticado: () -> Unit,
    onNavBackClick: () -> Unit,
    onInformacion: () -> Unit,
    onFormularioCompra: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        viewModel.verificarAuteticacion(onNoAutenticado)
    }

    CarritoComprasBodyScreen(
        uiState = uiState,
        onNavBackClick = onNavBackClick,
        onInformacion = onInformacion,
        onFormularioCompra = onFormularioCompra,
        onCerrarModalClick = viewModel::onCerrarModalClick,
        carrito = carrito
    )
}


@Composable
fun CarritoComprasBodyScreen(
    uiState: UiState,
    onNavBackClick: () -> Unit,
    onInformacion: () -> Unit,
    onFormularioCompra: () -> Unit,
    onCerrarModalClick: () -> Unit,
    carrito: List<Carrito>,
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
                .padding(16.dp, top = 40.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                        text = "Carrito de Compras",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Image(
                    painter = painterResource(resource = Res.drawable.property),
                    contentDescription = "Information Icon",
                    modifier = Modifier
                        .clickable { onInformacion() }
                        .padding(8.dp)
                        .width(32.dp)
                        .height(32.dp)
                )
            }

            Text(
                text = uiState.language!!.lista_articulos_anadidos,
                color = Color.Gray,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp)
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
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 30.dp, start = 20.dp, end = 20.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    carrito.forEach { carrito ->
                        val producto = productos.find { it.productoId == carrito.productoId }
                        if (producto != null) {
                            CarritoItem(
                                imagen = producto.imagen,
                                nombre = producto.nombre,
                                precio = producto.precio.toString(),
                                onRemoveClick = {

                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
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
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 5.dp)
                            ){
                                Text(
                                    text = uiState.language!!.total,
                                    fontSize = 12.sp,
                                    color = SecondaryText
                                )
                            }

                            Text(
                                text = "RD$ 237,000.00",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Button(
                            onClick = {
                                onFormularioCompra()
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .border(2.dp, ButtonBackground, RoundedCornerShape(10.dp)),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Row (
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(
                                    painter = painterResource(resource = Res.drawable.clasificate),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .padding(end = 6.dp),
                                    contentScale = ContentScale.Fit
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = uiState.language!!.continuar,
                                    color = ButtonBackground,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }
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
            }
        }
    }
}



@Composable
fun CarritoItem(
    imagen: String?,
    nombre: String,
    precio: String,
    onRemoveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Transparent)
            .border(
                BorderStroke(2.dp, Brush.horizontalGradient(listOf(MoradoStart, MarronEnd))),
                RoundedCornerShape(10.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val imageBitmap = imagen?.let { convertirBase64AImageBitmap(it) }

        if (imageBitmap != null) {
            Image(
                bitmap = imageBitmap,
                contentDescription = nombre,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(end = 6.dp),
                contentScale = ContentScale.Fit
            )
        } else {
            Image(
                painter = painterResource(resource = Res.drawable.product_default),
                contentDescription = nombre,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(end = 6.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = precio,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


                var cantidad by remember { mutableStateOf(1) }
                Quantity(
                    cantidad = cantidad,
                    onIncrement = { cantidad++ },
                    onDecrement = { if (cantidad > 1) cantidad-- },
                    modifier = Modifier.padding(start = 8.dp),
                    buttonColor = Color.Transparent,
                    borderColor = Color.Gray,
                    textColor = Color.White,
                    minCantidad = 0,
                    maxCantidad = productos[1].cantidad
                )

                Spacer(modifier = Modifier.width(4.dp))
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Image(
            painter = painterResource(resource = Res.drawable.trash),
            contentDescription = "Remove Favorite",
            modifier = Modifier
                .size(32.dp)
                .clickable { onRemoveClick() }
                .padding(4.dp),
            contentScale = ContentScale.Fit
        )
    }
}



@Preview
@Composable
fun CarritoComprasScreenPreview() {

}

package org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito

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
import org.aarondeveloper.dealerpos.librery.carrito
import org.aarondeveloper.dealerpos.librery.convertirBase64AImageBitmap
import org.aarondeveloper.dealerpos.librery.productos
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
fun ResumenCompraScreen(
    viewModel: CarritoViewModel = koinViewModel(),
    onNoAutenticado: () -> Unit,
    onNavBackClick: () -> Unit,
    onInformacion: () -> Unit,
    onCompletadoCompra: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ResumenCompraBodyScreen(
        uiState = uiState,
        onNavBackClick = onNavBackClick,
        onInformacion = onInformacion,
        onCompletadoCompra = onCompletadoCompra,
        onCerrarModalClick = viewModel::onCerrarModalClick,
    )

}

@Composable
fun ResumenCompraBodyScreen(
    uiState: UiState,
    onNavBackClick: () -> Unit,
    onInformacion: () -> Unit,
    onCompletadoCompra: () -> Unit,
    onCerrarModalClick: () -> Unit,
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
                text = "Resumen detallado de su orden",
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
                            ResumenItem(
                                imagen = producto.imagen,
                                nombre = producto.nombre,
                                precio = producto.precio.toString()
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
                            ) {
                                Column {
                                    Text(
                                        text = "Sub Total",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )

                                    Text(
                                        text = "RD$ 135,600.00",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )

                                }

                                Spacer(modifier = Modifier.weight(1f))

                                Column (
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        text = "IVA",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )

                                    Text(
                                        text = "RD$ 825.00",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )

                                }

                            }



                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 5.dp)
                            ){

                                Column  {
                                    Text(
                                        text = "Descuento",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )

                                    Text(
                                        text = "RD$ 1,400.00",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.weight(1f))

                                Column (
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        text = "Envio",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )

                                    Text(
                                        text = "RD\$ 960.00",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                    )
                                }
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 5.dp)
                            ) {

                                Column {
                                    Text(
                                        text = "Total",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )

                                    Text(
                                        text = "RD$ 237,000.00",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.weight(1f))

                                Button(
                                    onClick = {
                                        onCompletadoCompra()
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
                                            text = "Continuar",
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
            }
        }
    }
}


@Composable
fun ResumenItem(
    imagen: String?,
    nombre: String,
    precio: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageBitmap = imagen?.let { convertirBase64AImageBitmap(it) }
            if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = nombre,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .padding(bottom = 2.dp),
                    contentScale = ContentScale.Fit
                )
            } else {
                Image(
                    painter = painterResource(resource = Res.drawable.product_default),
                    contentDescription = nombre,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .padding(bottom = 8.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Sub Total: RD$ $precio",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "IVA: RD$ 118.00",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {

                    Text(
                        text = "Descuento: RD\$ 10.00",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "Cantidad: 2",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Text(
                text = "Total: RD$ 200.00",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)))
                .align(Alignment.BottomCenter)
        )
    }
}





@Preview
@Composable
fun ResumenCompraScreenPreview() {

}

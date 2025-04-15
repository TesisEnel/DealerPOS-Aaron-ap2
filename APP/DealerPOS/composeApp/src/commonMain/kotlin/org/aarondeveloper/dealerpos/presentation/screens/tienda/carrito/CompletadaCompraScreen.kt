package org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.librery.detalleVentaPago
import org.aarondeveloper.dealerpos.librery.venta
import org.aarondeveloper.dealerpos.ui.theme.ButtonBackground
import org.aarondeveloper.dealerpos.ui.theme.MarronEnd
import org.aarondeveloper.dealerpos.ui.theme.MoradoStart
import org.aarondeveloper.dealerpos.ui.theme.PrimaryText
import org.aarondeveloper.dealerpos.ui.theme.SecondaryText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun CompletadaCompraScreen(
    viewModel: CarritoViewModel = koinViewModel(),
    onNoAutenticado: () -> Unit,
    onFinalizadoCompra: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CompletadaCompraBodyScreen(
        uiState = uiState,
        onNoAutenticado = onNoAutenticado,
        onFinalizadoCompra = onFinalizadoCompra,
        onCerrarModalClick = viewModel::onCerrarModalClick,
    )

}

@Composable
fun CompletadaCompraBodyScreen(
    uiState: UiState,
    onNoAutenticado: () -> Unit,
    onFinalizadoCompra: () -> Unit,
    onCerrarModalClick: () -> Unit,
) {

    val pais = "Republica Dominicana"
    val provincia = "Maria Trinidad Sanchez"
    val ciudad = "Nagua"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.background),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 5.dp, start = 20.dp, end = 20.dp)
            ) {

                Image(
                    painter = painterResource(resource = Res.drawable.verificate),
                    contentDescription = "Validation Icon",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(top = 30.dp, bottom = 5.dp)
                )

                Text(
                    text = "¡Todo está listo! \nSu compra fue registrada correctamente.",
                    color = PrimaryText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(2.dp, Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)), RoundedCornerShape(30.dp))
                            .padding(16.dp)
                    ) {
                        Column{

                            Column (modifier = Modifier
                                .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
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
                                        text = "RESUMEN DE SU ORDEN",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Image(
                                        painter = painterResource(resource = Res.drawable.clasificate),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .padding(end = 6.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Column(
                                modifier = Modifier.padding(bottom = 8.dp)
                            ) {
                                Text(
                                    text = "Nombre Completo",
                                    fontSize = 12.sp,
                                    color = SecondaryText
                                )
                                Text(
                                    text = venta.nombre,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Número de Pedido",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )
                                    Text(
                                        text = venta.numeroPedido,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                }

                                Column (
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        text = "Fecha",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )
                                    Text(
                                        text = venta.fecha,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column  {
                                    Text(
                                        text = "Método de Entrega",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )
                                    Text(
                                        text = venta.metodoEntrega,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                }

                                Column (
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        text = "Métodos de Pago",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )

                                    val metodosPago = detalleVentaPago.joinToString(", ") { it.metodoPago }

                                    Text(
                                        text = metodosPago,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                }

                            }

                            if (venta.metodoEntrega == "Domicilio") {

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    ) {
                                        Text(
                                            text = "País",
                                            fontSize = 12.sp,
                                            color = SecondaryText
                                        )
                                        Text(
                                            text = pais,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryText
                                        )
                                    }

                                    Column (
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        Text(
                                            text = "Provincia",
                                            fontSize = 12.sp,
                                            color = SecondaryText
                                        )
                                        Text(
                                            text = provincia,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryText
                                        )
                                    }

                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Column {
                                        Text(
                                            text = "Ciudad",
                                            fontSize = 12.sp,
                                            color = SecondaryText
                                        )
                                        Text(
                                            text = ciudad,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryText
                                        )
                                    }

                                    Column (
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        Text(
                                            text = "Dirección",
                                            fontSize = 12.sp,
                                            color = SecondaryText
                                        )
                                        Text(
                                            text = venta.direccion,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryText
                                        )
                                    }
                                }
                            }

                            Column(
                                modifier = Modifier.padding(bottom = 8.dp)
                            ) {
                                Text(
                                    text = "Estado de Entrega",
                                    fontSize = 12.sp,
                                    color = SecondaryText
                                )
                                Text(
                                    text = venta.estadoEntrega,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Total Compra",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )
                                    Text(
                                        text = "RD\$ 2,350.00",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                }

                                Column (
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        text = "Total Pagado",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )
                                    Text(
                                        text = "RD\$ 2,350.00",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                onFinalizadoCompra()
                            },
                            modifier = Modifier
                                .width(230.dp)
                                .height(50.dp)
                                .border(2.dp, ButtonBackground, RoundedCornerShape(50.dp)),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Image(
                                painter = painterResource(resource = Res.drawable.star),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(end = 6.dp),
                                contentScale = ContentScale.Fit
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Confirmado",
                                color = ButtonBackground,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Image(
                                painter = painterResource(resource = Res.drawable.ico_check),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(end = 6.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
    }
}






@Preview
@Composable
fun CompletadaCompraScreenPreview() {

}

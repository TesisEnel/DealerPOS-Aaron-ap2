package org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import org.aarondeveloper.dealerpos.presentation.component.Expandable
import org.aarondeveloper.dealerpos.presentation.component.Input
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
fun PagoCompraScreen(
    viewModel: CarritoViewModel = koinViewModel(),
    onNoAutenticado: () -> Unit,
    onNavBackClick: () -> Unit,
    onInformacion: () -> Unit,
    onResumenCompra: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PagoCompraBodyScreen(
        uiState = uiState,
        onNavBackClick = onNavBackClick,
        onInformacion = onInformacion,
        onResumenCompra = onResumenCompra,
        onCerrarModalClick = viewModel::onCerrarModalClick,
    )

}

@Composable
fun PagoCompraBodyScreen(
    uiState: UiState,
    onNavBackClick: () -> Unit,
    onInformacion: () -> Unit,
    onResumenCompra: () -> Unit,
    onCerrarModalClick: () -> Unit,
) {

    var efectivoChecked by remember { mutableStateOf(true) }
    var tarjetaChecked by remember { mutableStateOf(true) }
    var transferenciaChecked by remember { mutableStateOf(true) }
    var efectivoCompletado by remember { mutableStateOf(true) }
    var transferenciaCompletado by remember { mutableStateOf(false) }
    var tarjetaCompletado by remember { mutableStateOf(true) }

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
                text = "Procesamiento de Pagos",
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

                    if(efectivoChecked == true){
                        Expandable(
                            title = "Efectivo",
                            content = { ContenidoEfectivo() },
                            borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                            textColor = Color.White,
                            textSize = 18.sp,
                            currentIcon = painterResource(resource = if (efectivoCompletado) Res.drawable.ico_check else Res.drawable.direct_botom)
                        )
                    }

                    if(transferenciaChecked == true){
                        Expandable(
                            title = "Tarjeta",
                            content = { ContenidoTarjeta() },
                            borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                            textColor = Color.White,
                            textSize = 18.sp,
                            currentIcon = painterResource(resource = if (transferenciaCompletado) Res.drawable.ico_check else Res.drawable.direct_botom)
                        )
                    }

                    if(tarjetaChecked == true){
                        Expandable(
                            title = "Transferencia",
                            content = { ContenidoTransferencia() },
                            borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                            textColor = Color.White,
                            textSize = 18.sp,
                            currentIcon = painterResource(resource = if (tarjetaCompletado) Res.drawable.ico_check else Res.drawable.direct_botom)
                        )
                    }

                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, bottom = 20.dp)
                        .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 50.dp))
                        .background(Color.Transparent)
                ){

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
                                        text = "Restante",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )

                                    Text(
                                        text = "RD$ 0.00",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )

                                }

                                Spacer(modifier = Modifier.weight(1f))

                                Column(
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        text = "Total",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )

                                    Text(
                                        text = "RD\$ 135,600.00",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }

                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(
                                    onClick = {
                                        onResumenCompra()
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
                                            text = "FORMALIZAR COMPRA",
                                            color = ButtonBackground,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
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
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ContenidoEfectivo() {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Input(
            value = "",
            onValueChange = {  },
            label = "Monto",
            borderRadius = 10,
            borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
            borderSize = 2,
            textSize = 14,
            verticalPadding = 12,
            type = "Number",
            isPassword = false,
            isDisabled = false
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 20.dp)
                    .border(1.dp, ButtonBackground, RoundedCornerShape(6.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(resource = Res.drawable.like),
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 4.dp),
                        contentScale = ContentScale.Fit
                    )

                    Text(
                        text = "Completado",
                        color = ButtonBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                }
            }
        }

    }
}

@Composable
fun ContenidoTransferencia() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BancoInfo(
            bankName = "Banco #1",
            accountNumber = "Cuenta #: 123456789",
            identityNumber = "Identidad #: 402-0000000-0",
            email = "Email: ejemplo@correo.com"
        )

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        BancoInfo(
            bankName = "Banco #2",
            accountNumber = "Cuenta #: 987654321",
            identityNumber = "Identidad #: 402-0000000-0",
            email = "Email: otro@correo.com"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Input(
            value = "",
            onValueChange = { },
            label = "Monto",
            borderRadius = 10,
            borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
            borderSize = 2,
            textSize = 14,
            verticalPadding = 12,
            type = "Number",
            isPassword = false,
            isDisabled = false
        )

        Input(
            value = "",
            onValueChange = { },
            label = "Nombre del Banco",
            borderRadius = 10,
            borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
            borderSize = 2,
            textSize = 14,
            verticalPadding = 12,
            type = "Text",
            isPassword = false,
            isDisabled = false
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)), RoundedCornerShape(10.dp))
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(resource = Res.drawable.file),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Color.Gray
                )
                Text(
                    text = "Comparte tu recibo de pago",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 20.dp)
                    .border(1.dp, ButtonBackground, RoundedCornerShape(6.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(resource = Res.drawable.like),
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 4.dp),
                        contentScale = ContentScale.Fit
                    )

                    Text(
                        text = "Completado",
                        color = ButtonBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}


@Composable
fun ContenidoTarjeta() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Input(
            value = "",
            onValueChange = {  },
            label = "Monto a Pagar",
            borderRadius = 10,
            borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
            borderSize = 2,
            textSize = 14,
            verticalPadding = 12,
            type = "Number",
            isPassword = false,
            isDisabled = false
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp)
                .border(
                    2.dp,
                    Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                    RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Cargando...",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 20.dp)
                    .border(1.dp, ButtonBackground, RoundedCornerShape(6.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(resource = Res.drawable.like),
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 4.dp),
                        contentScale = ContentScale.Fit
                    )

                    Text(
                        text = "Completado",
                        color = ButtonBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}


@Composable
fun BancoInfo(bankName: String, accountNumber: String, identityNumber: String, email: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = bankName,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = accountNumber,
            color = SecondaryText,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = identityNumber,
            color = SecondaryText,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = email,
            color = SecondaryText,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}




@Preview
@Composable
fun PagoCompraScreenPreview() {

}

package org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import org.aarondeveloper.dealerpos.librery.venta
import org.aarondeveloper.dealerpos.presentation.component.HoverToggle
import org.aarondeveloper.dealerpos.presentation.component.Input
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
fun FormularioCompraScreen(
    viewModel: CarritoViewModel = koinViewModel(),
    onNoAutenticado: () -> Unit,
    onNavBackClick: () -> Unit,
    onInformacion: () -> Unit,
    onPagoCompra: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FormularioCompraBodyScreen(
        uiState = uiState,
        onNavBackClick = onNavBackClick,
        onInformacion = onInformacion,
        onPagoCompra = onPagoCompra,
        onCerrarModalClick = viewModel::onCerrarModalClick,
    )

}

@Composable
fun FormularioCompraBodyScreen(
    uiState: UiState,
    onNavBackClick: () -> Unit,
    onInformacion: () -> Unit,
    onPagoCompra: () -> Unit,
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
                text = "Formulario del destinatario",
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

                    Text(
                        text = "Datos del Cliente",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.offset(x = 10.dp)
                    )

                    Input(
                        value = venta.nombre,
                        onValueChange = { venta.nombre = it },
                        label = "Nombre Completo",
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
                        value = venta.telefono,
                        onValueChange = { venta.telefono = it },
                        label = "Télefono",
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
                        value = venta.email,
                        onValueChange = {venta.email = it },
                        label = "Correo Electronico",
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

                    Text(
                        text = "Metodos de Entrega",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.offset(x = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Select(
                        selectedOption = venta.metodoEntrega,
                        onOptionChange = { venta.metodoEntrega = it },
                        titulo = "Metodos de Entrega",
                        options = listOf("Negocio" to "Negocio", "Domicilio" to "Domicilio"),
                        borderRadius = 10,
                        borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                        textSize = 16,
                        iconSize = 20,
                        verticalPadding = 20,
                        modifier = Modifier
                    )

                    if (venta.metodoEntrega == "domicilio") {
                        Spacer(modifier = Modifier.height(10.dp))

                        Select(
                            selectedOption = venta.paisId.toString(),
                            onOptionChange = { venta.paisId },
                            titulo = "Pais",
                            options = listOf("1" to "Republica Dominicana"),
                            borderRadius = 10,
                            borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                            textSize = 16,
                            iconSize = 20,
                            verticalPadding = 20,
                            modifier = Modifier
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Select(
                            selectedOption = venta.provinciaId.toString(),
                            onOptionChange = { venta.provinciaId },
                            titulo = "Provincia",
                            options = listOf("1" to "Maria Trinidad Sanchez"),
                            borderRadius = 10,
                            borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                            textSize = 16,
                            iconSize = 20,
                            verticalPadding = 20,
                            modifier = Modifier
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Select(
                            selectedOption = venta.ciudadId.toString(),
                            onOptionChange = { venta.ciudadId },
                            titulo = "Ciudad",
                            options = listOf("1" to "Nagua"),
                            borderRadius = 10,
                            borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                            textSize = 16,
                            iconSize = 20,
                            verticalPadding = 20,
                            modifier = Modifier
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        TextArea(
                            value = venta.direccion,
                            onValueChange = { venta.direccion = it },
                            label = "Dirección",
                            borderRadius = 10,
                            borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                            borderSize = 2
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Metodos de Pagos",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.offset(x = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            var efectivoChecked by remember { mutableStateOf(false) }
                            var tarjetaChecked by remember { mutableStateOf(false) }
                            var transferenciaChecked by remember { mutableStateOf(false) }

                            HoverToggle(
                                imagen = Res.drawable.ico_efectivo,
                                titulo = "Efectivo",
                                descripcion = "",
                                isChecked = efectivoChecked,
                                onCheckedChange = { efectivoChecked = it }
                            )

                            HoverToggle(
                                imagen = Res.drawable.ico_tarjeta,
                                titulo = "Tarjeta",
                                descripcion = "",
                                isChecked = tarjetaChecked,
                                onCheckedChange = { tarjetaChecked = it }
                            )

                            HoverToggle(
                                imagen = Res.drawable.ico_transferencia,
                                titulo = "Transferencia",
                                descripcion = "",
                                isChecked = transferenciaChecked,
                                onCheckedChange = { transferenciaChecked = it }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "¿Deseas comentarnos algo?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.offset(x = 10.dp)
                    )

                    TextArea(
                        value = venta.nota,
                        onValueChange = { venta.nota = it },
                        label = "Nota",
                        borderRadius = 10,
                        borderColor = Brush.horizontalGradient(listOf(MoradoStart, MarronEnd)),
                        borderSize = 2
                    )

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
                                    text = "Total",
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
                                onPagoCompra()
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



@Preview
@Composable
fun FormularioCompraScreenPreview() {

}

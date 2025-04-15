package org.aarondeveloper.dealerpos.presentation.component

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dealerpos.composeapp.generated.resources.Res
import dealerpos.composeapp.generated.resources.advertencia
import dealerpos.composeapp.generated.resources.back
import dealerpos.composeapp.generated.resources.background
import org.aarondeveloper.dealerpos.ui.theme.ButtonBackground
import org.aarondeveloper.dealerpos.ui.theme.PrimaryText
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun ModalBodyV1Screen(
    icono: DrawableResource,
    texto: String,
    altura: Int,
    iconoBoton: DrawableResource,
    textoBoton: String,
    onButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent.copy(alpha = 0.7f))
            .pointerInput(Unit) {
                detectTapGestures { }
            }
            .zIndex(1f),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .requiredSizeIn(maxHeight = altura.dp)
                .padding(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Transparent, shape = RoundedCornerShape(20.dp))
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
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(resource = icono),
                        contentDescription = "Error Icon",
                        modifier = Modifier
                            .size(150.dp)
                            .padding(bottom = 16.dp)
                    )

                    Text(
                        text = texto,
                        color = PrimaryText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Button(
                            onClick = { onButtonClick() },
                            modifier = Modifier
                                .border(2.dp, ButtonBackground, RoundedCornerShape(50.dp)),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Image(
                                painter = painterResource(resource = iconoBoton),
                                contentDescription = "Button Icon",
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(end = 8.dp)
                            )
                            Text(
                                text = textoBoton,
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




@Composable
fun ModalBodyV2Screen(
    icono: DrawableResource,
    texto: String,
    altura: Int,
    icono1Boton: DrawableResource,
    texto1Boton: String,
    onButton1Click: () -> Unit,
    icono2Boton: DrawableResource,
    texto2Boton: String,
    onButton2Click: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent.copy(alpha = 0.7f))
            .pointerInput(Unit) {
                detectTapGestures { }
            }
            .zIndex(1f),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .requiredSizeIn(maxHeight = altura.dp)
                .padding(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Transparent, shape = RoundedCornerShape(20.dp))
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
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(resource = icono),
                        contentDescription = "Error Icon",
                        modifier = Modifier
                            .size(150.dp)
                            .padding(bottom = 16.dp)
                    )

                    Text(
                        text = texto,
                        color = PrimaryText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Button(
                            onClick = { onButton1Click() },
                            modifier = Modifier
                                .weight(1f)
                                .border(2.dp, ButtonBackground, RoundedCornerShape(50.dp)),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Image(
                                painter = painterResource(resource = icono1Boton),
                                contentDescription = "Back Icon",
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(end = 8.dp)
                            )
                            Text(
                                text = texto1Boton,
                                color = ButtonBackground,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Button(
                            onClick = { onButton2Click() },
                            modifier = Modifier
                                .weight(1f)
                                .border(2.dp, ButtonBackground, RoundedCornerShape(50.dp)),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Image(
                                painter = painterResource(resource = icono2Boton),
                                contentDescription = "Retry Icon",
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(end = 8.dp)
                            )
                            Text(
                                text = texto2Boton,
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






@Preview
@Composable
fun ModalesScreenPreview() {
    //ModalBodyV1Screen(Res.drawable.advertencia, "Texto", 400, Res.drawable.back, "Retroceder", onButtonClick = {})
    ModalBodyV2Screen(Res.drawable.advertencia, "Texto", 400, Res.drawable.back, "Retroceder", onButton1Click = {}, Res.drawable.back, "Confirmado", onButton2Click = {})
}

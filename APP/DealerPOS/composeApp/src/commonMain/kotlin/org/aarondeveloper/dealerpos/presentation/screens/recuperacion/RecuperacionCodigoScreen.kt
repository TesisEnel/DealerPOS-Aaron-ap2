package org.aarondeveloper.dealerpos.presentation.screens.recuperacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.presentation.component.Input
import org.aarondeveloper.dealerpos.ui.theme.ButtonBackground
import org.aarondeveloper.dealerpos.ui.theme.MarronEnd
import org.aarondeveloper.dealerpos.ui.theme.MoradoStart
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RecuperacionCodigoScreen(
    onContinuarClick: () -> Unit
) {
    RecuperacionCodigoBodyScreen(onContinuarClick)
}

@Composable
fun RecuperacionCodigoBodyScreen(
    onContinuarClick: () -> Unit
) {
    val codigo = remember { androidx.compose.runtime.mutableStateOf("") }
    var isResendEnabled by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(60) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.background),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(resource = Res.drawable.security),
                contentDescription = "Verification Icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(top = 20.dp, bottom = 16.dp)
            )

            Text(
                text = "Revise la bandeja de entrada de su correo electrónico. Le hemos enviado un código de verificación para confirmar su cuenta.",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Input(
                value = codigo.value,
                onValueChange = { codigo.value = it },
                label = "Codigo",
                borderRadius = 10,
                borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                borderSize = 2,
                textSize = 16,
                verticalPadding = 22,
                type = "Text",
                isPassword = false,
                isDisabled = false
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onContinuarClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(2.dp, ButtonBackground, RoundedCornerShape(50.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "Continuar (2/3)",
                    color = ButtonBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isResendEnabled) "Reenviar código" else "Reenviar en $timeLeft segundos",
                color = if (isResendEnabled) Color.Blue else Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier
                    .clickable(enabled = isResendEnabled) {
                        if (isResendEnabled) {
                            isResendEnabled = false
                            timeLeft = 60
                        }
                    }
            )

            Spacer(modifier = Modifier.height(100.dp))
        }
    }

    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            kotlinx.coroutines.delay(1000L)
            timeLeft -= 1
        } else {
            isResendEnabled = true
        }
    }
}

@Preview
@Composable
fun RecuperacionCodigoScreenPreview() {
    RecuperacionCodigoScreen(
        onContinuarClick = {}
    )
}

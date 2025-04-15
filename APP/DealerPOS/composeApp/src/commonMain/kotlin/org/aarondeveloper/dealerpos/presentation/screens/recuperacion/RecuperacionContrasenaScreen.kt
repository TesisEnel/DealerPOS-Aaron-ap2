package org.aarondeveloper.dealerpos.presentation.screens.recuperacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun RecuperacionContrasenaScreen(
    onContinuarClick: () -> Unit
) {
    RecuperacionContrasenaBodyScreen(onContinuarClick)
}


@Composable
fun RecuperacionContrasenaBodyScreen(
    onContinuarClick: () -> Unit
) {
    val contrasena = remember { mutableStateOf("") }
    val repetirContrasena = remember { mutableStateOf("") }

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
            Image(
                painter = painterResource(resource = Res.drawable.password),
                contentDescription = "Verification Icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Asegúrese de que ambas contraseñas coincidan y que sea lo suficientemente segura para no ser atacada en el futuro.",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Input(
                value = contrasena.value,
                onValueChange = { contrasena.value = it },
                label = "Contraseña",
                borderRadius = 10,
                borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                borderSize = 2,
                textSize = 16,
                verticalPadding = 22,
                type = "Text",
                isPassword = true,
                isDisabled = false
            )

            Spacer(modifier = Modifier.height(16.dp))

            Input(
                value = repetirContrasena.value,
                onValueChange = { repetirContrasena.value = it },
                label = "Repetir Contraseña",
                borderRadius = 10,
                borderColor = Brush.horizontalGradient(listOf(MarronEnd, MoradoStart)),
                borderSize = 2,
                textSize = 16,
                verticalPadding = 22,
                type = "Text",
                isPassword = true,
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
                    text = "Continuar (3/4)",
                    color = ButtonBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun RecuperacionContrasenaScreenPreview() {
    RecuperacionContrasenaScreen(
        onContinuarClick = {}
    )
}

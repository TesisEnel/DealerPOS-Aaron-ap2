package org.aarondeveloper.dealerpos.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.ui.theme.SecondaryText
import org.jetbrains.compose.resources.painterResource

@Composable
fun Input(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    borderRadius: Int,
    borderColor: Brush,
    borderSize: Int,
    textSize: Int,
    verticalPadding: Int,
    type: String,
    isPassword: Boolean,
    isDisabled: Boolean
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .border(
                BorderStroke(borderSize.dp, borderColor),
                shape = RoundedCornerShape(borderRadius.dp)
            )
    ) {
        val visualTransformation = if (isPassword && !isPasswordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }

        val keyboardOptions = if (type == "Number") {
            KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        } else {
            KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        }

        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                if (!isDisabled) {
                    if (type == "Number" && newValue.all { it.isDigit() }) {
                        onValueChange(newValue)
                    } else if (type == "Text") {
                        onValueChange(newValue)
                    }
                }
            },
            textStyle = TextStyle(
                color = if (isDisabled) Color.Gray else SecondaryText,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            ),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            enabled = !isDisabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = verticalPadding.dp)
        )

        if (isPassword) {
            Image(
                painter = painterResource(if (isPasswordVisible) Res.drawable.show else Res.drawable.hide),
                contentDescription = if (isPasswordVisible) "Oculto" else "Mostrado",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .size(20.dp)
                    .clickable(enabled = !isDisabled) {
                        isPasswordVisible = !isPasswordVisible
                    }
            )
        }

        if (isDisabled) {
            Image(
                painter = painterResource(Res.drawable.lock),
                contentDescription = "Bloqueado",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .size(20.dp)
            )
        }

        AnimatedVisibility(
            visible = value.isEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = label,
                color = if (isDisabled) Color.Gray else Color.Gray,
                fontSize = textSize.sp,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}



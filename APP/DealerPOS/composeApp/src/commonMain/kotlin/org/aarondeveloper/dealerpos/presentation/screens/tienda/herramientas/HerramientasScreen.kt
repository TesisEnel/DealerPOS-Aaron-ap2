package org.aarondeveloper.dealerpos.presentation.screens.tienda.herramientas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.librery.Herramientas
import org.aarondeveloper.dealerpos.librery.herramientas
import org.aarondeveloper.dealerpos.ui.theme.MarronEnd
import org.aarondeveloper.dealerpos.ui.theme.MoradoStart
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun HerramientasScreen(

) {

    HerramientasBodyScreen(herramientas = herramientas)

}

@Composable
fun HerramientasBodyScreen(
    herramientas: List<Herramientas>,
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
                        .clickable { }
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
                        text = "Herramientas",
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
                        .clickable { }
                        .padding(8.dp)
                        .width(32.dp)
                        .height(32.dp)
                )
            }

            Text(
                text = "Navega y consulta nuestras herramientas",
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp, start = 20.dp, end = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                herramientas.forEach { herramienta ->
                    HerramientasItem(
                        iconoId = herramienta.icono,
                        nombre = herramienta.nombre,
                        onClick = {  }
                    )
                }
            }
        }
    }
}

@Composable
fun HerramientasItem(
    iconoId: DrawableResource,
    nombre: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(resource = iconoId),
                contentDescription = nombre,
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .padding(end = 16.dp)
            )

            Text(
                text = nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(resource = Res.drawable.direct_right),
                contentDescription = "Arrow",
                modifier = Modifier
                    .height(25.dp)
                    .width(25.dp)
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
fun HerramientasScreenPreview() {
    HerramientasScreen()
}

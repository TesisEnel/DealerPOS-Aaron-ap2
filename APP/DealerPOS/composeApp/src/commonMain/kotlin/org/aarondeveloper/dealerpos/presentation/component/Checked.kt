package org.aarondeveloper.dealerpos.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.librery.convertirBase64AImageBitmap
import org.aarondeveloper.dealerpos.ui.theme.MarronEnd
import org.aarondeveloper.dealerpos.ui.theme.MoradoStart
import org.jetbrains.compose.resources.painterResource

@Composable
fun Checked(
    imagen: String?,
    titulo: String,
    descripcion: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Transparent)
            .border(
                BorderStroke(2.dp, Brush.horizontalGradient(listOf(MoradoStart, MarronEnd))),
                RoundedCornerShape(10.dp)
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            val imageBitmap = imagen?.let { convertirBase64AImageBitmap(it) }

            val imageModifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(end = 6.dp)

            if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = titulo,
                    modifier = imageModifier,
                    contentScale = ContentScale.Fit
                )
            } else {
                Image(
                    painter = painterResource(resource = Res.drawable.product_default),
                    contentDescription = titulo,
                    modifier = imageModifier,
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = descripcion,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.align(Alignment.CenterVertically),
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    uncheckedColor = Color.LightGray
                )
            )
        }
    }
}




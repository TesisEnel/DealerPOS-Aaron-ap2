package org.aarondeveloper.dealerpos.presentation.screens.tienda.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dealerpos.composeapp.generated.resources.*
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.CategoriasDto
import org.aarondeveloper.dealerpos.data.remote.dto.dealerposapi.ProductosDto
import org.aarondeveloper.dealerpos.librery.convertirBase64AImageBitmap
import org.aarondeveloper.dealerpos.presentation.screens.tienda.PrincipalViewModel
import org.aarondeveloper.dealerpos.presentation.screens.tienda.UiState
import org.aarondeveloper.dealerpos.ui.theme.MarronEnd
import org.aarondeveloper.dealerpos.ui.theme.MoradoStart
import org.aarondeveloper.dealerpos.ui.theme.SecondaryText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    viewModel: PrincipalViewModel = koinViewModel(),
    onDetalleProducto: () -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeBodyScreen(
        uiState = uiState,
        onDetalleProducto = onDetalleProducto,
        onSeleccionarCategoria = viewModel::onSeleccionarCategoria,
        onSeleccionarProducto = viewModel::onSeleccionarProducto,
    )

}

@Composable
fun HomeBodyScreen(
    uiState: UiState,
    onDetalleProducto: () -> Unit,
    onSeleccionarCategoria: (Int) -> Unit,
    onSeleccionarProducto: (Int, () -> Unit) -> Unit,
){

    val categorias = uiState.categorias ?: emptyList()
    val productos = uiState.productos ?: emptyList()

    if (categorias.isEmpty() || productos.isEmpty()) {
        MensajePersonalizado(uiState = uiState)
    } else {
        VehiculoCategoria(uiState = uiState, onDetalleProducto = onDetalleProducto, categorias = categorias, productos = productos, onSeleccionarCategoria = onSeleccionarCategoria, onSeleccionarProducto = onSeleccionarProducto)
    }

}

@Composable
fun VehiculoCategoria(uiState: UiState, onDetalleProducto: () -> Unit, categorias: List<CategoriasDto>, productos: List<ProductosDto>, onSeleccionarCategoria: (Int) -> Unit, onSeleccionarProducto: (Int, () -> Unit) -> Unit) {

    Text(
        text = uiState.language!!.categorias,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.offset(x = 30.dp).padding(top = 15.dp)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, top = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categorias.size) { indice ->
                val categoria = categorias[indice]

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            onSeleccionarCategoria(categoria.categoriaId)
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color.Transparent, shape = CircleShape)
                            .border(
                                BorderStroke(2.dp, Brush.horizontalGradient(listOf(MoradoStart, MarronEnd))),
                                shape = CircleShape
                            )
                    ) {
                        val imagen = convertirBase64AImageBitmap(categoria.imagen)
                        if(imagen != null){
                            Image(
                                bitmap = imagen,
                                contentDescription = categoria.descripcion,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                            )
                        }
                        else{
                            Image(
                                painter = painterResource(resource = Res.drawable.product_default),
                                contentDescription = categoria.descripcion,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = categoria.descripcion,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        ProductosLista(productos = productos, onSeleccionarProducto = onSeleccionarProducto, onDetalleProducto = onDetalleProducto)
    }
}

@Composable
fun ProductosLista(productos: List<ProductosDto>, onSeleccionarProducto: (Int, () -> Unit) -> Unit, onDetalleProducto: () -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(bottom = 130.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        items(productos.size) { indice ->
            val producto = productos[indice]

            Card(
                shape = RoundedCornerShape(25.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                border = BorderStroke(2.dp, Brush.horizontalGradient(listOf(MoradoStart, MarronEnd))),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .clickable {
                        onSeleccionarProducto(producto.productoId, onDetalleProducto)
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .padding(16.dp)
                ) {
                    val imagen = convertirBase64AImageBitmap(producto.imagen)
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.background(Color.Transparent)
                    ) {
                        if (imagen != null) {
                            Image(
                                bitmap = imagen,
                                contentDescription = producto.nombre,
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(bottom = 16.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        } else {
                            Image(
                                painter = painterResource(resource = Res.drawable.product_default),
                                contentDescription = producto.nombre,
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(bottom = 16.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        }

                        Text(
                            text = producto.nombre,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "$${producto.precio}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun MensajePersonalizado(uiState: UiState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .offset(y = (-70).dp, x = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(Res.drawable.tienda),
                contentDescription = "",
                modifier = Modifier.size(250.dp)
            )
            Text(
                text = uiState.estadoBusqueda,
                color = SecondaryText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}



@Preview
@Composable
fun HomeScreenPreview() {

}

package com.example.stockpro.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stockpro.model.Producto
import com.example.stockpro.viewModel.StockViewModel
import androidx.navigation.NavController

@Composable
fun CatalogoScreen(
    nombreOperario: String,
    vm: StockViewModel,
    navController: NavController
) {

    var soloCriticos by remember {
        mutableStateOf(false)
    }

    val productosMostrar =
        if (soloCriticos)
            vm.obtenerProductosEnRiesgo()
        else
            vm.productos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Operario: $nombreOperario",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = {
                    soloCriticos = false
                }
            ) {
                Text("Ver Todo")
            }

            Button(
                onClick = {
                    soloCriticos = true
                }
            ) {
                Text("Stock Crítico")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(productosMostrar) { producto ->

                ProductoCard(
                    producto = producto,
                    onClick = {
                        navController.navigate(
                            "editar/${producto.id}"
                        )
                    }
                )

            }
        }
    }
}

@Composable
fun ProductoCard(
    producto: Producto,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                onClick()
            }
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Precio: $${producto.precio}"
            )

            Text(
                text = "Stock: ${producto.stockActual}",
                color =
                    if (producto.stockActual < 5)
                        Color.Red
                    else
                        Color.Black
            )
        }
    }
}

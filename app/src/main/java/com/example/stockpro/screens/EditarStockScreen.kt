package com.example.stockpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpro.viewModel.StockViewModel

@Composable
fun EditarStockScreen(
    productoId: Int,
    vm: StockViewModel,
    navController: NavController
) {

    val producto = vm.obtenerProducto(productoId)

    if (producto == null) {
        Text("Producto no encontrado")
        return
    }

    var stock by remember {
        mutableIntStateOf(producto.stockActual)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = producto.nombre,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = producto.descripcion
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Stock Actual: $stock",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                onClick = {
                    stock++
                }
            ) {
                Text("+1")
            }

            Button(
                onClick = {
                    stock--
                },
                enabled = stock > 0
            ) {
                Text("-1")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                vm.actualizarStock(
                    producto.id,
                    stock
                )

                navController.popBackStack()
            }
        ) {
            Text("Guardar y Volver")
        }
    }
}
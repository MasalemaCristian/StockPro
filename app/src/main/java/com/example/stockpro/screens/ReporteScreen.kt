package com.example.stockpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpro.viewModel.StockViewModel

@Composable
fun ReporteScreen(
    vm: StockViewModel,
    navController: NavController
) {

    val totalInventario =
        vm.calcularValorTotalInventario()

    val productosSinStock =
        vm.totalProductosSinStock()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Capital Invertido Total",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "$$totalInventario",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Productos sin stock: $productosSinStock",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Volver al Catálogo")
        }
    }
}


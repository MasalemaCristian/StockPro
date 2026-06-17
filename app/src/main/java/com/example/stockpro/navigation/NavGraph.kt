package com.example.stockpro.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockpro.screens.CatalogoScreen
import com.example.stockpro.screens.LoginScreen
import com.example.stockpro.viewModel.StockViewModel
import com.example.stockpro.screens.EditarStockScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    val stockViewModel: StockViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {

            LoginScreen { nombre ->

                navController.navigate(
                    "catalogo/$nombre"
                )

            }

        }

        composable(
            route = "editar/{id}"
        ) { backStackEntry ->

            val id =
                backStackEntry.arguments
                    ?.getString("id")
                    ?.toIntOrNull() ?: 0

            EditarStockScreen(
                productoId = id,
                vm = stockViewModel,
                navController = navController
            )
        }


        composable(
            route = "catalogo/{nombre}"
        ) { backStackEntry ->

            val nombre =
                backStackEntry.arguments?.getString("nombre")
                    ?: ""

            CatalogoScreen(
                nombreOperario = nombre,
                vm = stockViewModel,
                navController = navController
            )

        }
    }
}

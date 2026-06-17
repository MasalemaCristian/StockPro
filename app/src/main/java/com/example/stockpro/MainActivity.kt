package com.example.stockpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.stockpro.navigation.NavGraph
import com.example.stockpro.ui.theme.StockProTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            StockProTheme {

                NavGraph()

            }
        }
    }
}


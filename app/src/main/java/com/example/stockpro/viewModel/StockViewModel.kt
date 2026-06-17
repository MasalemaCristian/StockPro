package com.example.stockpro.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.stockpro.model.Producto

class StockViewModel : ViewModel() {

    val productos = mutableStateListOf(
        Producto(1, "Laptop HP", "Laptop empresarial", 850.0, 10),
        Producto(2, "Mouse Logitech", "Mouse inalámbrico", 25.0, 4),
        Producto(3, "Teclado Redragon", "Teclado mecánico", 50.0, 3),
        Producto(4, "Monitor Samsung", "Monitor 24 pulgadas", 180.0, 8),
        Producto(5, "Disco SSD", "SSD 1TB", 95.0, 0),
        Producto(6, "Impresora Epson", "Impresora multifunción", 220.0, 2)
    )

    fun obtenerProducto(id:Int): Producto?{
        return productos.find { it.id == id }
    }

    fun actualizarStock(id:Int,nuevaCantidad:Int){
        val index = productos.indexOfFirst { it.id == id }

        if(index != -1){
            productos[index] =
                productos[index].copy(stockActual = nuevaCantidad)
        }
    }

    fun calcularValorTotalInventario(): Double{
        return productos.sumOf {
            it.precio * it.stockActual
        }
    }

    fun obtenerProductosEnRiesgo(): List<Producto>{
        return productos.filter {
            it.stockActual < 5
        }
    }

    fun totalProductosSinStock(): Int{
        return productos.count {
            it.stockActual == 0
        }
    }


}

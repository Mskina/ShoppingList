package com.example.lista

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.concurrent.atomic.AtomicInteger

// Gardamos os elementos
class ShoppingInventory(
    label: String,
    initialChecked: Boolean = false
) {
    val id: Int = idProducto.incrementAndGet()
    var label: String by mutableStateOf(label)
    var checked: Boolean by mutableStateOf(initialChecked)

    companion object { // (1)
        private val idProducto: AtomicInteger = AtomicInteger(0)  // (2)
    }
}


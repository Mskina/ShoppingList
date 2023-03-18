package com.example.lista

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    private val _items = getItems().toMutableStateList()
    val items: List<ShoppingInventory>
        get() = _items

    fun remove(item: ShoppingInventory) {
        _items.remove(item)
    }

    fun add(item: ShoppingInventory) {
        _items.add(item)
    }

    fun updateItemLabel(item: ShoppingInventory, input: String) {
        items.find { it.id == item.id }?.let { item ->
            item.label = input
        }
    }

    fun changeItemChecked(
        item: ShoppingInventory,
        checked: Boolean
    ) =
        items.find { it.id == item.id }?.let { item ->
            item.checked = checked
        }

}

private fun getItems() = List(5) { i -> ShoppingInventory("Item $i") }


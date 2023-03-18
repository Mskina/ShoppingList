package com.example.lista

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count, { count++ }, modifier)
}

/*
@Composable
fun ItemCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {

        // Os cambios son monitorizados por Compose
        // Con remember facemos que se garde o valor e que non se reinicie en cada redibuxado
        // Con rememberSaveable gardamos ante cambios de configuración (por exemplo, xirar o dispositivo)
        var count by rememberSaveable { mutableStateOf(0) }

        if (count > 0) {
            var showItem by rememberSaveable { mutableStateOf(true) }
            if (showItem) {
                ShoppingListItem(
                    itemName = "Item engadido",
                    // Coa función lambda, cando pulsamos no X, agochamos o item
                    onClose = { showItem = false })
            }
            Text("You've bought $count items.")
        }

        Row(Modifier.padding(top = 8.dp)) {
            Button(
                onClick = { count++ },
                enabled = count < 10) {
                Text("Add item")
            }
            Button(
                onClick = { count = 0 },
                enabled = count > 0,
                modifier = Modifier.padding(start = 8.dp)) {
                Text("Clear item count")
            }
        }
    }
}*/

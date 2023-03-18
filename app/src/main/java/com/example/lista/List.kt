package com.example.lista

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun List(
    list: List<ShoppingInventory>,
    onValueChange: (ShoppingInventory, String) -> Unit,
    onCheckedItem: (ShoppingInventory, Boolean) -> Unit,
    onCloseItem: (ShoppingInventory) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyListState,
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
    ) {
        items(
            items = list,
            key = { item -> item.id }
        ) { item ->
            Item(
                itemName = item.label,
                onValueChange = {input -> onValueChange(item, input)},
                checked = item.checked,
                onCheckedChange = { checked -> onCheckedItem(item, checked) },
                onClose = { onCloseItem(item) },
                modifier = Modifier.animateItemPlacement(), // Este modifier debe ser nuevo para no arrastrar paddingValues
                // Alejandro no pasa modifier porque no hace animación.
            )
        }
        item { Spacer(modifier = Modifier.padding(25.dp)) }
    }
}


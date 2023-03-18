package com.example.lista

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp

@Composable
fun Item(
    itemName: String,
    onValueChange: (String) -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val requester = remember { FocusRequester() }

    Card(
        modifier = modifier.padding(16.dp, 6.dp, 16.dp, 6.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 3.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
//            Text(
//                text = itemName,
//                Modifier
//                    .weight(1f)
//                    .padding(start = 16.dp)
//            )
            BasicTextField(
                value = itemName,
                onValueChange = onValueChange,
                singleLine = true,
                //placeholder = { Text("Nombre del producto") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
                    .focusRequester(requester),
            )
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
            IconButton(onClick = onClose) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Close"
                )
            }
            LaunchedEffect(Unit) {
                requester.requestFocus()
            }
        }
    }

}
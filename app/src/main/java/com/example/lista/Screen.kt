package com.example.lista

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
@Preview
fun Screen() {
    val viewModel: ViewModel = viewModel()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar {
                Image(
                    painter = painterResource(id = R.drawable.baseline_shopping_basket_24),
                    contentDescription = "Image",
                    Modifier
                        .padding(10.dp)
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.primary),
                    alignment = Alignment.Center,
                )
                Text(
                    text = stringResource(R.string.appName),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.add(ShoppingInventory(label = ""))
                    coroutineScope.launch {
                        listState.animateScrollBy(value = 10000f,
                            animationSpec = tween(durationMillis = 5000))
                        //listState.animateScrollToItem(index = newID, 17)
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
            ) {
                Icon(Icons.Filled.Add, "Añadir elemento")
            }
        },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(
                // Defaults to null, that is, No cutout
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.appCreatorName), fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    ) { paddingValues ->
        List(
            list = viewModel.items,
            onValueChange = {item, input -> viewModel.updateItemLabel(item, input)},
            onCheckedItem = { item, checked ->
                viewModel.changeItemChecked(item, checked)
            },
            onCloseItem = { item ->
                viewModel.remove(item)
            },
            modifier = Modifier.padding(paddingValues),
            listState = listState,
        )
    }
}

package com.example.lista

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    val listState = rememberLazyListState() // #1
    val coroutineScope = rememberCoroutineScope() // #2

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
                        listState.animateScrollBy( // #2
                            value = 10000f,
                            animationSpec = tween(durationMillis = 5000) // #3
                        )
                        //listState.animateScrollToItem(viewModel.items.size, 20)
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
            onValueChange = { item, input -> viewModel.updateItemLabel(item, input) },
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

/**
 * #1
 * Controla la posición en la que estamos. Al girar la pantalla, continuamos en el mismo ítem
 * en el que estábamos.
 *
 * Fuente: https://developer.android.com/jetpack/compose/lists
 * Issue: https://issuetracker.google.com/issues/177245496
 * --> Hay un comportamiento "no deseado" (al menos por 152 personas) sobre este aspecto
 *
 * #2
 * No me quedó otra que usarlo porque es necesario para la animación
 * Both scrollToItem() and animateScrollToItem() are suspending functions,
 * which means that we need to invoke them in a coroutine.
 * See our coroutines documentation for more information on how to do that in Compose.
 * Fuente: https://developer.android.com/jetpack/compose/lists#control-scroll-position
 *
 * #3
 * Al añadir un elemento nuevo, la lista se desplace hasta el final.
 * Como no controlo en px el tamaño de cada caja, pongo un valor inventado que sea suficiente
 * para que siempre vaya al final. Con 'tween' ralentizo el efecto, para que no sea un desplazamiento
 * inmediato (5000 es claramente elevado, pero así compruebo que se efectúa)
 *
 * Alternativa: listState.animateScrollToItem() --> Nos permite ir directamente a un elemento
 * en concreto, PERO lo hace con "smooth scroll" (tan smooth que no lo veo). Es como la evolución
 * de scrollToItem(), que te lleva a un elemento en concreto sin animación.
 *
 * Idea: https://stackoverflow.com/questions/73137520/how-to-slow-down-animatescrolltoitem-in-jetpack-compose
 */



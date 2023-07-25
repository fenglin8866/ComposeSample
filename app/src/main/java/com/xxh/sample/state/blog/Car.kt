package com.xxh.sample.state.blog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel




@Composable
fun Cart(viewModel: CartViewModel = viewModel()) {
    LazyColumn {
        items(viewModel.cartItems) { item ->
            CartItem(item.quantity, incrementQuantity = {
                viewModel.incrementQuantity(item)
            }) {
                viewModel.decrementQuantity(item)
            }
        }

    }
}

/**
 * 状态提升
 */
@Composable
fun CartItem(
    quantity: Int,
    incrementQuantity: () -> Unit, // 事件
    decrementQuantity: () -> Unit // 事件
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = incrementQuantity) {
            Text("+")
        }

        Text(quantity.toString(), modifier = Modifier.padding(10.dp))

        Button(onClick = decrementQuantity) {
            Text("-")
        }
    }
}


@Composable
fun CartItem() {
    var quantity by remember {
        mutableIntStateOf(1)
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = { quantity++ }) {
            Text("+")
        }

        Text(quantity.toString(), modifier = Modifier.padding(10.dp))

        Button(onClick = { quantity-- }) {
            Text("-")
        }
    }
}
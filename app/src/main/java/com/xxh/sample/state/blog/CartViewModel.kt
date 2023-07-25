package com.xxh.sample.state.blog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    private val _cartItems = getCartData().toMutableStateList()

    val cartItems: List<CartItem>
        get() = _cartItems

    fun incrementQuantity(item: CartItem) {
        item.quantity++
    }

    fun decrementQuantity(item: CartItem) {
        item.quantity--
    }
}

fun getCartData() = MutableList(10) {
    CartItem(1)
}


class CartItem(value: Int){
    var quantity: Int by mutableIntStateOf(value)
}

package com.xxh.sample.state.blog

import android.content.res.Resources
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberCartState(
    lazyListState: LazyListState = rememberLazyListState(),
    resources: Resources = LocalContext.current.resources,
) = remember(lazyListState, resources) {
    CartState(lazyListState, resources)
}

class CartState(
    lazyListState: LazyListState,
    resources: Resources,
    expandedItems: List<CartItem> = emptyList()
) {
    //fun formatPrice(...) { ... }
}
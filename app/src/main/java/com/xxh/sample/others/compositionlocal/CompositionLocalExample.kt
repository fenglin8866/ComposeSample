package com.xxh.sample.others.compositionlocal

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.xxh.sample.R
import com.xxh.sample.common.component.XDivider


@Composable
fun CompositionLocalScreen() {
    Column {
        CompositionLocalExample()
        XDivider()
        FruitText(2)
        XDivider()
        //LocalElevationsSample()
        ReCompositionTest()
    }
}

/**
 * CompositionLocalProvider的使用
 */
@Composable
fun CompositionLocalExample() {
    MaterialTheme { // MaterialTheme sets ContentAlpha.high as default
        Column {
            Log.d("LocalElevationsSample", "CompositionLocalProvider 0")
            SideEffect {
                Log.d("LocalElevationsSample", "CompositionLocalProvider 0 SideEffect")
            }
            Text("Uses MaterialTheme's provided alpha")
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("Medium value provided for LocalContentAlpha")
                Text("This Text also uses the medium value")
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                    DescendantExample()
                }
            }
        }
    }
}

@Composable
fun DescendantExample() {
    // CompositionLocalProviders also work across composable functions
    Text("This Text uses the disabled alpha now")
}


/**
 * 常用LocalContext对象
 */
@Composable
fun FruitText(fruitSize: Int) {
    // Get `resources` from the current value of LocalContext
    val resources = LocalContext.current.resources
    val fruitText = remember(resources, fruitSize) {
        resources.getQuantityString(R.plurals.fruit_title, fruitSize)
    }
    Text(text = fruitText)
}

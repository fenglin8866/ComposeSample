package com.xxh.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ax(){
    var name by remember { mutableStateOf("") }
    val value= remember<List<String>> {
        mutableStateListOf()
    }
}

@Composable
fun WithConstraintsComposable() {
    BoxWithConstraints {
        Text("My minHeight is $minHeight while my maxWidth is $maxWidth")
    }
}

@Composable
fun MatchParentSizeComposable() {
    Box {
        Spacer(Modifier.matchParentSize().background(Color.LightGray))
       // ArtistCard()
    }
}
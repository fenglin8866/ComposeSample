package com.xxh.sample.basics

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BasicsScreen() {
   BasicNavGraph(rememberNavController())
}
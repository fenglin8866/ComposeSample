package com.xxh.sample.basics

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.common.ListScreenString
import com.xxh.sample.basics.BasicDestination.COMPOSITION_LOCAL
import com.xxh.sample.basics.BasicDestination.HOME_ROUTE
import com.xxh.sample.basics.compositionlocal.CompositionLocalScreen


object BasicDestination {
    const val HOME_ROUTE = "basic_home"
    const val COMPOSITION_LOCAL = "CompositionLocal"
}

val stateListData = listOf(COMPOSITION_LOCAL)

@Composable
fun BasicNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        composable(HOME_ROUTE) {
            ListScreenString(data = stateListData) {
                navController.navigate(it)
            }
        }
        composable(COMPOSITION_LOCAL) {
            CompositionLocalScreen()
        }
    }
}
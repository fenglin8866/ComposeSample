package com.xxh.sample.state

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.common.ListScreenString
import com.xxh.sample.state.StateDestination.BLOG_ROUTE
import com.xxh.sample.state.StateDestination.CODELAB_ROUTE
import com.xxh.sample.state.StateDestination.DEV_ROUTE
import com.xxh.sample.state.StateDestination.HOME_ROUTE
import com.xxh.sample.state.basic.StateBasicScreen
import com.xxh.sample.state.basic.ConversationScreen
import com.xxh.sample.state.codelabs.WellnessScreen2


object StateDestination {
    const val HOME_ROUTE = "state_home"
    const val DEV_ROUTE = "state_dev"
    const val CODELAB_ROUTE = "state_codelab"
    const val BLOG_ROUTE = "state_blog"

}

val stateListData = listOf(DEV_ROUTE, CODELAB_ROUTE, BLOG_ROUTE)

@Composable
fun StateNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        composable(HOME_ROUTE) {
            ListScreenString(data = stateListData) {
                Log.i("xxh714", "data= $it controller= $navController")
                navController.navigate(it)
            }
        }
        composable(DEV_ROUTE) {
            StateBasicScreen()
        }
        composable(CODELAB_ROUTE) {
            WellnessScreen2()
        }
        composable(BLOG_ROUTE) {
            ConversationScreen()
        }
    }
}
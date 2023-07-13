package com.xxh.sample.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.main.navigateSingleTopTo
import com.main.ui.theme.SampleAppTheme
import com.xxh.sample.state2.test1.ConversationScreen
import com.xxh.sample.ui.TestDestination.HOME_ROUTE
import com.xxh.sample.ui.TestDestination.STATE_ROUTE


object TestDestination {
    const val HOME_ROUTE = "test_home"
    const val STATE_ROUTE = "state"
}

@Composable
fun TestDemoApp(showBottomEvent:(Boolean)->Unit) {
    SampleAppTheme{
        TestGraph(showBottomEvent)
    }
}

@Composable
fun TestGraph(showBottomEvent:(Boolean)->Unit,navController: NavHostController=rememberNavController()) {
    NavHost(navController = navController, startDestination = HOME_ROUTE){
        composable(HOME_ROUTE) {
            TestMainScreen {
                navController.navigateSingleTopTo(it)
            }
        }
        /*todo 二级页面，怎么隐藏底部状态栏
        *  1.公用首页NavGraph，导航时当不在底部栏的route时隐藏
        *  2.不是同一个导航图，使用顶层共享变量来控制
        * */
        composable(STATE_ROUTE) {
            ConversationScreen(showBottomEvent)
        }
    }
}
package com.xxh.sample

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.main.navigateSingleTopTo
import com.main.ui.theme.SampleAppTheme
import com.xxh.sample.TestDestination.ANIMATION_ROUTE
import com.xxh.sample.TestDestination.BASICS_ROUTE
import com.xxh.sample.TestDestination.COMPONENT_ROUTE
import com.xxh.sample.TestDestination.GRAPHICS_ROUTE
import com.xxh.sample.TestDestination.HOME_ROUTE
import com.xxh.sample.TestDestination.IMAGES_ROUTE
import com.xxh.sample.TestDestination.LAYOUT_ROUTE
import com.xxh.sample.TestDestination.LIFECYCLE_ROUTE
import com.xxh.sample.TestDestination.NAVIGATION_ROUTE
import com.xxh.sample.TestDestination.PROPERTY_ROUTE
import com.xxh.sample.TestDestination.STATE_ROUTE
import com.xxh.sample.TestDestination.THEMING_ROUTE
import com.xxh.sample.state.blog.ConversationScreen
import com.xxh.sample.animation.AnimScreen
import com.xxh.sample.basics.BasicsScreen
import com.xxh.sample.component.ComponentScreen
import com.xxh.sample.graphics.GraphicsScreen
import com.xxh.sample.images.ImagesScreen
import com.xxh.sample.layout.LayoutScreen
import com.xxh.sample.lifecycle.LifecycleScreen
import com.xxh.sample.navigation.NavigationScreen
import com.xxh.sample.property.PropertyScreen
import com.xxh.sample.state.StateScreen
import com.xxh.sample.theme.ThemeScreen

/*todo 二级页面，怎么隐藏底部状态栏
*  1.公用首页NavGraph，导航时当不在底部栏的route时隐藏
*  2.不是同一个导航图，使用顶层共享变量来控制
*  3.不使用Bottombar,页面布局跳转
* */
object TestDestination {
    const val HOME_ROUTE = "test_home"
    const val BASICS_ROUTE = "basics"
    const val LIFECYCLE_ROUTE = "lifecycle"
    const val STATE_ROUTE = "state"
    const val LAYOUT_ROUTE = "layout"
    const val COMPONENT_ROUTE = "component"
    const val PROPERTY_ROUTE = "property"
    const val NAVIGATION_ROUTE = "navigation"
    const val THEMING_ROUTE = "theming"
    const val IMAGES_ROUTE = "images"
    const val GRAPHICS_ROUTE = "graphics"
    const val ANIMATION_ROUTE = "animation"
}

val testData = listOf(
    BASICS_ROUTE, LIFECYCLE_ROUTE, STATE_ROUTE, LAYOUT_ROUTE, COMPONENT_ROUTE,
    PROPERTY_ROUTE, NAVIGATION_ROUTE, THEMING_ROUTE, IMAGES_ROUTE, GRAPHICS_ROUTE, ANIMATION_ROUTE
)

/**
 * 1.公用首页NavGraph，导航时当不在底部栏的route时隐藏  推荐使用
 *
 * 不用navController，如果需要使用navController的方法，提升到顶层的state容器
 */
fun NavGraphBuilder.testGraph() {
    // navigation(startDestination = BASICS_ROUTE, route = HOME_ROUTE) {
    composable(BASICS_ROUTE) {
        BasicsScreen()
    }
    composable(LIFECYCLE_ROUTE) {
        LifecycleScreen()
    }
    composable(STATE_ROUTE) {
        StateScreen()
    }
    composable(LAYOUT_ROUTE) {
        LayoutScreen()
    }
    composable(COMPONENT_ROUTE) {
        ComponentScreen()
    }
    composable(PROPERTY_ROUTE) {
        PropertyScreen()
    }
    composable(NAVIGATION_ROUTE) {
        NavigationScreen()
    }
    composable(THEMING_ROUTE) {
        ThemeScreen()
    }
    composable(IMAGES_ROUTE) {
        ImagesScreen()
    }
    composable(GRAPHICS_ROUTE) {
        GraphicsScreen()
    }
    composable(ANIMATION_ROUTE) {
        AnimScreen()
    }
    // }
}


/**
 * 2.不是同一个导航图，使用顶层共享变量来控制
 * 使用副效应，当进入组合时，变量隐藏，退出组合时，变量显示
 */
@Composable
fun TestDemoApp(showBottomEvent: (Boolean) -> Unit) {
    SampleAppTheme {
        TestGraph(showBottomEvent)
    }
}

@Composable
fun TestGraph(
    showBottomEvent: (Boolean) -> Unit,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        composable(HOME_ROUTE) {
            TestMainScreen(testData) {
                navController.navigateSingleTopTo(it)
            }
        }

        composable(STATE_ROUTE) {
            ConversationScreen()
        }
    }
}
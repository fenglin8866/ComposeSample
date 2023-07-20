package com.main

import android.content.Context
import android.content.Intent
import androidx.compose.material.catalog.CatalogActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codelabs.animation.AnimationActivity
import com.codelabs.basiclayouts.BasicLayoutsActivity
import com.codelabs.basics.BasicActivity
import com.codelabs.state.StateActivity
import com.codelabs.theming.ui.start.ThemeStartActivity
import com.example.compose.jetchat.ChatActivity
import com.example.compose.jetsurvey.SurveyActivity
import com.example.crane.home.CraneActivity
import com.example.jetcaster.ui.CasterActivity
import com.example.jetlagged.LaggedActivity
import com.example.jetnews.ui.JetnewsActivity
import com.example.jetsnack.ui.SnackActivity
import com.example.owl.ui.OwlActivity
import com.example.rally.RallyActivity
import com.example.reply.ui.ReplyActivity

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        AppState(navController)
    }

/**
 * 一般状态容器：界面元素状态提升 + 封装界面逻辑
 */
@Stable
class AppState(val navController: NavHostController) {

    private val bottomRoute = bottomScreens.map { it.route }

    /**
     * 当前导航路径，没有封装为状态，不会触发重组
     */
     val currentRoute: String?
        get() = navController.currentDestination?.route

    private val currentRouteAsState
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route

    val isShowBottomBar
        @Composable get() = currentRouteAsState in bottomRoute

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigateSingleTopTo(route)
        }
    }

    fun clickDemo(route: String) {
        navController.navigate(route)
    }

    //todo context参数是否提升到AppState内
    fun clickCodelabs(content: String, context: Context) {
        var intent: Intent? = null
        when (content) {
            "State" -> intent = Intent(context, StateActivity::class.java)
            "JetNew" -> intent = Intent(context, JetnewsActivity::class.java)
            "Reply" -> intent = Intent(context, ReplyActivity::class.java)
            "Animation" -> intent = Intent(context, AnimationActivity::class.java)
            "Rally" -> intent = Intent(context, RallyActivity::class.java)
            "themeJetNew" -> intent = Intent(context, ThemeStartActivity::class.java)
            "BasicLayouts" -> intent = Intent(context, BasicLayoutsActivity::class.java)
            "Basic" -> intent = Intent(context, BasicActivity::class.java)
            "Crane" -> intent = Intent(context, CraneActivity::class.java)
        }
        intent?.let {
            ContextCompat.startActivity(context, intent, null)
        }
    }

    fun clickSample(title: String, context: Context) {
        var intent: Intent? = null
        when (title) {
            "JetSnack" -> intent = Intent(context, SnackActivity::class.java)
            "JetChat" -> intent = Intent(context, ChatActivity::class.java)
            "JetSurvey" -> intent = Intent(context, SurveyActivity::class.java)
            "JetLagged" -> intent = Intent(context, LaggedActivity::class.java)
            "JetCaster" -> intent = Intent(context, CasterActivity::class.java)
            "Owl" -> intent = Intent(context, OwlActivity::class.java)
            "Catalog" -> intent = Intent(context, CatalogActivity::class.java)
        }
        intent?.let {
            ContextCompat.startActivity(context, intent, null)
        }
    }
}
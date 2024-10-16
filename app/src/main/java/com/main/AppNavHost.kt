package com.main

import android.content.Context
import android.content.Intent
import androidx.compose.material.catalog.CatalogActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HomeMax
import androidx.compose.material.icons.filled.HomeMini
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codelabs.animation.AnimationActivity
import com.codelabs.basiclayouts.BasicLayoutsActivity
import com.codelabs.basics.BasicActivity
import com.codelabs.state.StateActivity
import com.codelabs.theming.ui.start.ThemeStartActivity
import com.common.ListScreen
import com.common.ListScreenMsg
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
import com.main.data.HomeData
import com.xxh.sample.TestMainScreen
import com.xxh.sample.testData
import com.xxh.sample.testGraph

interface MainDestinations {
    val icon: ImageVector
    val route: String
}

object Demo : MainDestinations {
    override val icon = Icons.Filled.Home
    override val route = "demo"
}

object Codelabs : MainDestinations {
    override val icon = Icons.Filled.HomeMax
    override val route = "codelabs"
}

object Samples : MainDestinations {
    override val icon = Icons.Filled.HomeMini
    override val route = "samples"
}

val bottomScreens = listOf(Demo, Codelabs, Samples)

/**
 * 导航Host，导航图，导航的定义
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier,
    clickDemo: (String) -> Unit,
    clickCodelabs: (String, Context) -> Unit,
    clickSample: (String, Context) -> Unit
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = "demo",
        modifier = modifier
    ) {
        composable(Codelabs.route) {
            ListScreenMsg(data = HomeData.codelabsData) {
                clickCodelabs(it, context)
            }
        }

        composable(Samples.route) {
            ListScreen(data = HomeData.samplesData,
                trans = { return@ListScreen it.title }) {
                clickSample(it, context)
            }
        }

        composable(Demo.route) {
            /*ListScreen(data = testData,trans = { return@ListScreen it }) {
                clickDemo(it)
            }*/
            TestMainScreen(testData, onClickAction = clickDemo)
            /* //NavHost适合跳转，不适合tab切换
             TestDemoApp(showBottomEvent)*/
        }

        testGraph()

    }
}

/**
 * 提升state之前的逻辑
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = "demo",
        modifier = modifier
    ) {
        composable(Codelabs.route) {
            ListScreenMsg(data = HomeData.codelabsData) {
                var intent: Intent? = null
                when (it) {
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
        }

        composable(Samples.route) {
            ListScreen(data = HomeData.samplesData, { return@ListScreen it.title }) {
                var intent2: Intent? = null
                when (it) {
                    "JetSnack" -> intent2 = Intent(context, SnackActivity::class.java)
                    "JetChat" -> intent2 = Intent(context, ChatActivity::class.java)
                    "JetSurvey" -> intent2 = Intent(context, SurveyActivity::class.java)
                    "JetLagged" -> intent2 = Intent(context, LaggedActivity::class.java)
                    "JetCaster" -> intent2 = Intent(context, CasterActivity::class.java)
                    "Owl" -> intent2 = Intent(context, OwlActivity::class.java)
                    "Catalog" -> intent2 = Intent(context, CatalogActivity::class.java)
                }
                intent2?.let {
                    ContextCompat.startActivity(context, intent2, null)
                }
            }
        }

        composable(Demo.route) {
            TestMainScreen(testData) {
                navController.navigate(it)
            }
            /* //NavHost适合跳转，不适合tab切换
             TestDemoApp(showBottomEvent)*/
        }

        testGraph()

    }
}




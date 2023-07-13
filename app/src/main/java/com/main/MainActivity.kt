package com.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.main.ui.theme.SampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
   /* var isShowBottomBar by remember {
        mutableStateOf(true)
    }*/
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    //控制底部栏的隐藏和显示
    val isShowBottomBar: Boolean=currentDestination?.route in (bottomScreens.map { it.route })

    Scaffold(bottomBar = {
        if (isShowBottomBar) {
            BottomNavigation(backgroundColor = MaterialTheme.colorScheme.surface) {
                bottomScreens.forEach {
                    BottomNavigationItem(
                        icon = {
                            Icon(it.icon, contentDescription = null)
                        },
                        label = { Text(it.route) },
                        selected = currentDestination?.route == it.route,
                        onClick = {
                            navController.navigateSingleTopTo(it.route)
                        })
                }

            }
        }
    }) { innerPadding ->
        AppNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}






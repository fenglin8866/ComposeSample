/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.rally

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rally.ui.components.RallyTabRow
import com.example.rally.ui.theme.RallyTheme

/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
class RallyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Composable
fun RallyApp() {
    RallyTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination

        /*问题点：只有点击有效，相当state是与点击绑定，navigation返回不响应
        var currentScreen:RallyDestination by remember {
            mutableStateOf(Overview)
        }*/
        HomeBottomBar(navController = navController, currentDestination = currentDestination)
    }
}

/*
@Composable
fun RallyAppStart() {
    RallyTheme {
        var currentScreen: RallyDestination by remember { mutableStateOf(Overview) }
        Scaffold(
            topBar = {
                RallyTabRow(
                    allScreens = rallyTabRowScreens,
                    onTabSelected = { screen -> currentScreen = screen },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                currentScreen.screen()
            }
        }
    }
}
*/

@Composable
fun HomeTopBar(navController: NavHostController, currentDestination: NavDestination?) {
    Scaffold(
        topBar = {
            RallyTabRow(
                allScreens = rallyTabRowScreens,
                onTabSelected = { screen ->
                    // currentScreen = screen
                    navController.navigateSingleTopTo(screen.route)
                },
                currentScreen = rallyTabRowScreens.find { it.route == currentDestination?.route } ?: Overview
            )
        }
    ) { innerPadding ->
        RallyNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun HomeBottomBar(navController: NavHostController, currentDestination: NavDestination?) {
    Scaffold(
        bottomBar = {
            BottomNavigation {
                rallyTabRowScreens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.route) },
                        selected = screen.route == currentDestination?.route,
                        /**
                         * 提供导航目标层次结构的序列。层次结构从此目标本身开始，然后是此目标的 NavDestination.parent，然后是该图形的父级，然后是层次结构，直到到达根导航图。
                         */
                        /*selected = currentDestination?.hierarchy?.any {
                            Log.i("xxh", "selected ${it.route}")
                            it.route == screen.route
                        } ?: false,*/
                        //selected = currentDestination?.hierarchy?.any { it.route == screen.route }==true,
                        onClick = {
                            navController.navigateSingleTopTo(screen.route)
                        })
                }
            }
        }
    ) { innerPadding ->
        RallyNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}







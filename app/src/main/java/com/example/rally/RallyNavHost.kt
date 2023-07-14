package com.example.rally

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.rally.ui.accounts.AccountsScreen
import com.example.rally.ui.accounts.SingleAccountScreen
import com.example.rally.ui.bills.BillsScreen
import com.example.rally.ui.overview.OverviewScreen

@Composable
fun RallyNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {
        composable(Overview.route) {
            Log.i("xxh714","route=$Overview.route")
            OverviewScreen(onClickSeeAllAccounts = {
                navController.navigateSingleTopTo(Accounts.route)
            }, onClickSeeAllBills = {
                navController.navigateSingleTopTo(Bills.route)
            }) { arg ->
                navController.navigateToSingleAccount(arg)
            }
        }
        addHomeGraph(navController)

        composable(
            SingleAccount.routeWithArg,
            arguments = SingleAccount.arguments
        ) {
            SingleAccountScreen(it.arguments?.getString(SingleAccount.accountTypeArg))
        }
    }
}

fun NavGraphBuilder.addHomeGraph(navController: NavHostController) {
    navigation(startDestination = Overview.route, route = "home") {

        composable(Accounts.route) {
            Log.i("xxh714","route=$Accounts.route")
            AccountsScreen {
                navController.navigateToSingleAccount(it)
            }
        }
        composable(Bills.route) {
            Log.i("xxh714","route=$Bills.route")
            BillsScreen()
        }
    }
}


fun NavHostController.navigateSingleTopTo(route: String) {
    navigate(route) {
        //获取栈中第一个NavDestination
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun NavHostController.navigateToSingleAccount(arg: String) {
    navigateSingleTopTo("${SingleAccount.route}/$arg")
}

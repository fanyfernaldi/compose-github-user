package com.example.composegithubuser

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composegithubuser.core.domain.model.GithubUser
import com.example.composegithubuser.ui.navigation.Screen
import com.example.composegithubuser.ui.screen.detail.DetailScreen
import com.example.composegithubuser.ui.screen.home.HomeScreen
import com.example.composegithubuser.ui.screen.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubUserApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { username ->
                        navController.navigate(Screen.Detail.createRoute(username))
                    },
                    navigateToProfile = {
                        navController.navigate(Screen.Profile.route)
                    }
                )
            }
            composable(route = Screen.Detail.route) {
                val username = it.arguments?.getString("username") ?: ""
                DetailScreen(
                    githubUsername = username,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(route = Screen.Profile.route) {
                ProfileScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}

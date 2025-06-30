package com.devup.tarefa.ui.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devup.tarefa.ui.screens.home.HomeScreen
import com.devup.tarefa.ui.screens.login.LoginScreen
import com.devup.tarefa.ui.screens.login.LoginViewModel
import com.devup.tarefa.ui.screens.register.RegisterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "login") {
        composable("register") {
            RegisterScreen(navController = navController)
        }
        composable("home") {
            HomeScreen()
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
    }
}


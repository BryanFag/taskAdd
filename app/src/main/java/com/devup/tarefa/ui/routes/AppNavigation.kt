package com.devup.tarefa.ui.routes

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devup.tarefa.ui.screens.home.HomeScreen
import com.devup.tarefa.ui.screens.login.LoginScreen
import com.devup.tarefa.ui.screens.register.RegisterScreen
import com.devup.tarefa.ui.screens.register.UserViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val context = LocalContext.current
    val application = context.applicationContext as Application

    val userViewModel: UserViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(application)
    )

    NavHost(navController = navController, startDestination = "login") {
        composable("register") {
            RegisterScreen(
                navController = navController,
                viewModel = userViewModel
            )
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(
                navController = navController,
                viewModel = userViewModel
            )
        }
    }
}

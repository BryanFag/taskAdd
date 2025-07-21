package com.devup.tarefa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.devup.tarefa.ui.routes.AppNavigation
import com.devup.tarefa.ui.screens.home.HomeScreen
import com.devup.tarefa.ui.screens.login.LoginScreen
import com.devup.tarefa.ui.theme.NumberPickerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumberPickerTheme {
                AppNavigation()
//                HomeScreen()
            }
        }
    }
}
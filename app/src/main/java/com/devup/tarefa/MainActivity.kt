package com.devup.tarefa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.devup.tarefa.ui.routes.AppNavigation
import com.devup.tarefa.ui.theme.NumberPickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumberPickerTheme {
                AppNavigation()
            }
        }
    }
}


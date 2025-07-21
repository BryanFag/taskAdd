package com.devup.tarefa.ui.screens.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devup.tarefa.R

@Composable
fun StatusInput(
    statusFinish: Int,
    onStatusChange: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val statusOptions = listOf(
        0 to Triple(R.drawable.not_check_icon, "Pendente", Color.Gray),
        1 to Triple(R.drawable.in_progress_icon, "Em Progresso", Color.Yellow),
        2 to Triple(R.drawable.check_icon, "Concluído", Color.Green),
        3 to Triple(R.drawable.cancelled_icon, "Cancelado", Color.Red)
    )

    val selectedOption = statusOptions.find { it.first == statusFinish }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .clickable { expanded = true }
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            selectedOption?.let { (_, iconTextColor) ->
                val (iconId, text, color) = iconTextColor
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = text,
                    tint = color,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = text,
                    color = color
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFF232323))
        ) {
            statusOptions.forEach { (status, iconTextColor) ->
                val (iconId, text, color) = iconTextColor

                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = iconId),
                                contentDescription = text,
                                tint = color,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = text,
                                color = color
                            )
                        }
                    },
                    onClick = {
                        onStatusChange(status)
                        expanded = false
                    }
                )
            }
        }
    }
}


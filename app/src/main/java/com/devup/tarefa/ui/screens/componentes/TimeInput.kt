package com.devup.tarefa.ui.screens.componentes

import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devup.tarefa.R
import java.util.*

@Composable
fun TimeInput(
    timeFinish: String,
    onTimeSelected: (String) -> Unit
) {
    var showTimePicker by remember { mutableStateOf(false) }
    val calendar = Calendar.getInstance()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(60.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .clickable { showTimePicker = true },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.time_icon),
                contentDescription = "Ícone de hora",
                tint = Color.White,
                modifier = Modifier.padding(start = 16.dp, end = 18.dp)
            )
            Column {
                Text(
                    text = if (timeFinish.isNotEmpty()) timeFinish else "Selecione a hora",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }

    if (showTimePicker) {
        TimePickerDialog(
            context,
            { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                onTimeSelected(formattedTime)
                showTimePicker = false
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).apply {
            setOnCancelListener { showTimePicker = false }
        }.show()
    }
}

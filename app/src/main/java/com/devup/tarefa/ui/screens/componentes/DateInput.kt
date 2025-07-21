package com.devup.tarefa.ui.screens.componentes

import android.app.DatePickerDialog
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devup.tarefa.R
import java.util.Calendar

@Composable
fun DateInput(
    dateFinish: String,
    onDateSelected: (String) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val calendar = Calendar.getInstance()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(60.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .clickable { showDatePicker = true },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.date_icon),
                contentDescription = "Ícone de data",
                tint = Color.White,
                modifier = Modifier.padding(start = 16.dp, end = 18.dp)
            )
            Column {
                Text(
                    text = if (dateFinish.isNotEmpty()) dateFinish else "Selecione a data",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                val formattedMonth = (month + 1).toString().padStart(2, '0')
                val formattedDay = day.toString().padStart(2, '0')
                onDateSelected("$formattedDay/$formattedMonth/$year")
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            setOnCancelListener { showDatePicker = false }
        }.show()
    }
}

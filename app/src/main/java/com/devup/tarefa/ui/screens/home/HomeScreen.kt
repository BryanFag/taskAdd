package com.devup.tarefa.ui.screens.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.devup.tarefa.R
import com.devup.tarefa.data.entity.TaskEntity
import com.devup.tarefa.data.singleton.UserSingleton
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.devup.tarefa.ui.screens.componentes.DateInput
import com.devup.tarefa.ui.screens.componentes.DescriptionInput
import com.devup.tarefa.ui.screens.componentes.StatusInput
import com.devup.tarefa.ui.screens.componentes.TimeInput
import java.util.Calendar

@Composable
fun HomeScreen(
) {
    var showDialog     by remember { mutableStateOf(false) }
    var description    by remember { mutableStateOf("") }
    var dateFinish     by remember { mutableStateOf("") }
    var timeFinish     by remember { mutableStateOf("") }
    var statusFinish   by remember { mutableStateOf(0) } // 0: Pendente, 1: Em Progresso, 2: Concluído, 3: Cancelado
    var selectedTask   by remember { mutableStateOf<TaskEntity?>(null) }

    var newDescription by remember { mutableStateOf("") }
    var newDateFinish by remember { mutableStateOf("") }
    var newTimeFinish by remember { mutableStateOf("") }

    val isPreview          = LocalInspectionMode.current
    val context            = LocalContext.current

    val onDismiss: () -> Unit    = { showDialog = false }

    val homeViewModel: HomeViewModel = hiltViewModel()
    val tasks by homeViewModel.tasks.collectAsState(initial = emptyList())


////    // Mock data for tasks
//    val tasks = "teste".let {
//        listOf(
//            TaskEntity(1, 1, "Task 1", "teste", 100, "10:00", "10:00"),
//            TaskEntity(2, 1, "Task 2", "teste", 100, "11:00", "10:00"),
//            TaskEntity(3, 1, "Task 3", "teste", 100, "12:00", "10:00")
//        )
//    }


    Box(
        modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = (Color(0xFF232323))),
        ) {
            /**
             * Header
             */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp, start = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_task),
                    contentDescription = "Ícone de exemplo",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "Tarefas",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = "Ícone de favorito",
                    tint = Color.White,
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                thickness = 3.dp,
                color = Color(0xFFBEF264)
            )


            /**
             * Perfil Section
             */
            Row(
                modifier = Modifier
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.Black)
                        .border(2.dp, Color.Gray, CircleShape)
                ) {
                    UserSingleton.picture?.let { pictureBytes ->
                        if (pictureBytes.isNotEmpty()) {
                            val bitmap =
                                ConverterUriToByteArray.decodeByteArrayWithCorrectOrientation(
                                    pictureBytes
                                )
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "Foto do usuário",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                            )
                        }
                    }
                }
                    Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Olá, ",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                )
                Text(
                    modifier = Modifier,
                    text = (UserSingleton.name ?: "Usuário").lowercase().replaceFirstChar { it.uppercase() },
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DescriptionInput(description = newDescription, onDescriptionChange = { newDescription = it })

                Spacer(modifier = Modifier.padding(top = 5.dp))

                DateInput(dateFinish = newDateFinish, onDateSelected = { newDateFinish = it })

                Spacer(modifier = Modifier.padding(top = 5.dp))

                TimeInput(timeFinish = newTimeFinish, onTimeSelected = { newTimeFinish = it })


                Spacer(modifier = Modifier.padding(top = 10.dp))

                Button(
                    onClick = {
                        val task = TaskEntity(
                            userId = UserSingleton.id,
                            name = UserSingleton.name,
                            description = newDescription,
                            insertDate = System.currentTimeMillis(),
                            dateFinish = newDateFinish,
                            timeFinish = newTimeFinish,
                            status = 0
                        )
                        if ( newDescription != "" && newDateFinish != "" && newTimeFinish != ""){
                            homeViewModel.insertTask(task)
                            newDescription = ""
                            newDateFinish = ""
                            newTimeFinish = ""
                        } else { Toast.makeText(context,"Usuário ou senha inválidos!",Toast.LENGTH_SHORT).show() }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBEF264)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Salvar atividade",
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                }
            }

            /**
             * Perfil Section
             */
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "Atividades",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                )
                LazyColumn {
                    items(tasks) { task ->
                        Button(
                            onClick = {
                                selectedTask = task
                                description = task.description
                                dateFinish = task.dateFinish
                                timeFinish = task.timeFinish
                                statusFinish = task.status
                                showDialog = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 2.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF18181B),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val taskStatus = when (task.status) {
                                    0 -> R.drawable.not_check_icon
                                    1 -> R.drawable.in_progress_icon
                                    2 -> R.drawable.check_icon
                                    else -> R.drawable.cancelled_icon
                                }
                                Icon(
                                    painter = painterResource(
                                        id = taskStatus
                                    ),
                                    contentDescription = "Ícone de tarefa",
                                    tint = when (task.status) {
                                        0 -> Color.Gray
                                        1 -> Color.Yellow
                                        2 -> Color.Green
                                        else -> Color.Red
                                    },
                                    modifier = Modifier
                                        .padding(end = 12.dp)
                                        .size(20.dp)
                                )
                                Text(
                                    text = task.description,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Column(
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        text = task.dateFinish,
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Light,
                                    )
                                    Text(
                                        text = task.timeFinish,
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Light,
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (showDialog) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.9f))
                        .then(if (!isPreview) Modifier.blur(200.dp) else Modifier)
                )

                Dialog(onDismissRequest = onDismiss) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFF18181B))
                            .padding(24.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Alterar Atividade",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            DescriptionInput(description = description,
                                onDescriptionChange = { description = it }
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            DateInput(
                                dateFinish = dateFinish,
                                onDateSelected = { dateFinish = it }
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            TimeInput(timeFinish = timeFinish,
                                onTimeSelected = { timeFinish = it }
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            StatusInput(
                                statusFinish = statusFinish,
                                onStatusChange = { statusFinish = it }
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    val deleteTask = selectedTask
                                    if (deleteTask != null) { homeViewModel.deleteTask(deleteTask) }
                                    onDismiss()
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                elevation = null,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Excluir atividade",
                                    color = Color.Gray,
                                    textDecoration = TextDecoration.Underline
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    val updateTask = TaskEntity(
                                        id = selectedTask?.id ?: 0,
                                        userId = UserSingleton.id,
                                        name = UserSingleton.name,
                                        description = description,
                                        insertDate = selectedTask?.insertDate ?: System.currentTimeMillis(),
                                        dateFinish = dateFinish,
                                        timeFinish = timeFinish,
                                        status = statusFinish
                                    )
                                    homeViewModel.updateTask(listOf(updateTask))
                                    onDismiss()
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFFBEF264
                                    )
                                )
                            ) {
                                Text("Salvar atividade", color = Color.Black)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    HomeScreen()
}
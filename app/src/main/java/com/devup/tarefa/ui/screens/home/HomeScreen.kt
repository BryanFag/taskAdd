package com.devup.tarefa.ui.screens.home

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.devup.tarefa.R
import com.devup.tarefa.data.entity.TaskEntity
import com.devup.tarefa.data.singleton.UserSingleton
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asImageBitmap

@Composable
fun HomeScreen(
) {
    var showDialog   by remember { mutableStateOf(false) }
    var description  by remember { mutableStateOf("") }
    var dateFinish   by remember { mutableStateOf("") }
    var timeFinish   by remember { mutableStateOf("") }
    var selectedTask by remember { mutableStateOf<TaskEntity?>(null) }

    val focusManager       = LocalFocusManager.current
    val isPreview          = LocalInspectionMode.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val homeViewModel: HomeViewModel = hiltViewModel()
    val onDismiss    : () -> Unit    = { showDialog = false }
    val tasks by homeViewModel.tasks.collectAsState(initial = emptyList())



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
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.tag_icon),
                            contentDescription = "Ícone de edição",
                            tint = Color.Gray
                        )
                    },
                    label = {
                        Text(
                            "Qual a atividade?",
                            color = Color.White
                        )
                    },
                    placeholder = { Text("Digite aqui...") },
                    textStyle = TextStyle(color = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
                OutlinedTextField(
                    value = dateFinish,
                    onValueChange = { dateFinish = it },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.date_icon),
                            contentDescription = "Ícone de edição",
                            tint = Color.Gray
                        )
                    },
                    label = {
                        Text(
                            "Data de término",
                            color = Color.White
                        )
                    },
                    placeholder = { Text("Digite aqui...") },
                    textStyle = TextStyle(color = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )

                )
                OutlinedTextField(
                    value = timeFinish,
                    onValueChange = { timeFinish = it },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.tag_icon),
                            contentDescription = "Ícone de edição",
                            tint = Color.Gray
                        )
                    },
                    label = {
                        Text(
                            "Hora de término",
                            color = Color.White
                        )
                    },
                    placeholder = { Text("Digite aqui...") },
                    textStyle = TextStyle(color = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    )
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Button(
                    onClick = {
                        val task = TaskEntity(
                            userId = UserSingleton.id,
                            name = UserSingleton.name,
                            description = description,
                            insertDate = System.currentTimeMillis(),
                            dateFinish = dateFinish,
                            timeFinish = timeFinish,
                            status = 0
                        )
                        homeViewModel.insertTask(task)},
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
                                val taskStatus = if (task.status == 2) R.drawable.check_icon else R.drawable.not_check_icon
                                Icon(
                                    painter = painterResource(
                                        id = taskStatus
                                    ),
                                    contentDescription = "Ícone de tarefa",
                                    tint = if (task.status == 1) Color(0xFFBEF264) else Color.Gray,
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

                            OutlinedTextField(
                                value = selectedTask?.description ?: "",
                                onValueChange = {},
                                textStyle = TextStyle(color = Color.White),
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.tag_icon),
                                        contentDescription = null,
                                        tint = Color.Gray
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = selectedTask?.dateFinish ?: "",
                                onValueChange = {},
                                textStyle = TextStyle(color = Color.White),
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.date_icon),
                                        contentDescription = null,
                                        tint = Color.Gray
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = selectedTask?.timeFinish ?: "",
                                onValueChange = {},
                                textStyle = TextStyle(color = Color.White),
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.time_icon),
                                        contentDescription = null,
                                        tint = Color.Gray
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = selectedTask?.status.toString(),
                                onValueChange = {},
                                textStyle = TextStyle(color = Color.White),
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.outline_bookmark_check_24),
                                        contentDescription = null,
                                        tint = Color.Gray
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Excluir atividade",
                                color = Color.Gray,
                                textDecoration = TextDecoration.Underline
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = onDismiss,
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
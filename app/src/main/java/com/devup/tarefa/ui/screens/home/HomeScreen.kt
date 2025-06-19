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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devup.tarefa.R
import com.devup.tarefa.ui.screens.register.RegisterScreen


@Composable
fun HomeScreen(
    navController: NavController,
    showDialog: Boolean = false,
    onDismiss: () -> Unit = {}
) {
    val isPreview = LocalInspectionMode.current

    Box(modifier = Modifier.fillMaxSize()) {
        var inputText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = (Color(0xFF09090B)))
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
                        .border(2.dp, Color.Gray, CircleShape),
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Olá, ",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                )
                Text(
                    modifier = Modifier,
                    text = "Bryan Fagundes",
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
                    value = inputText,
                    onValueChange = { inputText = it },
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
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.date_icon),
                            contentDescription = "Ícone de edição",
                            tint = Color.Gray
                        )
                    },
                    label = {
                        Text(
                            "20 de Agosto",
                            color = Color.White
                        )
                    },
                    placeholder = { Text("Digite aqui...") },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.tag_icon),
                            contentDescription = "Ícone de edição",
                            tint = Color.Gray
                        )
                    },
                    label = {
                        Text(
                            "08:00",
                            color = Color.White
                        )
                    },
                    placeholder = { Text("Digite aqui...") },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Button(
                    onClick = { /* Ação do botão */ },
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
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 16.dp, end = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF18181B))
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.check_icon),
                            contentDescription = "Ícone de tarefa",
                            tint = Color(0xFFBEF264),
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(20.dp)
                        )
                        Text(
                            text = "Tarefa 01",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "sab, 18",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                            )
                            Text(
                                text = "08:00",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 16.dp, end = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF18181B))
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.check_icon),
                            contentDescription = "Ícone de tarefa",
                            tint = Color(0xFFBEF264),
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(20.dp)
                        )
                        Text(
                            text = "Tarefa 01",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "sab, 18",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                            )
                            Text(
                                text = "08:00",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 16.dp, end = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF18181B))
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.check_icon),
                            contentDescription = "Ícone de tarefa",
                            tint = Color(0xFFBEF264),
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(20.dp)
                        )
                        Text(
                            text = "Tarefa 01",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "sab, 18",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                            )
                            Text(
                                text = "08:00",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 16.dp, end = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF18181B))
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.not_check_icon),
                            contentDescription = "Ícone de tarefa",
                            tint = Color.Gray,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(20.dp)
                        )
                        Text(
                            text = "Tarefa 01",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "sab, 18",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                            )
                            Text(
                                text = "08:00",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                            )
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
                                value = "Jantar",
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
                                value = "20 de agosto",
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
                                value = "21:00",
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
                                value = "Atividade concluída",
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
@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
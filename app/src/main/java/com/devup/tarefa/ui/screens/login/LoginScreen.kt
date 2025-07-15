package com.devup.tarefa.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devup.tarefa.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@Composable
fun LoginScreen(
    navController: NavController,
) {
    var email              by remember { mutableStateOf("") }
    var password           by remember { mutableStateOf("") }
    var showCheckAnimation by remember { mutableStateOf(false) }
    var isAuthenticated    by remember { mutableStateOf(false) }

    val keyboardController             = LocalSoftwareKeyboardController.current
    val focusManager                   = LocalFocusManager.current
    val systemUiController             = rememberSystemUiController()
    val loginViewModel: LoginViewModel = hiltViewModel()



    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFF82A449),
            darkIcons = false
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = (Color(0xFF232323))),
        horizontalAlignment = Alignment.CenterHorizontally
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
                .fillMaxWidth(0.7f)
                .align(Alignment.Start),
            thickness = 3.dp,
            color = Color(0xFFBEF264)
        )

        Spacer(modifier = Modifier.height(80.dp))

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(260.dp)
                .align(Alignment.CenterHorizontally)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        "Email",
                        color = Color.White
                    )
                },
                placeholder = { Text("Digite aqui...") },
                textStyle = TextStyle(color = Color.White),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        "Senha",
                        color = Color.White
                    )
                },
                placeholder = { Text("Digite aqui...") },
                textStyle = TextStyle(color = Color.White),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )
            TextButton(
                onClick = {
//                    Adicionar logica para redefinir a senha
                },
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.End)
            ) {
                Text(
                    text = "Redefinir a senha",
                    color = Color(0xFFC2C2C2),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(textDecoration = TextDecoration.Underline)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    loginViewModel.getUserEmail(email, password, onResult = { authenticated ->
                        if (authenticated) {
                            isAuthenticated = true
                            showCheckAnimation = true
                        } else { Toast.makeText(navController.context,"Usuário ou senha inválidos!",Toast.LENGTH_SHORT).show() }}) },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBEF264)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "LOGIN",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Não tem uma conta?",
                    color = Color(0xFFC2C2C2),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = "Cadastre-se",
                    color = Color(0xFFBEF264),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp)
                        .clickable { navController.navigate("register") }
                )
            }
        }
    }

    if (showCheckAnimation) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            val checkComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.check))
            val checkProgress by animateLottieCompositionAsState(
                composition = checkComposition,
                iterations = 1,
                speed = 1.5f,
                isPlaying = true,
                restartOnPlay = false
            )

            LottieAnimation(
                composition = checkComposition,
                progress = { checkProgress },
                modifier = Modifier.size(180.dp)
            )

            LaunchedEffect(checkProgress) {
                if (checkProgress == 1f) {
                    showCheckAnimation = false
                    navController.navigate("home")
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen()
//}
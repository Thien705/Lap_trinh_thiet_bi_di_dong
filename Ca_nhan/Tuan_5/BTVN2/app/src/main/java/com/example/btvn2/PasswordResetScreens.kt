package com.example.btvn2

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Định nghĩa màu sắc chủ đạo để dễ dàng thay đổi
val brandColor = Color(0xFF007AFF)
val lightTextColor = Color.Gray

// --- COMPONENT TÁI SỬ DỤNG ---

@Composable
fun LogoHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "UTH Logo",
            modifier = Modifier.size(120.dp) // Kích thước logo đã được tăng
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "SmartTasks",
            color = brandColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// --- CÁC MÀN HÌNH ---

// ----- Màn hình 1: Forgot Password -----
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(48.dp))
            Text("Forgot Password?", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Enter your Email, we will send you a verification code.",
                color = lightTextColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Your Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.navigate(Screen.Verification.createRoute(email)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = brandColor),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Next", fontSize = 16.sp)
            }
        }
    }
}

// ----- Màn hình 2: Verification -----
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(navController: NavController, email: String) {
    var code by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { /* No title needed */ },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            LogoHeader()
            Spacer(modifier = Modifier.height(48.dp))
            Text("Verify Code", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Enter the code we just sent you on your registered Email",
                color = lightTextColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            OtpTextField(
                otpText = code,
                onOtpTextChange = { value, _ ->
                    if (value.length <= 6) {
                        code = value
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (code.length == 6) {
                        navController.navigate(Screen.ResetPassword.createRoute(email, code))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = brandColor),
                shape = RoundedCornerShape(8.dp),
                enabled = code.length == 6
            ) {
                Text("Next", fontSize = 16.sp)
            }
        }
    }
}


// ----- Màn hình 3: Create new password -----
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(navController: NavController, email: String, verificationCode: String) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { /* No title */ },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            LogoHeader()
            Spacer(modifier = Modifier.height(48.dp))
            Text("Create new password", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Your new password must be different from previously used password",
                color = lightTextColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (password.isNotEmpty() && password == confirmPassword) {
                        navController.navigate(Screen.Confirm.createRoute(email, verificationCode, password))
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = brandColor),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Next", fontSize = 16.sp)
            }
        }
    }
}

// ----- Màn hình 4: Confirm -----
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmScreen(navController: NavController, email: String, verificationCode: String, password: String) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { /* No title */ },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            LogoHeader()
            Spacer(modifier = Modifier.height(48.dp))
            Text("Confirm", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We are here to help you!",
                color = lightTextColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Email") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = verificationCode,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Verification Code") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {},
                readOnly = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("New Password") }
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* Xử lý logic cuối cùng, quay về Login */ },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = brandColor),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Submit", fontSize = 16.sp)
            }
        }
    }
}


// --- COMPONENT CHO Ô NHẬP OTP ---
@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount) {
                onOtpTextChange.invoke(it, it.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    val char = when {
                        index >= otpText.length -> ""
                        else -> otpText[index].toString()
                    }
                    val isFocused = otpText.length == index
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .padding(horizontal = 4.dp)
                            .border(
                                1.dp,
                                if (isFocused) brandColor else Color.LightGray,
                                RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    )
}
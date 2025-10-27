package com.example.btvn

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onSignInClick: () -> Unit,
    onAnonymousClick: () -> Unit = {},
    message: String = ""
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("UTH SmartTasks", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onSignInClick() }
        ) {
            Text("Đăng nhập với Google")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(onClick = { onAnonymousClick() }) {
            Text("Đăng nhập ẩn danh")
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (message.isNotEmpty()) {
            Text(text = message)
        }
    }
}

package com.example.btvn

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseUser

@Composable
fun ProfileScreen(user: FirebaseUser?, onSignOut: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Thông tin người dùng", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Tên: ${user?.displayName ?: "Ẩn danh"}")
        Text("Email: ${user?.email ?: "Không có"}")
        Text("UID: ${user?.uid ?: "Không xác định"}")

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { onSignOut() }) {
            Text("Đăng xuất")
        }
    }
}

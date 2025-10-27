package com.example.thuchanh1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight

sealed interface AuthState {
    object Idle : AuthState
    object Loading : AuthState
    data class Error(val title: String, val message: String) : AuthState
    data class Success(val email: String) : AuthState
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    authState: AuthState = AuthState.Idle,
    configValid: Boolean = true,
    onLoginClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ElevatedButton(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(56.dp)
        ) {
            Text("Login by Gmail", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (!configValid) {
            // Show short configuration hint under button
            Text(
                text = "App not configured for Google Sign-In. Check Firebase settings.",
                color = Color(0xFFD84315),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        when (authState) {
            is AuthState.Idle -> {
                // nothing shown
            }

            is AuthState.Loading -> {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFECEFF1)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(color = Color(0xFF2196F3))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Signing in...",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF37474F)
                        )
                    }
                }
            }

            is AuthState.Error -> {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE6E6)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = authState.title,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            color = Color(0xFFB00020),
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = authState.message,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF5F2A2A)
                        )
                    }
                }
            }

            is AuthState.Success -> {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD9F0FF)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Success!",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF0A6FB6),
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Hi ${authState.email}",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF0A6FB6)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview_Idle() {
    MainScreen(authState = AuthState.Idle)
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview_Loading() {
    MainScreen(authState = AuthState.Loading)
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview_Error() {
    MainScreen(authState = AuthState.Error("Google Sign-In Failed", "User canceled the Google sign-in process."))
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview_Success() {
    MainScreen(authState = AuthState.Success("sample@gmail.com"))
}
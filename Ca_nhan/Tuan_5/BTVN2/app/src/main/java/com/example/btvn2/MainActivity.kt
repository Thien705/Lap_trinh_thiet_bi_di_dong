// File: app/src/main/java/com/example/btvn2/MainActivity.kt

package com.example.btvn2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
// import com.example.btvn2.ui.theme.Btvn2Theme // Tên theme của bạn có thể khác

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Btvn2Theme { // Thay thế MaterialTheme bằng theme của dự án nếu cần
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PasswordResetApp()
                }
            }
        }
    }
}

@Composable
fun PasswordResetApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ForgotPassword.route
    ) {
        // KHỐI LỆNH BỊ THIẾU 1: Forgot Password
        composable(route = Screen.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navController)
        }

        // KHỐI LỆNH BỊ THIẾU 2: Verification
        composable(
            route = Screen.Verification.route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerificationScreen(navController = navController, email = email)
        }

        // Khai báo màn hình 3 (Reset Password)
        composable(
            route = Screen.ResetPassword.route,
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("verificationCode") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val verificationCode = backStackEntry.arguments?.getString("verificationCode") ?: ""
            ResetPasswordScreen(
                navController = navController,
                email = email,
                verificationCode = verificationCode
            )
        }

        // Khai báo màn hình 4 (Confirm)
        composable(
            route = Screen.Confirm.route,
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("verificationCode") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val verificationCode = backStackEntry.arguments?.getString("verificationCode") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            ConfirmScreen(
                navController = navController,
                email = email,
                verificationCode = verificationCode,
                password = password
            )
        }
    }
}
package com.example.btvn2

sealed class Screen(val route: String) {
    data object ForgotPassword : Screen("forgot_password_screen")

    // Sửa route cho Verification để nhận thêm code
    data object Verification : Screen("verification_screen/{email}") {
        fun createRoute(email: String) = "verification_screen/$email"
    }

    // Sửa route cho ResetPassword để nhận thêm code
    data object ResetPassword : Screen("reset_password_screen/{email}/{verificationCode}") {
        fun createRoute(email: String, verificationCode: String) = "reset_password_screen/$email/$verificationCode"
    }

    // Sửa route cho Confirm để nhận thêm code
    data object Confirm : Screen("confirm_screen/{email}/{verificationCode}/{password}") {
        fun createRoute(email: String, verificationCode: String, password: String) = "confirm_screen/$email/$verificationCode/$password"
    }
}
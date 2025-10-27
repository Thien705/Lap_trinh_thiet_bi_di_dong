package com.example.btvn

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.common.api.ApiException

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            var message by remember { mutableStateOf("") }
            var signedIn by remember { mutableStateOf(auth.currentUser != null) }

            val signInLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val data: Intent? = result.data
                try {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    val account = task.getResult(ApiException::class.java)
                    val idToken = account?.idToken
                    if (idToken != null) {
                        val credential = GoogleAuthProvider.getCredential(idToken, null)
                        auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                            if (authTask.isSuccessful) {
                                message = "Đăng nhập thành công"
                                signedIn = true
                            } else {
                                message = "Xác thực Firebase thất bại: ${authTask.exception?.localizedMessage ?: "Không rõ"}"
                            }
                        }
                    } else {
                        message = "Không nhận được idToken từ Google"
                    }
                } catch (e: ApiException) {
                    message = "Google Sign-In thất bại: ${e.statusCode}"
                    e.printStackTrace()
                }
            }

            if (!signedIn) {
                LoginScreen(onSignInClick = {
                    val signInIntent = googleSignInClient.signInIntent
                    signInLauncher.launch(signInIntent)
                }, message = message)
            } else {
                ProfileScreen(auth.currentUser, onSignOut = {
                    auth.signOut()
                    googleSignInClient.signOut() // Also sign out from Google
                    signedIn = false
                    message = "Đã đăng xuất"
                })
            }
        }
    }
}

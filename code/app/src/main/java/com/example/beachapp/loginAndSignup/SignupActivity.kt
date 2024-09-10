package com.example.beachapp.loginAndSignup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.beachapp.MainActivity
import com.example.beachapp.R
import com.example.beachapp.databinding.ActivitySignupBinding
import com.example.beachapp.utility.BeachForecastData
//import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivitySignupBinding

    //firebase auth
    private lateinit var auth: FirebaseAuth


    private lateinit var firestore: FirebaseFirestore

//    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
        binding = DataBindingUtil.setContentView(this@SignupActivity, R.layout.activity_signup)
        firestore = FirebaseFirestore.getInstance()

        var email = ""
        var password = ""
        var confirmPassword = ""

        binding.apply {

            btnSignup.setOnClickListener {

                email = edtSignupEmail.text.toString()
                password = edtSignupPassword.text.toString()
                confirmPassword = edtSignupConfirmPassword.text.toString()

                signUp(email, password, confirmPassword)
            }

        }

        binding.txtLogin.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )
        }
    }

    private fun signUp(email: String, password: String, confirmPassword: String) {

        if (password.length < 6) {
            Toast.makeText(
                this@SignupActivity, "At least 6 Characters are required for password !!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(
                this@SignupActivity, "Confirm Password should be equal to password !!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d("LOG_SIGNUP", "User created")
                if (it.isSuccessful) {

                    addUserToFirestore(auth.currentUser?.email.toString())
                    CoroutineScope(Dispatchers.IO).launch {
                        BeachForecastData.fetchDataFromApi(this@SignupActivity)
                    }
                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener {
                Log.d("LOG_SIGNUP", it.message.toString())
                Toast.makeText(this@SignupActivity, "User already exist !!", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun addUserToFirestore(userEmail : String) {
        val docRef = firestore.collection("gmail")
        val map = mapOf(
            "name" to "User",
            "activities" to ArrayList<Int>(),
            "beaches" to ArrayList<Int>()
        )
        docRef.document(userEmail).set(map)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)!!
//                firebaseAuthWithGoogle(account)
//            } catch (e: ApiException) {
//                Log.w("LOG_GOOGLE_SIGNIN", "signInResult:failed code=" + e.statusCode)
//                Toast.makeText(this, "Google sign-in failed", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

//    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
//        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    startActivity(Intent(this@SignupActivity, MainActivity::class.java))
//                    finish()
//                } else {
//                    Log.w("Firebase Auth", "signInWithCredential:failure", task.exception)
//                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
//
//    private fun signInWithGoogle() {
//        val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent, RC_SIGN_IN)
//    }
}
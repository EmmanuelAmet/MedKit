package com.funmilola.medkit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var inputEmail: EditText;
    private lateinit var inputPassword:EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        //Initialize Firebase Auth
        auth = Firebase.auth

    }



    fun onLogin(view: View?) {
        inputEmail = findViewById(R.id.login_email_text)
        val email = inputEmail.text.toString()
        if(email==null || email==""){
            Toast.makeText(this, "Email field cannot be empty!", Toast.LENGTH_SHORT).show()
            return;
        }
        inputPassword = findViewById(R.id.password_text)
        val password = inputPassword.text.toString()
        if(password==null|| password==""){
            Toast.makeText(this, "Password field cannot be empty!", Toast.LENGTH_SHORT).show()
            return;
        }

        Log.d(TAG, "signIn:$email")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    val intent = Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

                // ...
            }
    }

    fun signUp(view: View?){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "EmailPassword"
        private const val RC_MULTI_FACTOR = 9005
    }
}
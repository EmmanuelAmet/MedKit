package com.funmilola.medkit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var inputFullName: EditText;
    private lateinit var inputEmail: EditText;
    private lateinit var inputPhone: EditText;
    private lateinit var inputPassword: EditText
    private lateinit var inputConfirmPassword: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String;
    private lateinit var password: String;
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        //Initialize Firebase Auth
        auth = Firebase.auth
    }
    fun onSignUp(view: View?) {

        if (!validateForm()) {
            return
        }


        Log.d(TAG, "createAccount:$email")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    // [END create_user_with_email]
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
                // [END_EXCLUDE]
            }
    }

    private fun validateForm(): Boolean {

        inputFullName = findViewById(R.id.fullname_text)
        var fullName = inputFullName.text.toString()
        if(fullName==null|| fullName==""){
            Toast.makeText(this, "Full Name field cannot be empty!", Toast.LENGTH_SHORT).show()
            return false;
        }

        inputEmail = findViewById(R.id.email_text)
        email = inputEmail.text.toString()
        if(email==null || email==""){
            Toast.makeText(this, "Email field cannot be empty!", Toast.LENGTH_SHORT).show()
            return false;
        }
        inputPassword = findViewById(R.id.password_text)
        password = inputPassword.text.toString()
        if(password==null|| password==""){
            Toast.makeText(this, "Password field cannot be empty!", Toast.LENGTH_SHORT).show()
            return false;
        }

        inputConfirmPassword = findViewById(R.id.confirm_password_text)
        var confirmPassword = inputConfirmPassword.text.toString()
        if(confirmPassword==null|| confirmPassword==""){
            Toast.makeText(this, "Password field cannot be empty!", Toast.LENGTH_SHORT).show()
            return false;
        }

        if(confirmPassword!=password){
            Toast.makeText(this, "Password and Confirm Password Mismatch!", Toast.LENGTH_SHORT).show()
            return false;
        }

        inputPhone = findViewById(R.id.phone_text)
        var phone = inputPhone.text.toString()
        if(phone==null|| phone==""){
            Toast.makeText(this, "Phone field cannot be empty!", Toast.LENGTH_SHORT).show()
            return false;
        }

        return true;
    }

    fun signIn(view: View?){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "EmailPassword"
        private const val RC_MULTI_FACTOR = 9005
    }
}
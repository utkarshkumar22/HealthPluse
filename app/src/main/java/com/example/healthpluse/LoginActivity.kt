package com.example.healthpluse

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var fAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var ButtonLogin = findViewById(R.id.button_login) as Button
        var TextViewRegister = findViewById(R.id.txtViewRegister) as TextView
        var EditTextEmail = findViewById(R.id.text_email) as EditText
        var EditTextPassword = findViewById(R.id.text_password) as EditText

        fAuth = FirebaseAuth.getInstance()

        ButtonLogin.setOnClickListener {

            val email = EditTextEmail.text.toString().trim()
            val password = EditTextPassword.text.toString().trim()

            if (email.isEmpty()) {
                EditTextEmail.error = "Email Required"
                EditTextEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                EditTextEmail.error = "Valid Email Required"
                EditTextEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 8) {
                EditTextPassword.error = "Password length must be greater than or equals to 8 characters."
                EditTextPassword.requestFocus()
                return@setOnClickListener
            }

            loginUser(email, password)
        }
        TextViewRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {
        var progressBar = findViewById(R.id.progressBar) as ProgressBar
        progressBar.visibility = View.VISIBLE

        fAuth.signInWithEmailAndPassword(email , password)
                .addOnCompleteListener(this) { task ->
                    progressBar.visibility = View.GONE
                    if (task.isSuccessful){
                        login()
                    }
                    else{
                        task.exception?.message?.let {
                            toast(it)
                        }
                    }
                }
    }

    override fun onStart() {
        super.onStart()

        fAuth.currentUser?.let {
            login()
        }
    }
}
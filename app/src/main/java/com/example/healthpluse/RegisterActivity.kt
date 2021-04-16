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

class RegisterActivity : AppCompatActivity() {

    private lateinit var fAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var TextViewLogin = findViewById(R.id.text_view_login) as TextView
        var ButtonRegister = findViewById(R.id.button_register) as Button
        var EditTextEmail = findViewById(R.id.edit_text_email) as EditText
        var EditTextPassword = findViewById(R.id.edit_text_password) as EditText

        fAuth = FirebaseAuth.getInstance()

        ButtonRegister.setOnClickListener{
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

            registerUser(email, password)

        }

        TextViewLogin.setOnClickListener{
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    private fun registerUser(email: String, password: String) {
        var progressBar = findViewById(R.id.progressbar) as ProgressBar
        progressBar.visibility = View.VISIBLE

        fAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){ task ->
                    progressBar.visibility = View.GONE
                    if(task.isSuccessful) {
                        // Registration Complete
                        login()
                    }
                    else {
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
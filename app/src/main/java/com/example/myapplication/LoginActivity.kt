package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextLogin: EditText
    private lateinit var editTextPass1: EditText
    private lateinit var editTextPass2: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()

    }


    private fun init(){
        editTextLogin = findViewById(R.id.emailAddress)
        editTextPass1 = findViewById(R.id.password1)
        editTextPass2 = findViewById(R.id.password2)
        registerButton = findViewById(R.id.registerbutton)

        registerButton.setOnClickListener() {
            val email = editTextLogin.text.toString()
            val pass1 = editTextPass1.text.toString()
            val pass2 = editTextPass2.text.toString()

            if (email.isEmpty() || '@' !in email) {
                Toast.makeText(this, "invalid e-mail", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (pass1.isEmpty() || pass1.length < 9 && !(pass1.matches(".[0123456789].".toRegex()))) {
                Toast.makeText(this, "this can't be your password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (pass1 != pass2) {
                Toast.makeText(this, "passwords don't match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, pass1)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "you registered successfully", Toast.LENGTH_SHORT).show()
                        } else
                            Toast.makeText(this, "you aren't registered", Toast.LENGTH_SHORT).show()
                    }

        }
    }


}
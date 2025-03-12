package com.example.moviesapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.databinding.ActivityLoginBinding
import com.example.moviesapp.models.User
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    private val TAG = "LoginActivity123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Log.d(TAG, "Fields are Empty")
                Toast.makeText(this, "Please fill all the details.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Login SUCCESS")
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Log.d(TAG, "Login FAILED: ${task.exception?.message}")
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.createAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}

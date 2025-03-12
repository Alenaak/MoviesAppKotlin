package com.example.moviesapp.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.databinding.ActivityIntroBinding
import com.google.firebase.auth.FirebaseAuth


class IntroActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityIntroBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


            window.statusBarColor= Color.TRANSPARENT


            Handler(Looper.getMainLooper()).postDelayed({
                if(FirebaseAuth.getInstance().currentUser==null){
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                else{
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            },3000)
        }

    }

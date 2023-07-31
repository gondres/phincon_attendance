package com.example.myapplication.login_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityIntroScreenBinding
import com.example.myapplication.databinding.ActivityLoginScreenBinding
import com.example.myapplication.forget_password.ForgetScreenActivity
import com.example.myapplication.register_screen.RegisterScreenActivity

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.apply {
            tvForgot.setOnClickListener {
                val intent = Intent(this@LoginScreenActivity,ForgetScreenActivity::class.java)
                startActivity(intent)
            }
            tvRegister.setOnClickListener {
                val intent = Intent(this@LoginScreenActivity,RegisterScreenActivity::class.java)
                startActivity(intent)
            }
            btnLogin.setOnClickListener {
                val intent = Intent(this@LoginScreenActivity,MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}
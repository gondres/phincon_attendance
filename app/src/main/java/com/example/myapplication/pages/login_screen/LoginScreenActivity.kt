package com.example.myapplication.pages.login_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityIntroScreenBinding
import com.example.myapplication.databinding.ActivityLoginScreenBinding
import com.example.myapplication.pages.forget_password.ForgetScreenActivity
import com.example.myapplication.pages.login_screen.view_model.LoginViewModel
import com.example.myapplication.pages.register_screen.RegisterScreenActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        init()
        clickListener()

    }


    private fun init() {
        auth = FirebaseAuth.getInstance()
    }

    private fun clickListener() {

        binding.tvForgot.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, ForgetScreenActivity::class.java)
            startActivity(intent)
        }
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, RegisterScreenActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            login()
        }

    }

    fun login() {
        val email: String = binding.etUsername.text.toString()
        val password: String = binding.etPassword.text.toString()

        val intent = Intent(this,MainActivity::class.java)

        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.postLogin(email,password)
            viewModel.isSuccess.observe(this,{
                if(it){
                    startActivity(intent)
                    finish()
                }else{
                    viewModel.failMessage.observe(this,{
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    })
                }
            })

        } else {
            Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
        }

    }
}
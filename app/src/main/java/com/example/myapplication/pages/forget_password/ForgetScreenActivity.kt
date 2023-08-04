package com.example.myapplication.pages.forget_password

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityForgetPasswordScreenBinding
import com.example.myapplication.databinding.ActivityIntroScreenBinding
import com.example.myapplication.databinding.ActivityLoginScreenBinding
import com.example.myapplication.pages.forget_password.view_model.ForgetViewModel
import com.example.myapplication.pages.login_screen.LoginScreenActivity
import com.google.firebase.auth.FirebaseAuth

class ForgetScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordScreenBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel: ForgetViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordScreenBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        init()
        clickListener()
    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
    }

    private fun clickListener() {
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }
        binding.btnReset.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val intent = Intent(this, LoginScreenActivity::class.java)
            if (email.isNotEmpty()) {
                viewModel.postForget(email).observe(this, {
                    if (it) {
                        Toast.makeText(this, "Check email to reset password", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(intent)
                        finish()
                    } else {
                        viewModel.failMessage.observe(this, {
                            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                        })
                    }
                })

            } else {
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
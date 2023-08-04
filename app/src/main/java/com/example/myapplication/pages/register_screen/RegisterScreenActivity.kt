package com.example.myapplication.pages.register_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityIntroScreenBinding
import com.example.myapplication.databinding.ActivityLoginScreenBinding
import com.example.myapplication.databinding.ActivityRegisterScreenBinding
import com.example.myapplication.pages.login_screen.LoginScreenActivity
import com.example.myapplication.pages.register_screen.view_model.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth

class RegisterScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterScreenBinding
    private val viewModel : RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterScreenBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        clickListener()
    }


    private fun clickListener() {
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener {
            validateData()
        }
    }

    fun validateData() {
        val intent = Intent(this, LoginScreenActivity::class.java)
        val email = binding.etEmail.text.toString()
        val fullName = binding.etFullName.text.toString()
        val password = binding.etPassword.text.toString()
        val repeatPassword = binding.etRepeat.text.toString()
        binding.apply {

            if (etEmail.text.isNotEmpty() && etFullName.text.isNotEmpty() && etPassword.text.isNotEmpty() && etRepeat.text.isNotEmpty()) {
                if (checkPassword(password, repeatPassword)) {
                    viewModel.postRegister(email,password).observe(this@RegisterScreenActivity,{
                        if(it){
                            Toast.makeText(this@RegisterScreenActivity,"Register Succesfull",Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                            finish()
                        }else{
                            viewModel.showFailMessage().observe(this@RegisterScreenActivity,{
                                Toast.makeText(this@RegisterScreenActivity,it,Toast.LENGTH_SHORT).show()
                            })
                        }
                    })
                } else {
                    Toast.makeText(
                        this@RegisterScreenActivity,
                        "Password not same",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@RegisterScreenActivity,
                    "Please fill all data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun checkPassword(password: String, repeatPassword: String): Boolean {
        if (password == repeatPassword) {
            return true
        }
        return false
    }
}
package com.example.myapplication.login_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityIntroScreenBinding
import com.example.myapplication.databinding.ActivityLoginScreenBinding
import com.example.myapplication.forget_password.ForgetScreenActivity
import com.example.myapplication.register_screen.RegisterScreenActivity
import com.google.firebase.auth.FirebaseAuth

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        binding.apply {
            tvForgot.setOnClickListener {
                val intent = Intent(this@LoginScreenActivity, ForgetScreenActivity::class.java)
                startActivity(intent)
            }
            tvRegister.setOnClickListener {
                val intent = Intent(this@LoginScreenActivity, RegisterScreenActivity::class.java)
                startActivity(intent)
            }
            btnLogin.setOnClickListener {
                login()

            }
        }

    }

    fun login() {
        val email: String = binding.etUsername.text.toString()
        val password: String = binding.etPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->
                if (task.isSuccessful){
                    val intent = Intent(this@LoginScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener {
                Toast.makeText(this,it.message.toString(),Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Email and Password cannot be empty",Toast.LENGTH_SHORT).show()
        }

    }
}
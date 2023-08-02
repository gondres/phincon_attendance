package com.example.myapplication.register_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityIntroScreenBinding
import com.example.myapplication.databinding.ActivityLoginScreenBinding
import com.example.myapplication.databinding.ActivityRegisterScreenBinding
import com.example.myapplication.login_screen.LoginScreenActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterScreenBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterScreenBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

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
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        {
                            if (it.isSuccessful) {
                                Toast.makeText(this@RegisterScreenActivity,"Login Successful",Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@RegisterScreenActivity,it.exception?.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }else{
                    Toast.makeText(this@RegisterScreenActivity,"Password not same",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@RegisterScreenActivity,"Please fill all data",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkPassword(password : String, repeatPassword :String): Boolean {
        if (password == repeatPassword) {
            return true
        }
      return false
    }
}
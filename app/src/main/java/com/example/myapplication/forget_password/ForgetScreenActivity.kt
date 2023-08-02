package com.example.myapplication.forget_password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityForgetPasswordScreenBinding
import com.example.myapplication.databinding.ActivityIntroScreenBinding
import com.example.myapplication.databinding.ActivityLoginScreenBinding
import com.google.firebase.auth.FirebaseAuth

class ForgetScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordScreenBinding
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordScreenBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        binding.btnReset.setOnClickListener {
        val email = binding.etEmail.text.toString()
        if(email.isNotEmpty()){
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this,"Check email to reset password",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,it.exception?.message,Toast.LENGTH_SHORT).show()
                }

            }
        }else{
            Toast.makeText(this,"Email cannot be empty",Toast.LENGTH_SHORT).show()
        }

        }

    }
}
package com.example.myapplication.forget_password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityForgetPasswordScreenBinding
import com.example.myapplication.databinding.ActivityIntroScreenBinding
import com.example.myapplication.databinding.ActivityLoginScreenBinding

class ForgetScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordScreenBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

    }
}
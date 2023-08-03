package com.example.myapplication.pages.splash_screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.utils.BaseParam
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.pages.introduce_screen.IntroScreenActivity
import com.example.myapplication.pages.login_screen.LoginScreenActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 1000
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPref: SharedPreferences
    private var showIntro: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        auth = FirebaseAuth.getInstance()
        supportActionBar?.hide()

        sharedPref = this.getSharedPreferences(
            BaseParam.attendaceSharedPref, Context.MODE_PRIVATE
        )
        showIntro = sharedPref.getBoolean("intro", true)
        Thread(Runnable {
            try {
                Thread.sleep(SPLASH_DISPLAY_LENGTH.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            if (auth.currentUser != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }


            if (auth.currentUser == null) {
                if (showIntro) {
                    val intent = Intent(this, IntroScreenActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, LoginScreenActivity::class.java)
                    startActivity(intent)
                }
            }


            finish()
        }).start()

    }


}
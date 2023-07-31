package com.example.myapplication.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.introduce_screen.IntroScreenActivity

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        Thread(Runnable {
            try {
                Thread.sleep(SPLASH_DISPLAY_LENGTH.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val intent = Intent(this, IntroScreenActivity::class.java)
            startActivity(intent)
            finish()
        }).start()
    }

}
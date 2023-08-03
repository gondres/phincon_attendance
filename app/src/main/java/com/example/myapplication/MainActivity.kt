package com.example.myapplication

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityRegisterScreenBinding
import com.example.myapplication.pages.history_screen.HistoryFragment
import com.example.myapplication.pages.home_screen.HomeFragment
import com.example.myapplication.pages.profile_screen.ProfileFragment
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        FirebaseApp.initializeApp(this)
        replaceFragment(HomeFragment())
        binding.apply {
            bottomNavigation.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.home -> replaceFragment(HomeFragment())
                    R.id.history -> replaceFragment(HistoryFragment())
                    R.id.profile -> replaceFragment(ProfileFragment())

                    else -> {

                    }
                }
                true
            }

        }

    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }
}
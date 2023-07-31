package com.example.myapplication.introduce_screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.BaseParam
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityIntroScreenBinding
import com.example.myapplication.introduce_screen.adapter.IntroPagerAdapter
import com.example.myapplication.login_screen.LoginScreenActivity
import com.example.myapplication.register_screen.RegisterScreenActivity
import me.relex.circleindicator.CircleIndicator3


class IntroScreenActivity : AppCompatActivity() {

    private var imageList = mutableListOf<Int>()
    private lateinit var binding: ActivityIntroScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroScreenBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        pushContent()

        binding.apply {

            viewPager.adapter = IntroPagerAdapter(imageList)
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {

                    if (position == 0) {
                        tvTitle.text = BaseParam.introTitleOne
                        tvDesc.text = BaseParam.introDescOne
                        dotOne.setImageResource(R.drawable.ic_oval_selected)
                        dotTwo.setImageResource(R.drawable.ic_oval)
                        dotThree.setImageResource(R.drawable.ic_oval)

                    }
                    if (position == 1) {
                        tvTitle.text = BaseParam.introTitleTwo
                        tvDesc.text = BaseParam.introDescTwo
                        dotOne.setImageResource(R.drawable.ic_oval)
                        dotTwo.setImageResource(R.drawable.ic_oval_selected)
                        dotThree.setImageResource(R.drawable.ic_oval)
                    }
                    if (position == 2) {
                        tvTitle.text = BaseParam.introTitleThree
                        tvDesc.text = BaseParam.introDescThree
                        dotOne.setImageResource(R.drawable.ic_oval)
                        dotTwo.setImageResource(R.drawable.ic_oval)
                        dotThree.setImageResource(R.drawable.ic_oval_selected)
                    }
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })

        }
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, RegisterScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun pushContent() {

        imageList.add(R.drawable.ic_splash_one)
        imageList.add(R.drawable.ic_splash_two)
        imageList.add(R.drawable.ic_splash_three)
    }
}
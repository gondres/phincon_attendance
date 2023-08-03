package com.example.myapplication.pages.introduce_screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.utils.BaseParam
import com.example.myapplication.utils.DataPref
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityIntroScreenBinding
import com.example.myapplication.pages.introduce_screen.adapter.IntroPagerAdapter
import com.example.myapplication.pages.login_screen.LoginScreenActivity
import com.example.myapplication.pages.register_screen.RegisterScreenActivity


class IntroScreenActivity : AppCompatActivity() {

    private var imageList = mutableListOf<Int>()
    private lateinit var binding: ActivityIntroScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroScreenBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        pushContent()
        setupSlider()
        clickListener()


    }

    private fun clickListener(){
        binding.btnLogin.setOnClickListener {
            DataPref.showIntro(this,false)
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnSignup.setOnClickListener {
            DataPref.showIntro(this,false)
            val intent = Intent(this, RegisterScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.tvSkip.setOnClickListener{
            DataPref.showIntro(this,false)
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun setupSlider(){
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
    }


    private fun pushContent() {

        imageList.add(R.drawable.ic_splash_one)
        imageList.add(R.drawable.ic_splash_two)
        imageList.add(R.drawable.ic_splash_three)
    }
}
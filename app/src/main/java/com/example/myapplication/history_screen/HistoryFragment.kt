package com.example.myapplication.history_screen

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHistoryBinding
import com.example.myapplication.history_screen.day_fragment.DayFragment
import com.example.myapplication.history_screen.month_fragment.MonthFragment
import com.example.myapplication.history_screen.week_fragment.WeekFragment
import com.example.myapplication.history_screen.year_fragment.YearFragment

class HistoryFragment : Fragment() {


    private lateinit var binding : FragmentHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        setupContainer()

        replaceFragment(DayFragment())
        return binding.root
    }

    private fun setupContainer(){


        binding.listDay.setOnClickListener {
            replaceFragment(DayFragment())
            binding.listDay.setBackgroundResource(R.drawable.background_rounded_logs)
            binding.tvDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue_color))
            binding.tvDay.setPadding(5)
            binding.tvDay.typeface = ResourcesCompat.getFont(requireContext(), R.font.avenir_bold)

            binding.listWeek.setBackgroundResource(0)
            binding.tvWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))

            binding.listMonth.setBackgroundResource(0)
            binding.tvMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))

            binding.listYear.setBackgroundResource(0)
            binding.tvYear.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))
        }

        binding.listWeek.setOnClickListener {
            replaceFragment(WeekFragment())
            binding.listWeek.setBackgroundResource(R.drawable.background_rounded_logs)
            binding.tvWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue_color))
            binding.tvWeek.setPadding(5)
            binding.tvWeek.typeface = ResourcesCompat.getFont(requireContext(), R.font.avenir_bold)

            binding.listDay.setBackgroundResource(0)
            binding.tvDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))

            binding.listMonth.setBackgroundResource(0)
            binding.tvMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))

            binding.listYear.setBackgroundResource(0)
            binding.tvYear.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))
        }


        binding.listYear.setOnClickListener {
            replaceFragment(YearFragment())
            binding.listYear.setBackgroundResource(R.drawable.background_rounded_logs)
            binding.tvYear.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue_color))
            binding.tvYear.setPadding(5)
            binding.tvYear.typeface = ResourcesCompat.getFont(requireContext(), R.font.avenir_bold)

            binding.listWeek.setBackgroundResource(0)
            binding.tvWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))

            binding.listMonth.setBackgroundResource(0)
            binding.tvMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))

            binding.listDay.setBackgroundResource(0)
            binding.tvDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))
        }

        binding.listMonth.setOnClickListener {
            replaceFragment(MonthFragment())
            binding.listMonth.setBackgroundResource(R.drawable.background_rounded_logs)
            binding.tvMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue_color))
            binding.tvMonth.setPadding(5)
            binding.tvMonth.typeface = ResourcesCompat.getFont(requireContext(), R.font.avenir_bold)

            binding.listWeek.setBackgroundResource(0)
            binding.tvWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))

            binding.listYear.setBackgroundResource(0)
            binding.tvYear.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))

            binding.listDay.setBackgroundResource(0)
            binding.tvDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_color))
        }
    }
    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frameLayoutHistory,fragment)
        fragmentTransaction?.commit()
    }

}
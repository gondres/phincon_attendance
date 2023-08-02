package com.example.myapplication.history_screen.week_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMonthBinding
import com.example.myapplication.databinding.FragmentWeekBinding
import com.example.myapplication.history_screen.day_fragment.adapter.DayListAdapter
import com.example.myapplication.model.AttendanceModel


class WeekFragment : Fragment() {

    private lateinit var binding : FragmentWeekBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWeekBinding.inflate(inflater, container, false)
//        val items = listOf<AttendanceModel>(
//            AttendanceModel(location = "Medan", address = "lubuk pakam", hour = "09:00", imageLocation = 0, status = "CHECK IN"),
//            AttendanceModel(location = "Medan", address = "lubuk pakam", hour = "09:00", imageLocation = 0, status = "CHECK IN"),
//            AttendanceModel(location = "Medan", address = "lubuk pakam", hour = "09:00", imageLocation = 0, status = "CHECK IN"),       )
//
//        val adapter = DayListAdapter(items)
//        binding.rvWeek.adapter = adapter
//        binding.rvWeek.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }


}
package com.example.myapplication.history_screen.month_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDayBinding
import com.example.myapplication.databinding.FragmentMonthBinding
import com.example.myapplication.history_screen.day_fragment.adapter.DayListAdapter
import com.example.myapplication.model.AttendanceModel


class MonthFragment : Fragment() {

    private lateinit var binding : FragmentMonthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMonthBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
//        val items = listOf<AttendanceModel>(
//            AttendanceModel(location = "Medan", address = "lubuk pakam", hour = "09:00", imageLocation = 0, status = "CHECK IN"),
//            AttendanceModel(location = "Medan", address = "lubuk pakam", hour = "09:00", imageLocation = 0, status = "CHECK IN"),
//            AttendanceModel(location = "Medan", address = "lubuk pakam", hour = "09:00", imageLocation = 0, status = "CHECK IN"),
//        )

//        val adapter = DayListAdapter(items)
//        binding.rvMonth.adapter = adapter
//        binding.rvMonth.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }


}
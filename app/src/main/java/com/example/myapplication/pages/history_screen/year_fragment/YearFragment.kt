package com.example.myapplication.pages.history_screen.year_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentWeekBinding
import com.example.myapplication.databinding.FragmentYearBinding
import com.example.myapplication.pages.history_screen.day_fragment.adapter.DayListAdapter
import com.example.myapplication.model.AttendanceModel


class YearFragment : Fragment() {

    private lateinit var binding : FragmentYearBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentYearBinding.inflate(inflater, container, false)

        return binding.root
    }


}
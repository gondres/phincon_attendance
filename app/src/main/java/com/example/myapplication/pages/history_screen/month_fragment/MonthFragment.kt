package com.example.myapplication.pages.history_screen.month_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDayBinding
import com.example.myapplication.databinding.FragmentMonthBinding
import com.example.myapplication.pages.history_screen.day_fragment.adapter.DayListAdapter
import com.example.myapplication.model.AttendanceModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MonthFragment : Fragment() {

    private lateinit var binding : FragmentMonthBinding
    private lateinit var listMonthAttendance: MutableList<AttendanceModel>
    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMonthBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        init()
        stateUi()
        return binding.root
    }
    private fun init() {
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Attendances")
            .child(auth.currentUser?.uid.toString())

        listMonthAttendance = mutableListOf()
        val adapter = DayListAdapter(listMonthAttendance)
        binding.rvMonth.adapter = adapter
        binding.rvMonth.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun stateUi(){
        if(listMonthAttendance.isEmpty()){
            binding.emptyState.visibility = View.VISIBLE
            binding.rvMonth.visibility = View.GONE
        }
    }
}
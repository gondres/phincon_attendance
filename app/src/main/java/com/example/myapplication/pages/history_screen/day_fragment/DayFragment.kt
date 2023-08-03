package com.example.myapplication.pages.history_screen.day_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentDayBinding
import com.example.myapplication.pages.history_screen.day_fragment.adapter.DayListAdapter
import com.example.myapplication.model.AttendanceModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*


class DayFragment : Fragment() {

    private lateinit var binding: FragmentDayBinding
    private lateinit var listDayAttendance: MutableList<AttendanceModel>
    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDayBinding.inflate(inflater, container, false)

        init()
        fetchData()

        return binding.root
    }


    private fun init() {
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Attendances")
            .child(auth.currentUser?.uid.toString())

        listDayAttendance = mutableListOf()
        val adapter = DayListAdapter(listDayAttendance)
        binding.rvDay.adapter = adapter
        binding.rvDay.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun stateUi(){
        if(listDayAttendance.isEmpty()){
            binding.emptyState.visibility = View.VISIBLE
            binding.rvDay.visibility = View.GONE
        }else{
            binding.emptyState.visibility = View.GONE
            binding.rvDay.visibility =  View.VISIBLE
        }
    }

    private fun fetchData() {

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listDayAttendance.clear()


                for (data in snapshot.children) {
                    val response = data.getValue(AttendanceModel::class.java)

                    if (response != null) {
                        print("date ${response.created_at}")
                        listDayAttendance.add(response)

                    }
                }
                stateUi()
                Collections.reverse(listDayAttendance);
                binding.rvDay.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                stateUi()
            }

        })


    }
}
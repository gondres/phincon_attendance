package com.example.myapplication.pages.history_screen.day_fragment.view_model

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.AttendanceModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

/**
 * Created by [Andreas Happy H] on 8/4/2023
 * Jakarta, Indonesia.
 */
class DayViewModel : ViewModel() {
    val listAttendance: MutableLiveData<List<AttendanceModel?>> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference


    fun fetchList(): MutableLiveData<List<AttendanceModel?>> {
        return listAttendance
    }

    fun isSuccessData(): MutableLiveData<Boolean> {
        return isSuccess
    }

    fun getListAttendance() {

        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Attendances")
            .child(auth.currentUser?.uid.toString())
        Log.d(ContentValues.TAG, "get attendance")

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(ContentValues.TAG, "success attendance")
                val tempList = mutableListOf<AttendanceModel?>()

                for (data in snapshot.children) {
                    val response = data.getValue(AttendanceModel::class.java)
                    if (response != null) {
                        Log.d(ContentValues.TAG, response.location)

                       tempList.add(response)


                    }
                }
                listAttendance.postValue(tempList)
                isSuccess.postValue(true)

            }

            override fun onCancelled(error: DatabaseError) {
                isSuccess.postValue(false)
            }

        })
    }

}
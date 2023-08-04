package com.example.myapplication.pages.home_screen.view_model

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.MainActivity
import com.example.myapplication.model.AttendanceModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

/**
 * Created by [Andreas Happy H] on 8/4/2023
 * Jakarta, Indonesia.
 */
class HomeViewModel : ViewModel() {

    val failMessage: MutableLiveData<String> = MutableLiveData()
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference




    fun showFailMessage(): MutableLiveData<String> {
        return failMessage
    }

    fun postAttendance(body : AttendanceModel) : LiveData<Boolean> {
        val isSuccess: MutableLiveData<Boolean> = MutableLiveData()

        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Attendances")
            .child(auth.currentUser?.uid.toString())
        Log.d(ContentValues.TAG, "get attendance")

        databaseRef.push().setValue(body).addOnCompleteListener {
            if (it.isSuccessful) {
                isSuccess.postValue(true)
            } else {
                failMessage.postValue(it.exception?.message)
                isSuccess.postValue(false)
            }
        }

        return isSuccess
    }

}
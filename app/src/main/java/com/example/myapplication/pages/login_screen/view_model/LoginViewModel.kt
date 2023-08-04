package com.example.myapplication.pages.login_screen.view_model

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.widget.Toast
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
class LoginViewModel : ViewModel() {
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val failMessage: MutableLiveData<String> = MutableLiveData()
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference



    fun isSuccessData(): MutableLiveData<Boolean> {
        return isSuccess
    }

    fun showFailMessage(): MutableLiveData<String> {
        return failMessage
    }

    fun postLogin(email : String, password:String) {

        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Attendances")
            .child(auth.currentUser?.uid.toString())

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
               isSuccess.postValue(true)
            }
        }.addOnFailureListener {
            isSuccess.postValue(false)
            failMessage.postValue(it.message.toString())

        }
    }

}
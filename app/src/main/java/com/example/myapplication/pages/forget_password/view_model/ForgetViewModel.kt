package com.example.myapplication.pages.forget_password.view_model

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.MainActivity
import com.example.myapplication.model.AttendanceModel
import com.example.myapplication.pages.login_screen.LoginScreenActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

/**
 * Created by [Andreas Happy H] on 8/4/2023
 * Jakarta, Indonesia.
 */
class ForgetViewModel : ViewModel() {
    val failMessage: MutableLiveData<String> = MutableLiveData()
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference





    fun showFailMessage(): MutableLiveData<String> {
        return failMessage
    }

    fun postForget(email : String) : LiveData<Boolean> {
        val isSuccess : MutableLiveData<Boolean> = MutableLiveData()
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Attendances")
            .child(auth.currentUser?.uid.toString())

        auth.sendPasswordResetEmail(email).addOnCompleteListener {
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
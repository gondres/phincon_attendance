package com.example.myapplication.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by [Andreas Happy H] on 8/2/2023
 * Jakarta, Indonesia.
 */
object DataPref {



    fun setCheckin(context: Context, isCheckin : Boolean) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(BaseParam.attendaceSharedPref, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isCheckin", isCheckin)
        editor.apply()
    }

    fun setLocation(context: Context, location : String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(BaseParam.attendaceSharedPref, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("location", location)
        editor.apply()
    }
    fun setAddress(context: Context, address : String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(BaseParam.attendaceSharedPref, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("address", address)
        editor.apply()
    }
    fun setImage(context: Context, image : Int) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(BaseParam.attendaceSharedPref, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("image", image)
        editor.apply()
    }

    fun getCheckin(context: Context) : Boolean{
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(BaseParam.attendaceSharedPref, Context.MODE_PRIVATE)
        val valueBoolean: Boolean = sharedPreferences.getBoolean("isCheckin", false)

        return valueBoolean
    }
    fun getLocation(context: Context) : String{
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(BaseParam.attendaceSharedPref, Context.MODE_PRIVATE)
        val data: String = sharedPreferences.getString("location", "").toString()

        return data
    }

    fun getAddress(context: Context) : String{
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(BaseParam.attendaceSharedPref, Context.MODE_PRIVATE)
        val data: String = sharedPreferences.getString("address", "").toString()

        return data
    }

    fun getImage(context: Context) : Int{
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(BaseParam.attendaceSharedPref, Context.MODE_PRIVATE)
        val data: Int = sharedPreferences.getInt("image", 0)

        return data
    }

    fun resetPref(context: Context){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(BaseParam.attendaceSharedPref, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.remove("isCheckin")
        editor.remove("location")
        editor.remove("address")
        editor.remove("image")
        editor.apply()
    }

    fun showIntro(context: Context, intro : Boolean) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(BaseParam.attendaceSharedPref, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("intro", intro)
        editor.apply()
    }

}
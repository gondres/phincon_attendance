package com.example.myapplication.model

import androidx.annotation.DrawableRes
import java.util.Date

/**
 * Created by [Andreas Happy H] on 8/1/2023
 * Jakarta, Indonesia.
 */
data class AttendanceModel(
    var status: String = "",
    var location: String = "",
    var address: String = "",
    var hour: String = "",
    var image : Int = 0,
    var created_at : Date = Date()
)
package com.example.myapplication.home_screen

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.example.myapplication.BaseParam
import com.example.myapplication.DataPref
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.AttendanceModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private lateinit var body: AttendanceModel

    private lateinit var location: String
    private lateinit var address: String
    private lateinit var hour: String
    private var image: Int = 0


    private lateinit var sharedPref: SharedPreferences
    private var isCheckin: Boolean = false
    private var isClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        init()
        locationOnClick()

        checkInCheckOut()

        updateTime()


        return binding.root
    }

    private fun updateTime() {
        val handler = Handler(Looper.getMainLooper())
        var updateInterval = 1000
        val timeRunnable = object : Runnable {
            override fun run() {

                binding.tvHour.text = getCurrentHourInIndonesia()

                handler.postDelayed(this, updateInterval.toLong())
            }
        }

        handler.post(timeRunnable)
    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Attendances")
            .child(auth.currentUser?.uid.toString())
        sharedPref = requireActivity().getSharedPreferences(
            BaseParam.attendaceSharedPref, Context.MODE_PRIVATE
        )

        isCheckin = sharedPref.getBoolean("isCheckin", false)

        if (isCheckin) {
            updateUI()
        } else {
            refreshUi()
        }
        hour = getCurrentHourInIndonesia()
        binding.tvHour.text = "${getCurrentHourInIndonesia()}"
        binding.tvDate.text = "${getCurrentDateFormatted()}"
    }

    private fun checkInCheckOut() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        var status: String
        val time = Date()

        binding.ImageViewText.setOnClickListener {

           if(locationOnClick() || DataPref.getCheckin(requireContext())){
               if (!isCheckin) {
                   editor.putBoolean("isCheckin", true)
                   status = "Check In"

                   DataPref.setLocation(requireContext(), location)
                   DataPref.setAddress(requireContext(), address)
                   DataPref.setImage(requireContext(), image)

                   body = AttendanceModel(
                       status = status,
                       location = location,
                       address = address,
                       image = image,
                       created_at = time,
                       hour = hour
                   )
               } else {
                   editor.putBoolean("isCheckin", false)
                   status = "Check Out"

                   location = DataPref.getLocation(requireContext())
                   address = DataPref.getAddress(requireContext())
                   image = DataPref.getImage(requireContext())

                   body = AttendanceModel(
                       status = status,
                       location = location,
                       address = address,
                       image = image,
                       created_at = time,
                       hour = hour
                   )
                   DataPref.resetPref(requireContext())
               }
               editor.apply()

               clickCheckin()

               databaseRef.push().setValue(body).addOnCompleteListener {
                   if (it.isSuccessful) {
                       Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                   } else {
                       Toast.makeText(requireContext(), it.exception?.toString(), Toast.LENGTH_SHORT)
                           .show()
                   }
               }
           }else{
               Toast.makeText(requireContext(),"Please choose location",Toast.LENGTH_SHORT).show()
           }

        }

    }

    private fun locationOnClick() : Boolean {

        binding.phinconContainer.setOnClickListener {

            if (DataPref.getCheckin(requireContext())) {
                null
            } else {
                onClickPhincon()
            }


            location = binding.tvPhincon.text.toString()
            address = binding.tvPhinconAddress.text.toString()
            hour = getCurrentHourInIndonesia()
            image = 0


         isClicked = true
        }

        binding.telkomselContainer.setOnClickListener {

            onClickTSO()

            location = binding.tvTelkomsel.text.toString()
            address = binding.tvTelkomselAddress.text.toString()
            hour = getCurrentHourInIndonesia()
            image = 1

            isClicked = true
        }

        binding.homeContainer.setOnClickListener {

            onClickHome()

            location = binding.tvHome.text.toString()
            address = binding.tvHomeAddress.text.toString()
            hour = getCurrentHourInIndonesia()
            image = 2

            isClicked = true
        }
        return isClicked
    }

    private fun clickCheckin() {
        if (!isCheckin) {
            updateUI()
            isCheckin = !isCheckin
        } else {
            refreshUi()
            isCheckin = !isCheckin
        }


    }

    private fun onClickPhincon() {
        binding.phinconContainer.setBackgroundResource(R.drawable.background_rounded_container_blue)
        binding.phinconContainer.setPadding(10)
        binding.tvPhincon.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        binding.tvPhinconAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.white
            )
        )

        binding.telkomselContainer.setBackgroundResource(0)
        binding.tvTelkomsel.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.black_color
            )
        )
        binding.tvTelkomselAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.grey_color
            )
        )

        binding.homeContainer.setBackgroundResource(0)
        binding.tvHome.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_color))
        binding.tvHomeAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.grey_color
            )
        )
    }

    private fun onClickTSO() {
        binding.phinconContainer.setBackgroundResource(0)
        binding.tvPhincon.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.black_color
            )
        )
        binding.tvPhinconAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.grey_color
            )
        )

        binding.telkomselContainer.setBackgroundResource(R.drawable.background_rounded_container_blue)
        binding.telkomselContainer.setPadding(10)
        binding.tvTelkomsel.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        binding.tvTelkomselAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.white
            )
        )


        binding.homeContainer.setBackgroundResource(0)
        binding.tvHome.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_color))
        binding.tvHomeAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.grey_color
            )
        )

    }

    private fun onClickHome() {
        binding.phinconContainer.setBackgroundResource(0)
        binding.tvPhincon.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.black_color
            )
        )
        binding.tvPhinconAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.grey_color
            )
        )

        binding.telkomselContainer.setBackgroundResource(0)
        binding.tvTelkomsel.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.black_color
            )
        )
        binding.tvTelkomselAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.grey_color
            )
        )


        binding.homeContainer.setBackgroundResource(R.drawable.background_rounded_container_blue)
        binding.homeContainer.setPadding(10)
        binding.tvHome.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        binding.tvHomeAddress.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }


    private fun updateUI() {
        binding.ImageViewText.setBackgroundResource(R.drawable.ic_checkout)
        binding.ImageViewText.text = "Check Out"
        binding.tvPhincon.text = DataPref.getLocation(requireContext())
        binding.tvPhinconAddress.text = DataPref.getAddress(requireContext())

        if (DataPref.getImage(requireContext()) == 0) {
            binding.ivPhincon.setImageResource(R.drawable.image_phincon)
        }
        if (DataPref.getImage(requireContext()) == 1) {
            binding.ivPhincon.setImageResource(R.drawable.image_telkomsel)
        }
        if (DataPref.getImage(requireContext()) == 2) {
            binding.ivPhincon.setImageResource(R.drawable.image_home)
        }

        binding.phinconContainer.setBackgroundResource(R.drawable.background_rounded_container_yellow)
        binding.phinconContainer.setPadding(10)
        binding.tvPhincon.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.black_color
            )
        )
        binding.tvPhinconAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.black_color
            )
        )
        binding.telkomselContainer.visibility = View.GONE
        binding.homeContainer.visibility = View.GONE
    }

    private fun refreshUi() {
        binding.ImageViewText.setBackgroundResource(R.drawable.ic_checkin)
        binding.ImageViewText.text = "Check In"
        binding.telkomselContainer.visibility = View.VISIBLE
        binding.homeContainer.visibility = View.VISIBLE

        binding.tvPhincon.text = "PT Phincon"
        binding.tvPhinconAddress.text = "Office. 88 @Kasablanka Office Tower 18th Floor"
        binding.phinconContainer.setBackgroundResource(0)
        binding.tvPhincon.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.black_color
            )
        )
        binding.tvPhinconAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.grey_color
            )
        )

        binding.telkomselContainer.setBackgroundResource(0)
        binding.tvTelkomsel.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.black_color
            )
        )
        binding.tvTelkomselAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.grey_color
            )
        )
        binding.homeContainer.setBackgroundResource(0)
        binding.tvHome.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_color))
        binding.tvHomeAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(), R.color.grey_color
            )
        )
    }

    fun getCurrentHourInIndonesia(): String {
        val timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        val calendar = Calendar.getInstance(timeZone)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val formatter = SimpleDateFormat("HH:mm")
        return formatter.format(calendar.time)
    }

    fun getCurrentDateFormatted(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(
            "dd MMMM yyyy", Locale("id", "ID")
        ) // "id" for Indonesia, "ID" for the country variant
        return dateFormat.format(calendar.time)
    }

}
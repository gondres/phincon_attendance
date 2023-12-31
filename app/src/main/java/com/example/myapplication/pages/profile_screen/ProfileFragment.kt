package com.example.myapplication.pages.profile_screen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.utils.DataPref
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.pages.login_screen.LoginScreenActivity
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){
            binding.tvEmail.text = auth.currentUser?.email
        }else{
            binding.tvEmail.text = "Unknown"
        }

        binding.ivLogout.setOnClickListener {
            auth.signOut()
            DataPref.resetPref(requireContext())
            val intent = Intent(requireContext(), LoginScreenActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return binding.root
    }

}
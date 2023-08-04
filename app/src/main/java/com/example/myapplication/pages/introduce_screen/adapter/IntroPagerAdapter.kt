package com.example.myapplication.pages.introduce_screen.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R

/**
 * Created by [Andreas Happy H] on 7/31/2023
 * Jakarta, Indonesia.
 */
class IntroPagerAdapter(private var image : List<Int>) : RecyclerView.Adapter<IntroPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivIntro : ImageView = itemView.findViewById(R.id.ivIntro)

        init {
            ivIntro.setOnClickListener { v : View ->
                val position = adapterPosition

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_intro_image,parent,false))

    }

    override fun getItemCount(): Int {
       return image.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
      holder.ivIntro.setImageResource(image[position])

    }

}
package com.example.myapplication.history_screen.day_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.AttendanceModel

class DayListAdapter(private val items: MutableList<AttendanceModel>) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_list_logs, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        if(items[position].image == 0){
            holder.ivLocation.setImageResource(R.drawable.image_phincon)
        }
        if(items[position].image == 1){
            holder.ivLocation.setImageResource(R.drawable.image_telkomsel)
        }
        if(items[position].image == 2){
            holder.ivLocation.setImageResource(R.drawable.image_home)
        }

        if (items[position].location == "Telkomsel Smart Office"){
            holder.tvLocation.text = "TSO"
        }else{

            holder.tvLocation.text = items[position].location
        }
        holder.tvCheckin.text = items[position].status
        holder.tvAddress.text = items[position].address
        holder.tvHour.text = items[position].hour
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvCheckin: TextView = itemView.findViewById(R.id.tvCheckin)
    val tvLocation: TextView = itemView.findViewById(R.id.tvLocation)
    val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
    val ivLocation: ImageView = itemView.findViewById(R.id.ivLocation)
    val tvHour: TextView = itemView.findViewById(R.id.tvHour)
}
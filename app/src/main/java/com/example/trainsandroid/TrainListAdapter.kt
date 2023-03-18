package com.example.trainsandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Array.get

class TrainListAdapter (private val names: List<String>) :
    RecyclerView.Adapter<TrainListAdapter.MyViewHolder>()  {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val num: TextView= itemView.findViewById(R.id.num_train)
        val depDay: TextView = itemView.findViewById(R.id.dep_date)
        val arrDay: TextView = itemView.findViewById(R.id.arr_date)
        val depCity: TextView = itemView.findViewById(R.id.dep_city)
        val arrCity: TextView = itemView.findViewById(R.id.arr_city)
    }

    private var groupList: ArrayList<TrainsModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.trains_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val trains: TrainsModel = groupList!!.get(position)
        holder.num.text = trains.numTrain
        holder.depCity.text = trains.depCityTrain
        holder.arrCity.text = trains.arrCityTrain
        holder.depDay.text = trains.depDayTrain
        holder.arrDay.text = trains.arrDayTrain
    }

    override fun getItemCount(): Int {
        return names.size
    }
}
package com.example.trainsandroid

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.paperdb.Paper
import java.lang.reflect.Array.get
import kotlin.coroutines.coroutineContext


class TrainListAdapter (private val names: ArrayList<TrainsModel>, private val context: Context) :
    RecyclerView.Adapter<TrainListAdapter.MyViewHolder>()  {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val num: TextView= itemView.findViewById(R.id.num_train)
        val depDay: TextView = itemView.findViewById(R.id.dep_date)
        val arrDay: TextView = itemView.findViewById(R.id.arr_date)
        val depCity: TextView = itemView.findViewById(R.id.dep_city)
        val arrCity: TextView = itemView.findViewById(R.id.arr_city)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.trains_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val trains: TrainsModel = names!![position]
        Paper.init(context)
        var departDay = trains.depDayTrain.substring(0, 10)
        var arrivDay = trains.arrDayTrain.substring(0,10)
        holder.num.text = trains.numTrain
        holder.depCity.text = "Город отправления: " + trains.depCityTrain
        holder.arrCity.text = "Город прибытия: " + trains.arrCityTrain
        holder.depDay.text = "Дата отправления: " + departDay
        holder.arrDay.text = "Дата прибытия: " + arrivDay

        holder.itemView.setOnClickListener{
            val intent = Intent(context.applicationContext, CreateOrderActivity::class.java)
            Paper.book("id").write("id", trains)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return names.size
    }
}
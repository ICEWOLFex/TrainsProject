package com.example.trainsandroid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trainsandroid.R
import com.example.trainsandroid.models.OrderTrainModel
import org.w3c.dom.Text

class OrderListAdapter (private val orderInfo: ArrayList<OrderTrainModel>, private val context: Context):
    RecyclerView.Adapter<OrderListAdapter.MyViewHolder>(){
        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            var id:Int = 0
            val num: TextView = itemView.findViewById(R.id.num_train_order)
            val depDay: TextView = itemView.findViewById(R.id.dep_date_order)
            val arrDay: TextView = itemView.findViewById(R.id.arr_date_order)
            val depCity: TextView = itemView.findViewById(R.id.dep_city_order)
            val arrCity: TextView = itemView.findViewById(R.id.arr_city_order)
            val carriege: TextView = itemView.findViewById(R.id.carriege)
            val sit: TextView = itemView.findViewById(R.id.sit)
            val price: TextView = itemView.findViewById(R.id.price_order)
            val state: TextView = itemView.findViewById(R.id.state_order)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.orders_card, parent, false)
            return MyViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return orderInfo.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val order: OrderTrainModel = orderInfo[position]
           // holder.num.text = order.idOrder.toString()
            holder.num.text = order.numTrain
            holder.depDay.text = "Дата отправления: " + order.depDayTrain.substring(0,10)
            holder.arrDay.text = "Дата прибытия: " +order.arrDayTrain.substring(0,10)
            holder.depCity.text = "Город отправления: " + order.depCityTrain
            holder.arrCity.text = "Город прибытия: " +order.arrCityTrain
            holder.carriege.text = "Вагон: " + order.carriageOrder + "; "
            holder.sit.text = "Место: " + order.sitOrder
            holder.price.text = "Цена билета: " + order.priceOrder+"р"
            holder.state.text = "Статус - " + order.stateOrder
        }
    }




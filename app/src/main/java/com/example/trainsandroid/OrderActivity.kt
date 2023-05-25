package com.example.trainsandroid

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainsandroid.adapters.OrderListAdapter
import com.example.trainsandroid.api.API
import com.example.trainsandroid.api.RequestBuilder
import com.example.trainsandroid.models.OrderModel
import com.example.trainsandroid.models.OrderTrainModel
import com.example.trainsandroid.models.TokenModel
import com.example.trainsandroid.models.TrainsModel
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderActivity : AppCompatActivity() {
    var id: Int? = 0
    var trains: ArrayList<OrderTrainModel> = ArrayList()
    var adapter : OrderListAdapter? = null
    lateinit var list: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        list = findViewById(R.id.orders_list)

        overridePendingTransition(R.anim.right_in, R.anim.left_out)

        getData()

        val toProfile: ImageButton = findViewById(R.id.backtoprofile)
        toProfile.setOnClickListener{
            val intent = Intent(applicationContext, MainProfile::class.java)
            startActivity(intent)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deleteItem = trains.get(viewHolder.adapterPosition)
                val pos = viewHolder.adapterPosition
                trains.removeAt(pos)
                deleteData(deleteItem.idOrder)
                adapter!!.notifyDataSetChanged()

            }

        }).attachToRecyclerView(list)
    }

    private fun deleteData(id:Int){
        val token: TokenModel? = Paper.book("token").read("token")
        val list: RecyclerView = findViewById(R.id.orders_list)
        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)
        val delete: Call<Void> = apiInterface.deleteOrder(id, "Bearer " + token!!.token)
        delete.enqueue(object:Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, "Билет удалён", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, "Отсутствует подключение", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getData(){
        val token: TokenModel? = Paper.book("token").read("token")

        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)
        val getOrders: Call<ArrayList<OrderModel>> = apiInterface.getClientsOrders( token!!.idAccount.toInt(),"Bearer " + token!!.token)
        getOrders.enqueue(object:Callback<ArrayList<OrderModel>>{
            override fun onResponse(
                call: Call<ArrayList<OrderModel>>,
                response: Response<ArrayList<OrderModel>>
            ) {
                val order: ArrayList<OrderModel>? = response.body()
                for(i in order!!) {
                    id = i.trainsId
                    val getTrains: Call<TrainsModel> = apiInterface.getOrderTrains(id!!, "Bearer " + token!!.token)
                    getTrains.enqueue(object:Callback<TrainsModel>{
                        override fun onResponse(
                            call: Call<TrainsModel>,
                            response: Response<TrainsModel>
                        ) {
                            if(response.isSuccessful) {
                                var train = OrderTrainModel(i.idOrder,response.body()!!.numTrain,response.body()!!.depDayTrain,response.body()!!.arrDayTrain,response.body()!!.depCityTrain,response.body()!!.arrCityTrain,i.carriageOrder,i.sitOrder,i.priceOrder, i.stateOrder)
                                trains.add(train!!)
                                list.layoutManager = LinearLayoutManager(applicationContext)
                                adapter = OrderListAdapter(trains!!, applicationContext)
                                list.adapter = adapter
                               // Toast.makeText(applicationContext, trains.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }


                        override fun onFailure(call: Call<TrainsModel>, t: Throwable) {
                            Toast.makeText(applicationContext, "Отсутствует подключение", Toast.LENGTH_SHORT).show()
                        }
                    })

                }

            }

            override fun onFailure(call: Call<ArrayList<OrderModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "Отсутствует подключение", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
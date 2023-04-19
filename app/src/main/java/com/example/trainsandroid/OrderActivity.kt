package com.example.trainsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        overridePendingTransition(R.anim.right_in, R.anim.left_out)

        getData()

        val toProfile: ImageButton = findViewById(R.id.backtoprofile)
        toProfile.setOnClickListener{
            val intent = Intent(applicationContext, MainProfile::class.java)
            startActivity(intent)
        }
    }

    private fun getData(){
        val token: TokenModel? = Paper.book("token").read("token")
        val list: RecyclerView = findViewById(R.id.orders_list)
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
                                var train = OrderTrainModel(response.body()!!.numTrain,response.body()!!.depDayTrain,response.body()!!.arrDayTrain,response.body()!!.depCityTrain,response.body()!!.arrCityTrain,i.carriageOrder,i.sitOrder)
                                trains.add(train!!)
                                list.layoutManager = LinearLayoutManager(applicationContext)
                                val adapter = OrderListAdapter(trains!!, applicationContext)
                                list.adapter = adapter
                               // Toast.makeText(applicationContext, trains.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }


                        override fun onFailure(call: Call<TrainsModel>, t: Throwable) {

                        }
                    })

                }

            }

            override fun onFailure(call: Call<ArrayList<OrderModel>>, t: Throwable) {

            }
        })
    }
}
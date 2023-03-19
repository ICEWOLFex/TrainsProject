package com.example.trainsandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OperationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operation)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        val list: RecyclerView = findViewById(R.id.trains_list)
        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)

        Paper.init(this)
        val token: TokenModel? = Paper.book("token").read("token")

        val getTrains: Call<ArrayList<TrainsModel>> = apiInterface.getTrainsList("Bearer " + token!!.token)
        getTrains.enqueue(object:Callback<ArrayList<TrainsModel>>{
            override fun onResponse(
                call: Call<ArrayList<TrainsModel>>,
                response: Response<ArrayList<TrainsModel>>
            ) {
                if(response.isSuccessful){
                    val listTrains: ArrayList<TrainsModel> = response.body()!!
                    list.layoutManager = LinearLayoutManager(applicationContext)
                    val adapter = TrainListAdapter(listTrains)
                    list.adapter = adapter
                }
                else{

                }
            }
            override fun onFailure(call: Call<ArrayList<TrainsModel>>, t: Throwable) {

            }
        })
    }
}
package com.example.trainsandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.remove
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
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

        Paper.init(this)
        val token: TokenModel? = Paper.book("token").read("token")

        val list: RecyclerView = findViewById(R.id.trains_list)
        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)
        val exit: Button = findViewById(R.id.exit)
        val toProfile: ImageButton = findViewById(R.id.to_profile)
        val search: ImageButton = findViewById(R.id.search_button)
        val dep: EditText = findViewById(R.id.departure_city)
        val ar: EditText = findViewById(R.id.arrival_city)

        overridePendingTransition(R.anim.right_in, R.anim.left_out)

        exit.setOnClickListener{
            val sharedPref = this?.getSharedPreferences("auth", MODE_PRIVATE) ?: return@setOnClickListener
            with(sharedPref.edit()){
                remove("log")
                remove("pas")
                apply()
            }
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        toProfile.setOnClickListener{
            val intent = Intent(applicationContext, MainProfile::class.java)
            startActivity(intent)
        }

        search.setOnClickListener{
            val getSearchTrains: Call<ArrayList<TrainsModel>> = apiInterface.getSearchingTrains(dep.text.toString(), ar.text.toString(), "Bearer " + token!!.token)
            getSearchTrains.enqueue(object:Callback<ArrayList<TrainsModel>>{
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
                }

                override fun onFailure(call: Call<ArrayList<TrainsModel>>, t: Throwable) {

                }
            })
        }

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
            }
            override fun onFailure(call: Call<ArrayList<TrainsModel>>, t: Throwable) {

            }
        })
    }
}
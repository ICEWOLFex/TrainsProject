package com.example.trainsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainProfile : AppCompatActivity() {
    var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_profile)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        Paper.init(this)
        val token: TokenModel? = Paper.book("token").read("token")

        val exit: Button = findViewById(R.id.exit)
        exit.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)
        val nametext: TextView = findViewById(R.id.nametext)
        val firstnametext: TextView = findViewById(R.id.firstnametext)
        val lastnametext: TextView = findViewById(R.id.lastnametext)
        val call: Call<AccauntsModel> = apiInterface.getDataAccount(token!!.idAccount.toInt(), "Bearer "+ token.token)
        call.enqueue(object:Callback<AccauntsModel>{
            override fun onResponse(call: Call<AccauntsModel>, response: Response<AccauntsModel>) {
                val accauntsModel: AccauntsModel? = response.body()
                id = accauntsModel!!.clientId
               // nametext.text = id.toString()
                val call2: Call<ClientsModel> = apiInterface.getDataClient(id, "Bearer " + token.token)
                call2.enqueue(object:Callback<ClientsModel>{
                    override fun onResponse(
                        call: Call<ClientsModel>,
                        response: Response<ClientsModel>
                    ) {
                        val clientsModel = response.body()
                        firstnametext.text = clientsModel!!.firstnameClient
                        nametext.text = clientsModel!!.birthdayClient
                        lastnametext.text = clientsModel!!.lastnameClient
                    }

                    override fun onFailure(call: Call<ClientsModel>, t: Throwable) {

                    }
                })

            }

            override fun onFailure(call: Call<AccauntsModel>, t: Throwable) {

            }
        })
    }
}
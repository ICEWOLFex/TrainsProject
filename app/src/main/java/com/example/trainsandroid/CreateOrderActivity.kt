package com.example.trainsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList



class CreateOrderActivity : AppCompatActivity() {
    private var servicesModel: Array<String> = arrayOf("Сервисы")
    private var todayDate: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_order)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        Paper.init(this)
        GetServices()
        val service: Spinner = findViewById(R.id.services)
        val listServices = servicesModel
        val listAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listServices)
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        service!!.adapter(listAdapter)
    }

    fun GetServices(){
        val token: TokenModel? = Paper.book("token").read("toke")
        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)
        val call: Call<ServicesModel> = apiInterface.getServicesList("Bearer " + token!!.token)
        call.enqueue(object:Callback<ServicesModel>{
            override fun onResponse(call: Call<ServicesModel>, response: Response<ServicesModel>){
                servicesModel += arrayOf(response.body()!!.nameServices)
            }
            override fun onFailure(call: Call<ServicesModel>, t: Throwable) {

            }
        })
    }

    fun PostData(sit: String, carriage: String, service: Int?){
        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)
        val trains: TrainsModel? = Paper.book("id").read("id")
        val token: TokenModel? = Paper.book("token").read("token")

        if(Calendar.getInstance().get(Calendar.MONTH).toString().length == 1 && Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString().length == 2){
            todayDate = Calendar.getInstance().get(Calendar.YEAR).toString() + "-0" + Calendar.getInstance().get(Calendar.MONTH).toString() + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
        }
        else if(Calendar.getInstance().get(Calendar.MONTH).toString().length == 1 && Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString().length == 1){
            todayDate = Calendar.getInstance().get(Calendar.YEAR).toString() + "-0" + Calendar.getInstance().get(Calendar.MONTH).toString() + "-0" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
        }
        else if(Calendar.getInstance().get(Calendar.MONTH).toString().length == 2 && Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString().length == 1){
            todayDate = Calendar.getInstance().get(Calendar.YEAR).toString() + "-" + Calendar.getInstance().get(Calendar.MONTH).toString() + "-0" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
        }
        else{
            todayDate = Calendar.getInstance().get(Calendar.YEAR).toString() + "-" + Calendar.getInstance().get(Calendar.MONTH).toString() + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
        }
        val orderModel = OrderModel(0, "Не оплачено", todayDate, carriage, sit, token!!.idAccount, trains!!.idTrains, service)
        val call: Call<OrderModel> = apiInterface.createOrder(orderModel, token!!.token)
        call.enqueue(object:Callback<OrderModel>{
            override fun onResponse(call: Call<OrderModel>, response: Response<OrderModel>) {
                if(response.isSuccessful){
                    val intent = Intent(applicationContext, MainProfile::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<OrderModel>, t: Throwable) {

            }
        })
    }
}

private fun Spinner.adapter(listAdapter: ArrayAdapter<String>) {

}

package com.example.trainsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.trainsandroid.api.API
import com.example.trainsandroid.api.RequestBuilder
import com.example.trainsandroid.models.AccauntsModel
import com.example.trainsandroid.models.ClientsModel
import com.example.trainsandroid.models.TokenModel
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

        overridePendingTransition(R.anim.back_right_in, R.anim.back_left_out)

        Paper.init(this)
        val token: TokenModel? = Paper.book("token").read("token")

        val order: Button = findViewById(R.id.to_orders)
        order.setOnClickListener{
            val intent = Intent(applicationContext, OrderActivity::class.java)
            startActivity(intent)
        }

        val fromProfile: ImageButton = findViewById(R.id.from_profile)
        fromProfile.setOnClickListener{
            val intent = Intent(applicationContext, OperationActivity::class.java)
            startActivity(intent)
        }

        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)
        val nametext: TextView = findViewById(R.id.nametext)
        val firstnametext: TextView = findViewById(R.id.firstnametext)
        val lastnametext: TextView = findViewById(R.id.lastnametext)
        val emailtext: TextView = findViewById(R.id.emailtext)
        val save: Button = findViewById(R.id.SaveChange)

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
                        nametext.text = clientsModel!!.nameClient
                        lastnametext.text = clientsModel!!.lastnameClient
                        emailtext.text = clientsModel!!.emailClient
                            save.setOnClickListener {
                                if(firstnametext.text.toString().length > 1 && nametext.text.toString().length > 1 && lastnametext.text.toString().length > 1) {
                                    val clientsUpdate = ClientsModel(
                                        clientsModel.idClient,
                                        firstnametext.text.toString(),
                                        nametext.text.toString(),
                                        lastnametext.text.toString(),
                                        clientsModel.serPasClient,
                                        clientsModel.numPasClient,
                                        clientsModel.birthdayClient,
                                        clientsModel.recidenceClient,
                                        clientsModel.issuedDayClient,
                                        clientsModel.issuedCodeClient,
                                        clientsModel.phoneClient,
                                        emailtext.text.toString()
                                    )
                                    val call: Call<ClientsModel> = apiInterface.updateClientsData(
                                        id,
                                        clientsUpdate,
                                        "Bearer " + token.token
                                    )
                                    call.enqueue(object : Callback<ClientsModel> {
                                        override fun onResponse(
                                            call: Call<ClientsModel>,
                                            response: Response<ClientsModel>
                                        ) {
                                            if (response.isSuccessful) {
                                                Toast.makeText(
                                                    applicationContext,
                                                    "Данные обновлены",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }

                                        override fun onFailure(
                                            call: Call<ClientsModel>,
                                            t: Throwable
                                        ) {
                                            Toast.makeText(
                                                applicationContext,
                                                "Что-то пошло не так",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    })
                                }
                                else{
                                    if(!firstnametext.text.any { it in "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ" }){
                                        firstnametext.error = "Поле принимает только кирилицу"
                                    }
                                    if(firstnametext.text.toString().isEmpty()){
                                        firstnametext.error = "Поле не может быть пустым"
                                    }
                                    if(!nametext.text.any{it in "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ"}){
                                        nametext.error = "Поле принимает только кирилицу"
                                    }
                                    if(nametext.text.toString().isEmpty()){
                                        nametext.error = "Поле не может быть пустым"
                                    }
                                    if(!lastnametext.text.any { it in "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ" }){
                                        lastnametext.error = "Поле принимает только кирилицу"
                                    }
                                    if(lastnametext.text.toString().isEmpty()){
                                        lastnametext.error = "Поле не может быть пустым"
                                    }
                                    if(!emailtext.text.any{it in "@."}){
                                        emailtext.error = "Некоректный формат"
                                    }
                                    if(emailtext.text.toString().isEmpty()){
                                        emailtext.error = "Поле не может быть пустым"
                                    }
                                    Toast.makeText(
                                        applicationContext,"Что-то пошло не так", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                    override fun onFailure(call: Call<ClientsModel>, t: Throwable) {
                        Toast.makeText(applicationContext, "Отсутствует подключение", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            override fun onFailure(call: Call<AccauntsModel>, t: Throwable) {
                Toast.makeText(applicationContext, "Отсутствует подключение", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
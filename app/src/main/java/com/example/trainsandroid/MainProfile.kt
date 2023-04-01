package com.example.trainsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
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

        overridePendingTransition(R.anim.back_right_in, R.anim.back_left_out)

        Paper.init(this)
        val token: TokenModel? = Paper.book("token").read("token")

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
                                        clientsModel.emailClient
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
                                    if(firstnametext.text.toString().length < 1){
                                        firstnametext.setError("Поле не может быть пустым")
                                    }
                                    if(!firstnametext.text.any { it in "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ" }){
                                        firstnametext.setError("Поле принимает только кирилицу")
                                    }
                                    if(nametext.text.toString().length < 1){
                                        nametext.setError("Поле не может быть пустым")
                                    }
                                    if(!nametext.text.any{it in "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ"}){
                                        nametext.setError("Поле принимает только кирилицу")
                                    }
                                    if(lastnametext.text.toString().length < 1){
                                        lastnametext.setError("Поле не может быть пустым")
                                    }
                                    if(!lastnametext.text.any { it in "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ" }){
                                        lastnametext.setError("Поле принимает только кирилицу")
                                    }
                                    if(emailtext.text.toString().length < 1){
                                        emailtext.setError("Поле не может быть пустым")
                                    }
                                    if(!emailtext.text.any{it in "@."}){
                                        emailtext.setError("Некоректный формат")
                                    }
                                    Toast.makeText(
                                        applicationContext,"Что-то пошло не так", Toast.LENGTH_SHORT).show()
                                }
                            }
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
package com.example.trainsandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.paperdb.Paper
import retrofit2.*
import javax.sql.CommonDataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        Paper.init(this)
        val login: EditText = findViewById(R.id.Login)
        val password: EditText = findViewById(R.id.Password)
        val regbutton: Button = findViewById(R.id.ToRegButton)
        val enterbutton: Button = findViewById(R.id.EnterButton)

        var sharedPref = this?.getSharedPreferences("auth", MODE_PRIVATE) ?: return
        val savedLogin = sharedPref?.getString("log", "")!!
        val savedPassword = sharedPref?.getString("pas", "")!!
        if(savedLogin.isNotEmpty() && savedPassword.isNotEmpty()){
            PostData(savedLogin, savedPassword)
        }

        overridePendingTransition(R.anim.right_in, R.anim.left_out)

        enterbutton.setOnClickListener{
            var check = true
            if(login.text.length < 1){
                check = false
                login.setError("Заполните поле")
            }
            if(password.text.length < 1){
                check = false
                password.setError("Заполните поле")
            }
            if(check){
                sharedPref = this?.getSharedPreferences("auth", MODE_PRIVATE) ?: return@setOnClickListener
                with (sharedPref.edit()) {
                    putString("log", login.text.toString())
                    putString("pas", password.text.toString())
                    apply()
                }
                PostData(login.text.toString(), password.text.toString())
            }
        }

        regbutton.setOnClickListener{
            val intent = Intent(applicationContext, Registration::class.java)
            startActivity(intent)
        }
    }
    fun PostData(log: String, pas: String){
        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)

        val accauntsModel = AccauntsModel(0,log, pas, "string", 0, 0)
        val call: Call<TokenModel> = apiInterface.authAccaunt(accauntsModel)
        call.enqueue(object:Callback<TokenModel>{
            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Отсутствует подключение  к интернету",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                if(response.isSuccessful){
                    Paper.book("token").write("token", response.body()!!)
                    val intent = Intent(applicationContext, OperationActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(
                        applicationContext,
                        "Неверный логин или пароль",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }
}
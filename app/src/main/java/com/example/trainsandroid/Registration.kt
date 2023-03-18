package com.example.trainsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Registration : AppCompatActivity() {

    private var datebirth: String = ""
    private var dateissued: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        val toauth: Button = findViewById(R.id.ToAuthButton)
        toauth.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        val login: EditText = findViewById(R.id.Loginreg)
        val password: EditText = findViewById(R.id.Passwordreg)
        val firstname: EditText = findViewById(R.id.firstname)
        val name: EditText = findViewById(R.id.name)
        val lastname: EditText = findViewById(R.id.lastname)
        val serpas: EditText = findViewById(R.id.serpas)
        val numpas: EditText = findViewById(R.id.numpas)

        val residence: EditText = findViewById(R.id.recedence)

        val issuedcode:EditText = findViewById(R.id.issuedcode)
        val phone: EditText = findViewById(R.id.phone)
        val email: EditText = findViewById(R.id.email)
        val registration: Button = findViewById(R.id.registration)
        registration.setOnClickListener{
            var check: Boolean = true
            if(login.text.toString().length < 1){
                check = false
                login.setError("Поле не заполнено")
            }
            if(login.text.toString().length > 0 && login.text.toString().length < 3){
                login.setError("Логин должен содержать не менее 4 символов")
            }
            if(password.text.toString().length < 1){
                check = false
                password.setError("Поле не заполнено")
            }
            if(!password.text.any {it in "1234567890"}){
                check = false
                password.setError("Пароль должен содержать цифры, заглавные и прописные латинские буквы")
            }
            if(!password.text.any {it in "qwertyuiopasdfghjklzxcvbnm"}){
                check = false
                password.setError("Пароль должен содержать цифры, заглавные и прописные латинские буквы")
            }
            if(!password.text.any {it in "QWERTYUIOPASDFGHJKLZXCVBNM"}){
                check = false
                password.setError("Пароль должен содержать цифры, заглавные и прописные латинские буквы")
            }
            if(password.text.toString().length > 0 && password.text.toString().length < 8){
                check = false
                password.setError("Пароль должен содержать не менее 8 символов")
            }
            if(firstname.text.toString().length <1 ){
                check = false
                firstname.setError("Поле не заполнено")
            }
            if(name.text.toString().length <1 ){
                check = false
                name.setError("Поле не заполнено")
            }
            if(lastname.text.toString().length <1 ){
                check = false
                lastname.setError("Поле не заполнено")
            }
            if(serpas.text.toString().length <1){
                check = false
                serpas.setError("Поле не заполнено")
            }
            if(numpas.text.toString().length <1){
                check = false
                numpas.setError("Поле не заполнено")
            }
            if(residence.text.toString().length <1){
                check = false
                residence.setError("Поле не заполнено")
            }
            if(issuedcode.text.toString().length <1){
                check = false
                issuedcode.setError("Поле не заполнено")
            }
            if(phone.text.toString().length < 1){
                check = false
                phone.setError("Поле не заполнено")
            }
            if(check){
               // Toast.makeText(this, getBirthday(), Toast.LENGTH_SHORT).show()
               // Toast.makeText(this, getIssuedday(), Toast.LENGTH_SHORT).show()
                PostData(login.text.toString(), password.text.toString(), firstname.text.toString(), name.text.toString(), lastname.text.toString(), serpas.text.toString(), numpas.text.toString(), getBirthday(), residence.text.toString(), getIssuedday(), issuedcode.text.toString(), phone.text.toString(), email.text.toString())
            }
        }
    }

    fun getBirthday(): String{
        val birthday: DatePicker = findViewById(R.id.birthday)
        val thisYear = birthday.year
        val thisMonth = birthday.month
        val thisDay = birthday.dayOfMonth
        if(thisMonth.toString().length==2 && thisDay.toString().length==2){
            datebirth = "$thisYear-$thisMonth-$thisDay"
        }
        else if(thisMonth.toString().length < 2 && thisDay.toString().length ==2){
            datebirth = "$thisYear-0$thisMonth-$thisDay"
        }
        else if(thisMonth.toString().length==2 && thisDay.toString().length<2){
            datebirth = "$thisYear-$thisMonth-0$thisDay"
        }
        else{
            datebirth = "$thisYear-0$thisMonth-0$thisDay"
        }
        val today = Calendar.getInstance()
        birthday.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)) { view, year, month, day ->
            val month = month + 1
            if(month.toString().length == 2 && day.toString().length ==2) {
                datebirth = "$year-$month-$day"
            }
            else if(month.toString().length < 2 && day.toString().length ==2){
                datebirth = "$year-0$month-$day"
            }
            else if(month.toString().length == 2 && day.toString().length <2){
                datebirth = "$year-$month-0$day"
            }
            else{
                datebirth = "$year-0$month-0$day"
            }
        }
            return datebirth
        //return date
    }

    fun getIssuedday(): String{
        val issuedday: DatePicker = findViewById(R.id.issuedday)
        val thisYear = issuedday.year
        val thisMonth = issuedday.month
        val thisDay = issuedday.dayOfMonth
        if(thisMonth.toString().length==2 && thisDay.toString().length==2){
            dateissued = "$thisYear-$thisMonth-$thisDay"
        }
        else if(thisMonth.toString().length < 2 && thisDay.toString().length ==2){
            dateissued = "$thisYear-0$thisMonth-$thisDay"
        }
        else if(thisMonth.toString().length==2 && thisDay.toString().length<2){
            dateissued = "$thisYear-$thisMonth-0$thisDay"
        }
        else{
            dateissued = "$thisYear-0$thisMonth-0$thisDay"
        }
        val today = Calendar.getInstance()
        issuedday.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)) { view, year, month, day ->
            val month = month + 1
            if(month.toString().length == 2 && day.toString().length ==2) {
                dateissued = "$year-$month-$day"
            }
            else if(month.toString().length < 2 && day.toString().length ==2){
                dateissued = "$year-0$month-$day"
            }
            else if(month.toString().length == 2 && day.toString().length <2){
                dateissued = "$year-$month-0$day"
            }
            else{
                dateissued = "$year-0$month-0$day"
            }
        }
        return dateissued
        //return date
    }

    fun PostData(log:String, pas:String, first:String, nm:String, last:String, ser:String, num:String, birth: String, res:String, issday:String, isscode:String, phn:String, mail:String){
        val login: EditText = findViewById(R.id.Loginreg)
        val password: EditText = findViewById(R.id.Passwordreg)
        val firstname: EditText = findViewById(R.id.firstname)
        val name: EditText = findViewById(R.id.name)
        val lastname: EditText = findViewById(R.id.lastname)
        val serpas: EditText = findViewById(R.id.serpas)
        val numpas: EditText = findViewById(R.id.numpas)
        val residence: EditText = findViewById(R.id.recedence)
        val issuedcode:EditText = findViewById(R.id.issuedcode)
        val phone: EditText = findViewById(R.id.phone)
        val email: EditText = findViewById(R.id.email)
        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)

        val registrationModel = RegistrationModel(0, log, pas, "", 0, 0 , 0, first, nm, last, ser, num, birth, res, issday, isscode, phn, mail)
        val call: Call<RegistrationModel> = apiInterface.addAccaunt(registrationModel)
        call.enqueue(object:Callback<RegistrationModel>{
            override fun onResponse(
                call: Call<RegistrationModel>,
                response: Response<RegistrationModel>
            ) {
                if(response.isSuccessful){
                    login.text = null
                    password.text = null
                    firstname.text = null
                    name.text = null
                    lastname.text = null
                    serpas.text = null
                    numpas.text = null
                    residence.text = null
                    issuedcode.text = null
                    phone.text = null
                    email.text = null
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
                else{

                }
            }

            override fun onFailure(call: Call<RegistrationModel>, t: Throwable) {
               // Toast.makeText(this, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }
}
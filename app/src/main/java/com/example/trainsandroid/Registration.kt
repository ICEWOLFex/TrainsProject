package com.example.trainsandroid

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent.*
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.trainsandroid.api.API
import com.example.trainsandroid.api.RequestBuilder
import com.example.trainsandroid.models.RegistrationModel
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

        overridePendingTransition(R.anim.back_right_in, R.anim.back_left_out)

        val login: EditText = findViewById(R.id.Loginreg)
        val password: EditText = findViewById(R.id.Passwordreg)
        val firstname: EditText = findViewById(R.id.firstname)
        val name: EditText = findViewById(R.id.name)
        val lastname: EditText = findViewById(R.id.lastname)
        val serpas: EditText = findViewById(R.id.serpas)
        val numpas: EditText = findViewById(R.id.numpas)

        val residence: EditText = findViewById(R.id.recedence)

        val issuedcode:EditText = findViewById(R.id.issuedcode)

        issuedcode.isCursorVisible = false
        issuedcode.addTextChangedListener (object: TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                issuedcode.setOnKeyListener { v, keyCode, event ->
                    if (issuedcode.text.toString().length == 3) {
                        issuedcode.setText("$s-")
                        issuedcode.setSelection(issuedcode.text.length)
                    }
                    if (keyCode == KEYCODE_DEL) {
                        issuedcode.text.clear()
                    }
                    var consumed = false
                    consumed
                }
            }
        })

        val phone: EditText = findViewById(R.id.phone)

        phone.isCursorVisible = false
        phone.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                phone.setSelection(phone.text.length)
                phone.setOnKeyListener { v, keyCode, event ->
                    if (phone.text.toString().length == 1) {
                        phone.setText("+7($s")
                    }
                    if (phone.text.toString().length == 6) {
                        phone.setText("$s)")
                    }
                    if (phone.text.toString().length == 10) {
                        phone.setText("$s-")
                    }
                    if (phone.text.toString().length == 13) {
                        phone.setText("$s-")
                    }
                    if (keyCode == KEYCODE_DEL) {
                        phone.text.clear()
                    }
                    var consumed = false
                    consumed
                }
            }
        })

        val email: EditText = findViewById(R.id.email)
        val registration: Button = findViewById(R.id.registration)
        registration.setOnClickListener{
            var check: Boolean = true
            if(login.text.toString().length in 1..3){
                login.error = "Логин должен содержать не менее 4 символов"
            }
            if(login.text.toString().isEmpty()){
                check = false
                login.error = "Поле не заполнено"
            }
            if(!password.text.any {it in "1234567890"}){
                check = false
                password.error = "Пароль должен содержать цифры, заглавные и прописные латинские буквы"
            }
            if(!password.text.any {it in "qwertyuiopasdfghjklzxcvbnm"}){
                check = false
                password.error = "Пароль должен содержать цифры, заглавные и прописные латинские буквы"
            }
            if(!password.text.any {it in "QWERTYUIOPASDFGHJKLZXCVBNM"}){
                check = false
                password.error = "Пароль должен содержать цифры, заглавные и прописные латинские буквы"
            }
            if(password.text.toString().length in 1..7){
                check = false
                password.error = "Пароль должен содержать не менее 8 символов"
            }
            if(password.text.toString().isEmpty()){
                check = false
                password.error = "Поле не заполнено"
            }
            if(!firstname.text.any { it in "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ" }){
                check = false
                firstname.error = "Поле принимает только кирилицу"
            }
            if(firstname.text.toString().isEmpty()){
                check = false
                firstname.error = "Поле не заполнено"
            }
            if(!name.text.any { it in "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ" }){
                check = false
                name.error = "Поле принимает только кирилицу"
            }
            if(name.text.toString().isEmpty()){
                check = false
                name.error = "Поле не заполнено"
            }
            if(!lastname.text.any { it in "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ" }){
                check = false
                lastname.error = "Поле принимает только кирилицу"
            }
            if(lastname.text.toString().isEmpty()){
                check = false
                lastname.error = "Поле не заполнено"
            }
            if(serpas.text.toString().length != 4){
                check = false
                serpas.error = "Некорректные данные"
            }
            if(serpas.text.toString().isEmpty()){
                check = false
                serpas.error = "Поле не заполнено"
            }
            if(numpas.text.toString().length != 6){
                check=false
                numpas.error = "Некорректные данные"
            }
            if(numpas.text.toString().isEmpty()){
                check = false
                numpas.error = "Поле не заполнено"
            }
            if(residence.text.toString().isEmpty()){
                check = false
                residence.error = "Поле не заполнено"
            }
            if(issuedcode.text.toString().length !=7){
                check=false
                issuedcode.error = "Некорректные данные"
            }
            if(issuedcode.text.toString().isEmpty()){
                check = false
                issuedcode.error = "Поле не заполнено"
            }
            if(phone.text.toString().length != 16){
                check=false
                phone.error = "Некорректные данные"
            }
            if(phone.text.toString().isEmpty()){
                check = false
                phone.error = "Поле не заполнено"
            }
            if(!email.text.any{it in "@"} && !email.text.any{it in "."}){
                email.error = "Некоректный формат"
            }
            if(email.text.toString().isEmpty()){
                check=false
                email.setError("Поле не заполнено")
            }
            if(check){
                PostData(login.text.toString(), password.text.toString(), firstname.text.toString(), name.text.toString(), lastname.text.toString(), serpas.text.toString(), numpas.text.toString(), getBirthday(), residence.text.toString(), getIssuedday(), issuedcode.text.toString(), phone.text.toString(), email.text.toString())
            }
        }
    }

    private fun getBirthday(): String{
        val birthday: DatePicker = findViewById(R.id.birthday)
        val thisYear = birthday.year
        val thisMonth = birthday.month + 1
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
            today.get(Calendar.DAY_OF_MONTH)) { _, year, month, day ->
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
    }

    private fun getIssuedday(): String{
        val issuedday: DatePicker = findViewById(R.id.issuedday)
        val thisYear = issuedday.year
        val thisMonth = issuedday.month + 1
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
            today.get(Calendar.DAY_OF_MONTH)) { _, year, month, day ->
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
    }

    private fun PostData(log:String, pas:String, first:String, nm:String, last:String, ser:String, num:String, birth: String, res:String, issday:String, isscode:String, phn:String, mail:String){
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
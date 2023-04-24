package com.example.trainsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.trainsandroid.api.API
import com.example.trainsandroid.api.RequestBuilder
import com.example.trainsandroid.models.OrderModel
import com.example.trainsandroid.models.ServicesModel
import com.example.trainsandroid.models.TokenModel
import com.example.trainsandroid.models.TrainsModel
import io.paperdb.Paper
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class CreateOrderActivity : AppCompatActivity() {
    private var todayDate: String = ""
    private var servicesModel: ArrayList<ServicesModel>? = null
    var servId: Int = 0
    var fullPrice: Int = 0
    var servPrice: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_order)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        overridePendingTransition(R.anim.back_right_in, R.anim.back_left_out)

        val num: TextView = findViewById(R.id.num_train_data)
        val depday:TextView = findViewById(R.id.dep_date_data)
        val depcity: TextView = findViewById(R.id.dep_city_data)
        val arrday: TextView = findViewById(R.id.arr_date_data)
        val arrcity: TextView = findViewById(R.id.arr_city_data)
        val info: TextView = findViewById(R.id.info)
        var cariege: Int? = null
        var sit: Int? = null
        val price: TextView = findViewById(R.id.price)
        val priceserv: TextView = findViewById(R.id.price_serv)

        val create: Button = findViewById(R.id.create_order)

        val cariege1Sit1: Button = findViewById(R.id.carriege1_sit1)
        val cariege1Sit2: Button = findViewById(R.id.carriege1_sit2)
        val cariege1Sit3: Button = findViewById(R.id.carriege1_sit3)
        val cariege1Sit4: Button = findViewById(R.id.carriege1_sit4)
        val cariege1Sit5: Button = findViewById(R.id.carriege1_sit5)
        val cariege1Sit6: Button = findViewById(R.id.carriege1_sit6)

        val cariege2Sit1: Button = findViewById(R.id.carriege2_sit1)
        val cariege2Sit2: Button = findViewById(R.id.carriege2_sit2)
        val cariege2Sit3: Button = findViewById(R.id.carriege2_sit3)
        val cariege2Sit4: Button = findViewById(R.id.carriege2_sit4)
        val cariege2Sit5: Button = findViewById(R.id.carriege2_sit5)
        val cariege2Sit6: Button = findViewById(R.id.carriege2_sit6)

        val cariege3Sit1: Button = findViewById(R.id.carriege3_sit1)
        val cariege3Sit2: Button = findViewById(R.id.carriege3_sit2)

        checkSits(cariege1Sit1,
            cariege1Sit2,
            cariege1Sit3,
            cariege1Sit4,
            cariege1Sit5,
            cariege1Sit6,
            cariege2Sit1,
            cariege2Sit2,
            cariege2Sit3,
            cariege2Sit4,
            cariege2Sit5,
            cariege2Sit6,
            cariege3Sit1,
            cariege3Sit2)

        cariege1Sit1.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.selected_train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 1 :: Место: 1"
            cariege = 1
            sit = 1
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }

        cariege1Sit2.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.selected_train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 1 :: Место: 2"
            cariege = 1
            sit = 2
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }
        cariege1Sit3.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.selected_train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 1 :: Место: 3"
            cariege = 1
            sit = 3
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }
        cariege1Sit4.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.selected_train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 1 :: Место: 4"
            cariege = 1
            sit = 4
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }
        cariege1Sit5.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.selected_train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 1 :: Место: 5"
            cariege = 1
            sit = 5
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }
        cariege1Sit6.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.selected_train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 1 :: Место: 6"
            cariege = 1
            sit = 6
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }

        cariege2Sit1.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.selected_train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 2 :: Место: 1"
            cariege = 2
            sit = 1
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }
        cariege2Sit2.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.selected_train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 2 :: Место: 2"
            cariege = 2
            sit = 2
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }
        cariege2Sit3.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.selected_train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 2 :: Место: 3"
            cariege = 2
            sit = 3
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }
        cariege2Sit4.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.selected_train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 2 :: Место: 4"
            cariege = 2
            sit = 4
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }
        cariege2Sit5.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.selected_train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 2 :: Место: 5"
            cariege = 2
            sit = 5
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }
        cariege2Sit6.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.selected_train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 2 :: Место: 6"
            cariege = 2
            sit = 6
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }

        cariege3Sit1.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.selected_train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.train_button)
            info.text = "Вагон: 3 :: Место: 1"
            cariege = 3
            sit = 1
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }
        cariege3Sit2.setOnClickListener{
            cariege1Sit1.setBackgroundResource(R.drawable.train_button)
            cariege1Sit2.setBackgroundResource(R.drawable.train_button)
            cariege1Sit3.setBackgroundResource(R.drawable.train_button)
            cariege1Sit4.setBackgroundResource(R.drawable.train_button)
            cariege1Sit5.setBackgroundResource(R.drawable.train_button)
            cariege1Sit6.setBackgroundResource(R.drawable.train_button)
            cariege2Sit1.setBackgroundResource(R.drawable.train_button)
            cariege2Sit2.setBackgroundResource(R.drawable.train_button)
            cariege2Sit3.setBackgroundResource(R.drawable.train_button)
            cariege2Sit4.setBackgroundResource(R.drawable.train_button)
            cariege2Sit5.setBackgroundResource(R.drawable.train_button)
            cariege2Sit6.setBackgroundResource(R.drawable.train_button)
            cariege3Sit1.setBackgroundResource(R.drawable.train_button)
            cariege3Sit2.setBackgroundResource(R.drawable.selected_train_button)
            info.text = "Вагон: 3 :: Место: 2"
            cariege = 3
            sit = 2
            checkSits(cariege1Sit1,cariege1Sit2,cariege1Sit3,cariege1Sit4,cariege1Sit5, cariege1Sit6,cariege2Sit1,cariege2Sit2,cariege2Sit3,cariege2Sit4,cariege2Sit5,cariege2Sit6,cariege3Sit1,cariege3Sit2)
        }

        val service: Spinner = findViewById(R.id.services)
        GetServices(service)
        service.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                servId = servicesModel!![position].idServices
                servPrice = servicesModel!![position].priceSevices.toInt()
                priceserv.text = "Цена услуги: " + servicesModel!![position].priceSevices
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val back: ImageButton = findViewById(R.id.back)
        back.setOnClickListener{
            val intent = Intent(applicationContext, OperationActivity::class.java)
            startActivity(intent)
        }

        Paper.init(this)
        val trains: TrainsModel? = Paper.book("id").read("id")
        num.text = trains?.numTrain
        depcity.text = "Город отправления: " + trains?.depCityTrain
        arrcity.text = "Город прибытия: " + trains?.arrCityTrain
        depday.text = "Дата отправления: " + trains?.depDayTrain?.substring(0,10)
        arrday.text = "Дата прибытия: " + trains?.arrDayTrain?.substring(0,10)

        fullPrice+=trains?.priceTrain!!.toInt()

        price.text = "Цена рейса: " + fullPrice

        create.setOnClickListener{
            if(cariege != null && sit != null){
                PostData(sit.toString(), cariege.toString(), servId)
            }
            else{
                Toast.makeText(applicationContext, "Не выбрано место", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun GetServices(service: Spinner) {

        val token: TokenModel? = Paper.book("token").read("token")
        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)
        val call: Call<ArrayList<ServicesModel>> =
            apiInterface.getServicesList("Bearer " + token!!.token)
        call.enqueue(object : Callback<ArrayList<ServicesModel>> {
            override fun onResponse(
                call: Call<ArrayList<ServicesModel>>,
                response: Response<ArrayList<ServicesModel>>
            )
            {
                if(response.isSuccessful) {
                    servicesModel = response.body()
                    val listAdapter = ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_spinner_item,
                        servicesModel!!
                    )
                    service!!.adapter = listAdapter
                }
                else{
                    Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<ServicesModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "Упс, что-то пошло не так", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun PostData(sit: String, carriage: String, service: Int?){
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
        fullPrice += servPrice
        val orderModel = OrderModel(0, "Не оплачено", todayDate, carriage, sit, fullPrice.toString(), token!!.idAccount, trains!!.idTrains, service)
        val call: Call<OrderModel> = apiInterface.createOrder(orderModel, "Bearer " + token!!.token)
        call.enqueue(object:Callback<OrderModel>{
            override fun onResponse(call: Call<OrderModel>, response: Response<OrderModel>) {
                if(response.isSuccessful){
                    val intent = Intent(applicationContext, MainProfile::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<OrderModel>, t: Throwable) {
                Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun checkSits(carriage1sit1: Button,
                  carriage1sit2: Button,
                  carriage1sit3: Button,
                  carriage1sit4: Button,
                  carriage1sit5: Button,
                  carriage1sit6: Button,
                  carriage2sit1: Button,
                  carriage2sit2: Button,
                  carriage2sit3: Button,
                  carriage2sit4: Button,
                  carriage2sit5: Button,
                  carriage2sit6: Button,
                  carriage3sit1: Button,
                  carriage3sit2: Button){
        val apiInterface = RequestBuilder.buildRequest().create(API::class.java)
        val trains: TrainsModel? = Paper.book("id").read("id")
        val token: TokenModel? = Paper.book("token").read("token")
        val call: Call<ArrayList<OrderModel>> = apiInterface.checkSits("Bearer " + token!!.token)
        call.enqueue(object:Callback<ArrayList<OrderModel>>{
            override fun onResponse(
                call: Call<ArrayList<OrderModel>>,
                response: Response<ArrayList<OrderModel>>
            ) {
                if(response.isSuccessful){
                    val orderModels = response.body()
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 1 && x.sitOrder.toInt() == 1 }){
                        carriage1sit1.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage1sit1.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 1 && x.sitOrder.toInt() == 2 }){
                        carriage1sit2.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage1sit2.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 1 && x.sitOrder.toInt() == 3 }){
                        carriage1sit3.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage1sit3.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 1 && x.sitOrder.toInt() == 4 }){
                        carriage1sit4.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage1sit4.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 1 && x.sitOrder.toInt() == 5 }){
                        carriage1sit5.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage1sit5.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 1 && x.sitOrder.toInt() == 6 }){
                        carriage1sit6.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage1sit6.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 2 && x.sitOrder.toInt() == 1 }){
                        carriage2sit1.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage2sit1.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 2 && x.sitOrder.toInt() == 2 }){
                        carriage2sit2.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage2sit2.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 2 && x.sitOrder.toInt() == 3 }){
                        carriage2sit3.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage2sit3.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 2 && x.sitOrder.toInt() == 4 }){
                        carriage2sit4.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage2sit4.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 2 && x.sitOrder.toInt() == 5 }){
                        carriage2sit5.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage2sit5.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 2 && x.sitOrder.toInt() == 6 }){
                        carriage2sit6.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage2sit6.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 3 && x.sitOrder.toInt() == 1 }){
                        carriage3sit1.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage3sit1.isEnabled = false
                    }
                    if(orderModels!!.any { x -> x.trainsId == trains!!.idTrains && x.carriageOrder.toInt() == 3 && x.sitOrder.toInt() == 2 }){
                        carriage3sit2.setBackgroundResource(R.drawable.blocked_train_button)
                        carriage3sit2.isEnabled = false
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<OrderModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "Упс, что-то пошло не так", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

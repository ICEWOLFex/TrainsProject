package com.example.trainsandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class OperationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operation)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION


    }
}
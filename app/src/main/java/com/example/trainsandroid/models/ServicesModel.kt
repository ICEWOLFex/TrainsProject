package com.example.trainsandroid.models

data class ServicesModel(
    val idServices: Int,
    val nameServices: String,
    val priceSevices: String
){
    override fun toString(): String {
        return nameServices
    }
}

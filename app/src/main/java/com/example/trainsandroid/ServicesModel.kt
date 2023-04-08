package com.example.trainsandroid

data class ServicesModel(
    val idServices: Int,
    val nameServices: String
){
    override fun toString(): String {
        return nameServices
    }
}

package com.example.trainsandroid.models

data class OrderModel(
    val idOrder: Int,
    val stateOrder: String,
    val dateOrder: String,
    val carriageOrder: String,
    val sitOrder: String,
    val clientId: Int,
    val trainsId: Int,
    val servicesId: Int?
)

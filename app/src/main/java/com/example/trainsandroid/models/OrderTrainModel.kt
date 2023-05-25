package com.example.trainsandroid.models

data class OrderTrainModel(
    var idOrder: Int,
    var numTrain: String,
    var depDayTrain: String,
    var arrDayTrain: String,
    var depCityTrain: String,
    var arrCityTrain: String,
    var carriageOrder: String,
    var sitOrder: String,
    var priceOrder: String,
    var stateOrder: String
)

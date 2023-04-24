package com.example.trainsandroid.models

data class TrainsModel(
    val idTrains: Int,
    val numTrain: String,
    val depDayTrain: String,
    val arrDayTrain: String,
    val depCityTrain: String,
    val arrCityTrain: String,
    val carriageOrder: String,
    val sitOrder: String,
    val priceTrain: String
)

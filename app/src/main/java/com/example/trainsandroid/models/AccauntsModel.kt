package com.example.trainsandroid.models

data class AccauntsModel (val idAccount: Int,
                          val loginAccount: String,
                          val passwordAccount: String,
                          val salt: String,
                          val clientId: Int,
                          val roleId: Int
)
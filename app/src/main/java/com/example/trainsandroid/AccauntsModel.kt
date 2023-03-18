package com.example.trainsandroid

data class AccauntsModel (val idAccount: Int,
                          val loginAccount: String,
                          val passwordAccount: String,
                          val salt: String,
                          val clientId: Int,
                          val roleId: Int
)
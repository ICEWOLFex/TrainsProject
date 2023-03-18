package com.example.trainsandroid

import java.util.*

data class RegistrationModel(
    val idAccountReg: Int,
    val loginAccountReg: String,
    val passwordAccountReg: String,
    val saltReg: String,
    val clientIdReg: Int,
    val roleIdReg: Int,
    val idClientReg: Int,
    val firstnameClientReg: String,
    val nameClientReg: String,
    val lastnameClientReg: String,
    val serPasClientReg: String,
    val numPasClientReg: String,
    val birthdayClientReg: String,
    val recidenceClientReg: String,
    val issuedDayClientReg: String,
    val issuedCodeClientReg: String,
    val phoneClientReg: String,
    val emailClientReg: String
)
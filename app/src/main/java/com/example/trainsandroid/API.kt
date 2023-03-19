package com.example.trainsandroid

import retrofit2.Call
import retrofit2.http.*

interface API {
    @POST("Accounts/sign_in")
    fun authAccaunt(@Body accauntsModel: AccauntsModel):
            Call<TokenModel>

    @POST("Accounts/sign_up")
    fun addAccaunt(@Body registrationModel: RegistrationModel):
            Call<RegistrationModel>

    @GET("Accounts/{id}")
    fun getDataAccount(@Path("id") id: Int, @Header("Authorization") token: String):
            Call<AccauntsModel>

    @GET("Clients/{id}")
    fun getDataClient(@Path("id") id: Int, @Header("Authorization") token: String):
            Call<ClientsModel>

    @GET("Trains")
    fun getTrainsList(@Header("Authorization") token: String):
            Call<ArrayList<TrainsModel>>
}
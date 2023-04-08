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

    @GET("Trains/Search")
    fun getSearchingTrains(@Query ("depCity") depart: String, @Query ("arrCity") arrival:String, @Header("Authorization") token: String):
            Call<ArrayList<TrainsModel>>

    @POST("Orders")
    fun createOrder(@Body orderModel: OrderModel, @Header("Authorization") token: String):
            Call<OrderModel>

    @GET("Services")
    fun getServicesList(@Header("Authorization") token: String):
            Call<ArrayList<ServicesModel>>

    @PUT("Clients/{id}")
    fun updateClientsData(@Path("id") id: Int, @Body clientsModel: ClientsModel, @Header("Authorization") token: String):
            Call<ClientsModel>

    @GET("Orders/ClientsOrder")
    fun getClientsOrders(@Query ("idClient") id: Int, @Header("Authorization") token: String):
            Call<ArrayList<OrderModel>>

    @GET("Orders")
    fun checkSits(@Header("Authorization") token: String):
            Call<ArrayList<OrderModel>>
}
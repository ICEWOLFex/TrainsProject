package com.example.trainsandroid

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestBuilder {
        private var URL = "http://192.168.166.36:5000/api/"
        private var retrofit: Retrofit? = null
        fun buildRequest(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
            retrofit =
                Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient).build()
            return retrofit!!
        }
}
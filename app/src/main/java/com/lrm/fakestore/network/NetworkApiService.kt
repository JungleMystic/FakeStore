package com.lrm.fakestore.network

import com.lrm.fakestore.model.Product
import com.lrm.fakestore.model.Products
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dummyjson.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NetworkApiService {
    @GET("/products")
    suspend fun getData(): Products

    @GET("/products/")
    suspend fun getProductById(@Path("id") id: Int): Product
}

object ProductApi {
    val retrofitService: NetworkApiService by lazy {
        retrofit.create(NetworkApiService::class.java)
    }
}
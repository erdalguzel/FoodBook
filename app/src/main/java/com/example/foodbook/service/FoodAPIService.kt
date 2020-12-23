package com.example.foodbook.service

import com.example.foodbook.model.Food
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class FoodAPIService {

    private val BASE_URL = "https://raw.githubusercontent.com/"

    // No need to add addCallAdapterFactory() if you do not use RxJava
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(FoodAPI::class.java)

    fun getData(): Single<List<Food>> {
        return api.getFood()
    }
}
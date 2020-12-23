package com.example.foodbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.foodbook.model.Food
import com.example.foodbook.service.FoodDAO
import com.example.foodbook.service.FoodDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class FoodDetailViewModel(application: Application) : BaseViewModel(application) {

    val foodLiveData = MutableLiveData<Food>()

    @InternalCoroutinesApi
    fun fetchRoomData(id: Int) {
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            val food = dao.getFoodById(id)
            foodLiveData.value = food
        }
    }
}
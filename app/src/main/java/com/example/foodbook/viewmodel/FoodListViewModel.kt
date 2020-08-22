package com.example.foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodbook.model.Food

class FoodListViewModel : ViewModel() {
    val foodData = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodProgress = MutableLiveData<Boolean>()

    fun refreshData() {
        val banana = Food("Banana", "100", "50", "20", "2", "")
        val strawberry = Food("Strawberry", "200", "70", "40", "4", "")
        val melon = Food("Melon", "300", "90", "60", "6", "")

        foodData.value = listOf(banana, strawberry, melon)
        foodErrorMessage.value = false
        foodProgress.value = false
    }

}
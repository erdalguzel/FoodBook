package com.example.foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodbook.model.Food

class FoodDetailViewModel : ViewModel() {

    val foodLiveData = MutableLiveData<Food>()

    fun fetchJSONData() {
        val banana = Food("Banana", "100", "50", "20", "2", "")
        foodLiveData.value = banana
    }
}
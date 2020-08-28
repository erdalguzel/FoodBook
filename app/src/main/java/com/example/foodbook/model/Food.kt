package com.example.foodbook.model

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("isim")
    val foodName: String?,
    @SerializedName("kalori")
    val foodCalorie: String?,
    @SerializedName("karbonhidrat")
    val foodCarb: String?,
    @SerializedName("protein")
    val foodProtein: String?,
    @SerializedName("yag")
    val foodOil: String,
    @SerializedName("gorsel")
    val foodUrl: String?
) {
    
}
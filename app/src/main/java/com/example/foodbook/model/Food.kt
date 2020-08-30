package com.example.foodbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Food(
    // Name variable of ColumnInfo annotation must **exactly** match the key name of JSON data
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val foodName: String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val foodCalorie: String?,
    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val foodCarb: String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val foodProtein: String?,
    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    val foodOil: String,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val foodUrl: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
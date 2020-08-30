package com.example.foodbook.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodbook.model.Food

@Dao
interface FoodDAO {
    // vararg keyword allows us to insert multiple objects of argument type T
    @Insert
    suspend fun insertAll(vararg food: Food): List<Long>

    @Query("SELECT * FROM Food")
    suspend fun getAllFoods(): List<Food>

    @Query("SELECT * FROM Food WHERE uuid = :foodID")
    suspend fun getFoodById(foodID: Int): Food

    @Query("DELETE FROM Food")
    suspend fun deleteAllFood()
}
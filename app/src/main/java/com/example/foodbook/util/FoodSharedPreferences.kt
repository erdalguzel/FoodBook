package com.example.foodbook.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class FoodSharedPreferences {

    companion object {

        private var sharedPreferences: SharedPreferences? = null
        @Volatile
        private var instance: FoodSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createSharedPreferences(context).also {
                instance = it
            }
        }

        private fun createSharedPreferences(context: Context): FoodSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return FoodSharedPreferences()
        }

    }

    fun saveTime(time: Long) {
        sharedPreferences?.edit(commit = true) {
            putLong("Time", time)
        }
    }
}
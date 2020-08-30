package com.example.foodbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.foodbook.model.Food
import com.example.foodbook.service.FoodAPIService
import com.example.foodbook.service.FoodDatabase
import com.example.foodbook.util.FoodSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class FoodListViewModel(application: Application) : BaseViewModel(application) {
    val foodData = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodProgress = MutableLiveData<Boolean>()
    private val foodSharedPreferences = FoodSharedPreferences(getApplication())

    private val foodApi = FoodAPIService()
    private val disposable = CompositeDisposable()

    @InternalCoroutinesApi
    fun refreshData() {
        fetchJSONData()
    }

    @InternalCoroutinesApi
    private fun fetchJSONData() {
        foodProgress.value = true

        disposable.add(
            foodApi.getData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Food>>() {
                    override fun onSuccess(t: List<Food>) {
                        saveToDatabase(t)
                    }

                    override fun onError(e: Throwable) {
                        foodErrorMessage.value = true
                        foodProgress.value = false
                        e.printStackTrace()
                    }

                }
                ))
    }

    private fun showFoodList(foodList: List<Food>) {
        foodData.value = foodList
        foodProgress.value = false
        foodErrorMessage.value = false
    }

    @InternalCoroutinesApi
    private fun saveToDatabase(foodList: List<Food>) {
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood()
            val uuidList = dao.insertAll(*foodList.toTypedArray())

            var i = 0
            while (i < foodList.size) {
                foodList[i].uuid = uuidList[i].toInt()
                i++
            }

            showFoodList(foodList)
        }
        foodSharedPreferences.saveTime(System.nanoTime())
    }

}
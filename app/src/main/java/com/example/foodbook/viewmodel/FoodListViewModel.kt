package com.example.foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodbook.model.Food
import com.example.foodbook.service.FoodAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FoodListViewModel : ViewModel() {
    val foodData = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodProgress = MutableLiveData<Boolean>()

    private val foodApi = FoodAPIService()
    private val disposable = CompositeDisposable()

    fun refreshData() {
        fetchJSONData()
    }

    private fun fetchJSONData() {
        foodProgress.value = true

        disposable.add(
            foodApi.getData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Food>>() {
                    override fun onSuccess(t: List<Food>) {
                        foodData.value = t
                        foodProgress.value = false
                        foodErrorMessage.value = false
                    }

                    override fun onError(e: Throwable) {
                        foodErrorMessage.value = true
                        foodProgress.value = false
                        e.printStackTrace()
                    }

                }
                ))
    }

}
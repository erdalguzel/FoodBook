package com.example.foodbook.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.foodbook.R
import com.example.foodbook.util.downloadImage
import com.example.foodbook.util.drawPlaceholder
import com.example.foodbook.viewmodel.FoodDetailViewModel
import kotlinx.android.synthetic.main.fragment_food_detail.*
import kotlinx.coroutines.InternalCoroutinesApi

class FoodDetailFragment : Fragment() {

    private lateinit var viewModel: FoodDetailViewModel
    private var foodID: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_detail, container, false)
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let { bundle: Bundle? ->
            foodID = FoodDetailFragmentArgs.fromBundle(bundle!!).foodID
        }

        viewModel = ViewModelProviders.of(this).get(FoodDetailViewModel::class.java)
        viewModel.fetchRoomData(foodID)
        observeData()
    }

    private fun observeData() {
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer { food ->
            food.let { it ->
                foodNameId.text = it.foodName
                foodCalorieId.text = it.foodCalorie
                foodCarbId.text = it.foodCarb
                foodProteinId.text = it.foodProtein
                foodOilId.text = it.foodProtein
                context?.let {
                    foodImageId.downloadImage(food.foodUrl, drawPlaceholder(it))
                }

            }

        })

    }

}
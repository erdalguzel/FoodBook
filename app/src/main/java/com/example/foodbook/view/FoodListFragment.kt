package com.example.foodbook.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodbook.R
import com.example.foodbook.adapters.FoodRecyclerAdapter
import com.example.foodbook.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_food_list.*
import kotlinx.coroutines.InternalCoroutinesApi

class FoodListFragment : Fragment() {

    private lateinit var viewModel: FoodListViewModel
    private var recyclerFoodAdapter = FoodRecyclerAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FoodListViewModel::class.java)
        viewModel.refreshData()

        foodListRecyclerView.layoutManager = LinearLayoutManager(context)
        foodListRecyclerView.adapter = recyclerFoodAdapter

        swipeRefreshLayout.setOnRefreshListener {
            loadingProgressBar.visibility = View.VISIBLE
            foodListRecyclerView.visibility = View.GONE
            errorTextView.visibility = View.GONE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.foodData.observe(viewLifecycleOwner, Observer { observer ->
            observer.let {
                foodListRecyclerView.visibility = View.VISIBLE
                recyclerFoodAdapter.updateFoodList(observer)
            }
        })

        viewModel.foodErrorMessage.observe(viewLifecycleOwner, Observer { error ->
            error.let {
                if (it) {
                    errorTextView.visibility = View.VISIBLE
                    foodListRecyclerView.visibility = View.GONE
                } else {
                    errorTextView.visibility = View.GONE
                }
            }
        })

        viewModel.foodProgress.observe(viewLifecycleOwner, Observer {
            it.let { isLoading ->
                if (isLoading) {
                    foodListRecyclerView.visibility = View.GONE
                    errorTextView.visibility = View.GONE
                    loadingProgressBar.visibility = View.VISIBLE
                } else {
                    loadingProgressBar.visibility = View.GONE
                }
            }
        })
    }
}
package com.guresberatcan.satteliteapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.guresberatcan.satteliteapp.databinding.ActivityListScreenBinding
import com.guresberatcan.satteliteapp.utils.Resource
import com.guresberatcan.satteliteapp.view.adapter.SatelliteListAdapter
import com.guresberatcan.satteliteapp.viewmodel.ListScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListScreenBinding
    private val viewModel: ListScreenViewModel by viewModels()
    private val satelliteAdapter = SatelliteListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeObservers()
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = satelliteAdapter
            satelliteAdapter.itemClickListener = { satellite ->
                TODO("Route to satellite detail")
            }
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    satelliteAdapter.filter(it)
                }
                return false
            }
        })
        populateSatelliteList()
    }

    private fun populateSatelliteList() {
        viewModel.getSatelliteList(
            applicationContext.assets.open("satellite-list.json").bufferedReader()
                .use { it.readText() })
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.satelliteListSharedFlow.collect {
                        when (it) {
                            is Resource.Success -> {
                                hideLoading()
                                satelliteAdapter.items = it.value
                            }

                            is Resource.Loading -> showLoading()
                        }
                    }
                }
            }
        }
    }
}
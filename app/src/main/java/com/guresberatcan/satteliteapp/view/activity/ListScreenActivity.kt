package com.guresberatcan.satteliteapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.guresberatcan.satteliteapp.databinding.ActivityListScreenBinding
import com.guresberatcan.satteliteapp.utils.Resource
import com.guresberatcan.satteliteapp.view.adapter.SatelliteListAdapter
import com.guresberatcan.satteliteapp.viewmodel.ListScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListScreenActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListScreenBinding.inflate(layoutInflater)
    }
    private val viewModel: ListScreenViewModel by viewModels()
    private val satelliteAdapter = SatelliteListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        subscribeObservers()
        initializeViews()
    }

    private fun initializeViews() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = satelliteAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            satelliteAdapter.itemClickListener = { satellite ->
                viewModel.getSatelliteDetail(
                    satellite.id,
                    satellite.name,
                    getSatelliteDetailFilePath()
                )
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

    private fun getSatelliteDetailFilePath() =
        applicationContext.assets.open("satellite-detail.json").bufferedReader()
            .use { it.readText() }


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

                launch {
                    viewModel.satelliteDetailSharedFlow.collect {
                        when (it) {
                            is Resource.Success -> {
                                startActivity(
                                    Intent(
                                        this@ListScreenActivity,
                                        SatelliteDetailActivity::class.java
                                    ).apply {
                                        putExtra("data", it.value)
                                    })
                            }

                            is Resource.Loading -> {}
                        }
                    }
                }
            }
        }
    }
}
package com.guresberatcan.satteliteapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.guresberatcan.satteliteapp.databinding.ActivityListScreenBinding
import com.guresberatcan.satteliteapp.viewmodel.ListScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListScreenBinding
    private val viewModel: ListScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
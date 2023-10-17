package com.guresberatcan.satteliteapp.view.activity

import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.guresberatcan.satteliteapp.R
import com.guresberatcan.satteliteapp.data.model.SatelliteBO
import com.guresberatcan.satteliteapp.databinding.ActivitySatelliteDetailBinding
import com.guresberatcan.satteliteapp.utils.Resource
import com.guresberatcan.satteliteapp.viewmodel.SatelliteDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySatelliteDetailBinding.inflate(layoutInflater)
    }
    private val viewModel: SatelliteDetailViewModel by viewModels()

    private var data: SatelliteBO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getDataFromIntent()
        data?.id?.let { viewModel.getPositions(it, getPositionFilePath()) }
        initializeViews()
        subscribeObservers()
    }

    private fun initializeViews() {
        binding.satelliteName.text = data?.name
        binding.firstFlightDateText.text = data?.firstFlight
        binding.heightMassText.text = SpannableStringBuilder().bold {
            append(applicationContext.resources.getString(R.string.heightMassText))
        }.append(" ").append(data?.height.toString()).append("/").append(data?.mass.toString())
        binding.costText.text = SpannableStringBuilder().bold {
            append(applicationContext.resources.getString(R.string.costText))
        }.append(" ").append(data?.costPerLaunch.toString())
    }

    private fun getDataFromIntent() {
        data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("data", SatelliteBO::class.java)
        } else {
            intent.getParcelableExtra("data")
        }
    }

    private fun getPositionFilePath() =
        applicationContext.assets.open("positions.json").bufferedReader()
            .use { it.readText() }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.positionSharedFlow.collect {
                        when (it) {
                            is Resource.Success -> {
                                binding.lastPositionsText.text = SpannableStringBuilder().bold {
                                    append(applicationContext.resources.getString(R.string.lastPositionsText))
                                }.append(" ").append("(").append(it.value?.posX.toString())
                                    .append(",").append(it.value?.posY.toString()).append(")")
                            }

                            is Resource.Loading -> Unit
                        }
                    }
                }
            }
        }
    }
}
package com.example.cleanarchitecture.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityCustomBinding
import com.example.cleanarchitecture.presentation.viewModel.CustomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class CustomActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityCustomBinding
    private val viewModel: CustomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityCustomBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        initView()
        initListener()
        initObserver()

    }

    private fun initView() {
        dataBinding.viewModel = viewModel
    }

    private fun initListener() {
        dataBinding.btnCustom.setOnClickListener {
            viewModel.fetchData()
        }
    }

    private fun initObserver() {
        viewModel.data.observe(this, Observer { data ->
            dataBinding.tvCustom.text = data?.let {
                val randomIndex = Random.nextInt(0,data.size)
                data[randomIndex].title
            } ?: getString(R.string.no_data_found)
        })
    }
}

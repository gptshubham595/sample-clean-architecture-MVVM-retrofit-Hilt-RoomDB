package com.example.cleanarchitecture.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.model.CustomModel
import com.example.cleanarchitecture.data.model.RepositoryResult
import com.example.cleanarchitecture.domain.usecases.CustomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomViewModel @Inject constructor(private val useCase: CustomUseCase) : ViewModel() {
    private val _data = MutableLiveData<List<CustomModel>>()
    val data = _data as LiveData<List<CustomModel>>
    val TAG = CustomViewModel::class.java.simpleName
    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.fetchData()?.let {
                it.collect(FlowCollector {
                    when (it) {
                        is RepositoryResult.Success -> {
                            it.data?.let { data ->
                                Log.d(TAG, "Success")
                                _data.postValue(data)
                            }
                        }

                        is RepositoryResult.Error -> {
                            Log.d(TAG, "Err")
                        }

                        is RepositoryResult.Loading -> {
                            Log.d(TAG, "Loading")
                        }
                    }
                })
            }
        }
    }

}
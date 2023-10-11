package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.data.api.ApiService
import com.example.cleanarchitecture.data.repository.CustomRepository
import com.example.cleanarchitecture.domain.usecases.CustomUseCase
import com.example.cleanarchitecture.presentation.viewModel.CustomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val koinModule = module {
    viewModel { CustomViewModel(get()) }
    single { CustomUseCase(get()) }
    single {CustomRepository(get(), get())}
}

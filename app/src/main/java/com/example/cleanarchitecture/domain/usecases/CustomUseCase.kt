package com.example.cleanarchitecture.domain.usecases

import com.example.cleanarchitecture.data.model.CustomModel
import com.example.cleanarchitecture.data.model.RepositoryResult
import com.example.cleanarchitecture.data.repository.CustomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomUseCase @Inject constructor(private val repository: CustomRepository) {
    suspend fun fetchData(): Flow<RepositoryResult<List<CustomModel>>>/*RepositoryResult<List<CustomModel>>*/ {
        return repository.fetchData()
    }
}
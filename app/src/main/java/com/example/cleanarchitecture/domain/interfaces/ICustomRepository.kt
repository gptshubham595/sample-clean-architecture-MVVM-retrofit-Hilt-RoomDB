package com.example.cleanarchitecture.domain.interfaces

import com.example.cleanarchitecture.data.model.CustomModel
import com.example.cleanarchitecture.data.model.RepositoryResult
import kotlinx.coroutines.flow.Flow

interface ICustomRepository {

    suspend fun fetchData(): Flow<RepositoryResult<List<CustomModel>>>

}
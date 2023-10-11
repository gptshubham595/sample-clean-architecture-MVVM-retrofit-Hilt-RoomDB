package com.example.cleanarchitecture.data.repository

import android.util.Log
import com.example.cleanarchitecture.data.api.ApiService
import com.example.cleanarchitecture.data.database.CustomModelDao
import com.example.cleanarchitecture.data.model.CustomModel
import com.example.cleanarchitecture.data.model.RepositoryResult
import com.example.cleanarchitecture.domain.interfaces.ICustomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CustomRepository @Inject constructor(
    private val apiService: ApiService,
    private val customModelDao: CustomModelDao
) : ICustomRepository {

    override suspend fun fetchData(): Flow<RepositoryResult<List<CustomModel>>> /*RepositoryResult<List<CustomModel>>*/ =
        flow<RepositoryResult<List<CustomModel>>> {
            /*return@withContext */try {
            val cachedData = customModelDao.fetchData()
            if (cachedData.isNotEmpty()) {
                Log.d("CachedData", "$cachedData")
                emit(RepositoryResult.Success(cachedData))
            } else {
                val newData = apiService.fetchData("")
                newData.forEach {
                    customModelDao.insertData(it)
                }
                emit(RepositoryResult.Success(newData))
            }
        } catch (e: Exception) {
            emit(RepositoryResult.Error(e))
        }

        }.onStart {
            emit(RepositoryResult.Loading)
        }.catch {
            emit(RepositoryResult.Error(Throwable("")))
        }.flowOn(Dispatchers.IO)

}
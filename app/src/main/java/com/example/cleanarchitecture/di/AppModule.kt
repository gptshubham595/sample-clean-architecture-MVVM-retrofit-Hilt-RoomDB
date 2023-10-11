package com.example.cleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.Utils.APIConstants.Companion.BASE_URl
import com.example.cleanarchitecture.data.api.ApiService
import com.example.cleanarchitecture.data.database.CustomDataBase
import com.example.cleanarchitecture.data.database.CustomModelDao
import com.example.cleanarchitecture.data.repository.CustomRepository
import com.example.cleanarchitecture.domain.usecases.CustomUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl(BASE_URl)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCustomRepository(
        apiService: ApiService,
        customModelDao: CustomModelDao
    ): CustomRepository {
        return CustomRepository(apiService, customModelDao)
    }

    @Provides
    @Singleton
    fun provideCustomModelDao(dataBase: CustomDataBase): CustomModelDao {
        return dataBase.customModelDao()
    }

    @Provides
    @Singleton
    fun provideCustomDataBase(@ApplicationContext context: Context): CustomDataBase {
        return Room.databaseBuilder(context, CustomDataBase::class.java, "custom_database").build()
    }

    @Provides
    @Singleton
    fun provideCustomUseCase(respository: CustomRepository): CustomUseCase {
        return CustomUseCase(respository)
    }

}
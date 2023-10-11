package com.example.cleanarchitecture.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitecture.data.model.CustomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomModelDao {

    @Query("SELECT * FROM custom_table")
    fun fetchData(): List<CustomModel>

    @Insert
    fun insertData(data: CustomModel)
}
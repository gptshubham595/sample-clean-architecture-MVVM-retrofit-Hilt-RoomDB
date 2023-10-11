package com.example.cleanarchitecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.model.CustomModel

@Database(entities = [CustomModel::class], version = 1)
abstract class CustomDataBase : RoomDatabase() {
    abstract fun customModelDao(): CustomModelDao
}
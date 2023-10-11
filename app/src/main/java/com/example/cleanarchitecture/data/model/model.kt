package com.example.cleanarchitecture.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.lang.Exception

@Entity(tableName = "custom_table")
data class CustomModel(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
)

sealed class RepositoryResult<out T> {
    data class Success<out T>(val data: T) : RepositoryResult<T>()
    data object Loading: RepositoryResult<Nothing>()
    data class Error(val exception: Throwable) : RepositoryResult<Nothing>()
}
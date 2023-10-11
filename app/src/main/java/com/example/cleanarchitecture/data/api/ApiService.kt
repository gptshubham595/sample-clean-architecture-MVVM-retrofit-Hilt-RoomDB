package com.example.cleanarchitecture.data.api

import com.example.cleanarchitecture.Utils.APIConstants
import com.example.cleanarchitecture.Utils.Constant
import com.example.cleanarchitecture.data.model.CustomModel
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET(APIConstants.API_FETCH_DATA)
    suspend fun fetchData(
        @Header(Constant.HEADER) header: String
    ): List<CustomModel>

}
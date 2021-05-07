package com.example.i2e1task.data.api

import com.example.i2e1task.data.model.UsersApiResponse
import com.example.i2e1task.utils.DEFAULT_PAGE_SIZE
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET("users")
    fun getUsers(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = DEFAULT_PAGE_SIZE
    ): Single<UsersApiResponse>
}

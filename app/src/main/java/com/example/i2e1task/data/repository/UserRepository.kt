package com.example.i2e1task.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.i2e1task.data.api.ServiceBuilder
import com.example.i2e1task.data.api.UserApiService
import com.example.i2e1task.data.model.User
import com.example.i2e1task.utils.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlin.LazyThreadSafetyMode.NONE


class UserRepository {
    private val userApiService: UserApiService by lazy(NONE) {
        ServiceBuilder.buildService(UserApiService::class.java)
    }

    fun getUsers(): Flow<PagingData<User>> =
        Pager(
            PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            )
        ) {
            UserDataSource(userApiService)
        }.flow
}
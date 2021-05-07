package com.example.i2e1task.data.model

data class UsersApiResponse(
    val status: Boolean,
    val message: String,
    val data: UsersData
)
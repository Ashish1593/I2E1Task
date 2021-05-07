package com.example.i2e1task.data.model
import com.google.gson.annotations.SerializedName

data class UsersData(
    val users: List<User>,
    @SerializedName("has_more")
    val hasMore: Boolean
)
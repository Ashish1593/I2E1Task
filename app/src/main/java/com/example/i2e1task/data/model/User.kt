package com.example.i2e1task.data.model
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val userImage: String,
    @SerializedName("items")
    val galleryImages: List<String>
)
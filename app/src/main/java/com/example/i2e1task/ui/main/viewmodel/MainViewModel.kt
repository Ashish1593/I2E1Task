package com.example.i2e1task.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.i2e1task.data.model.User
import com.example.i2e1task.data.repository.UserRepository

import kotlinx.coroutines.flow.Flow

class MainViewModel(
    userRepository: UserRepository
) : ViewModel() {
    val users: Flow<PagingData<User>> = userRepository.getUsers()
}
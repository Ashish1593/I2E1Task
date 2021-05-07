package com.example.i2e1task.ui.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.i2e1task.data.repository.UserRepository
import com.example.i2e1task.ui.main.adapter.MainAdapter
import com.example.i2e1task.ui.main.viewmodel.MainViewModel
import com.example.i2e1task.databinding.ActivityMainBinding
import com.example.i2e1task.ui.main.viewmodel.MainViewModelFactory


import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(UserRepository())
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        mAdapter = MainAdapter()
        with(binding.recyclerView) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launch {
            viewModel.users.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }
}

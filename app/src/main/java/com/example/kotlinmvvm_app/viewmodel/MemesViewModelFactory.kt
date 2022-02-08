package com.example.kotlinmvvm_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvm_app.repository.MemesRepository

class MemesViewModelFactory(private val memesRepo:MemesRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MemesViewModel(memesRepo) as T
    }
}
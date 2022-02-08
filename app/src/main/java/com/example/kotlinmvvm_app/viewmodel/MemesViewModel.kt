package com.example.kotlinmvvm_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvm_app.model.Jokes
import com.example.kotlinmvvm_app.repository.MemesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MemesViewModel(private val memesRepos:MemesRepository):ViewModel() {
    val memesLiveData:LiveData<Jokes>
    get() = memesRepos.memes

    //state flow
    private val memesStateFlow= MutableStateFlow(Jokes())
    val stateFlow: MutableStateFlow<Jokes> get() = memesStateFlow


    init {
        viewModelScope.launch (Dispatchers.IO){
          //  memesRepos.getMemes()
            memesRepos.getMemesFlowData().collect{
                memesStateFlow.value=it
            }
        }

    }





}
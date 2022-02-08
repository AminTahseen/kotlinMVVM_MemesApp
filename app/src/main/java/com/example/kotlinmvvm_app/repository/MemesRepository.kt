package com.example.kotlinmvvm_app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinmvvm_app.api.RetrofitApiInterface
import com.example.kotlinmvvm_app.model.Data
import com.example.kotlinmvvm_app.model.Jokes
import kotlinx.coroutines.flow.*

class MemesRepository(private val apiInterface:RetrofitApiInterface) {

    // live data
    private val memesLiveData=MutableLiveData<Jokes>()
    val memes:LiveData<Jokes>
    get()=memesLiveData

    suspend fun getMemes(){
        val result=apiInterface.getJokes()
        if(result.body()!=null) memesLiveData.postValue(result.body())
    }

    // Flow
    suspend fun getMemesFlowData(): Flow<Jokes> {
        return flow<Jokes>{
            val result=apiInterface.getJokes()
            result.body()?.let { emit(it) }
        }
    }

}
package com.example.kotlinmvvm_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvm_app.adapter.MemesAdapter
import com.example.kotlinmvvm_app.api.ApiUtilities
import com.example.kotlinmvvm_app.api.RetrofitApiInterface
import com.example.kotlinmvvm_app.model.Meme
import com.example.kotlinmvvm_app.repository.MemesRepository
import com.example.kotlinmvvm_app.viewmodel.MemesViewModel
import com.example.kotlinmvvm_app.viewmodel.MemesViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var memesViewModel: MemesViewModel
    private lateinit var recyclerview:RecyclerView
    private lateinit var apiInterface:RetrofitApiInterface
    private lateinit var memesData:ArrayList<Meme>
    private lateinit var memesRepository:MemesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linkXML()
        initUI()

//        memesViewModel.memesLiveData.observe(this,{
//            it.data?.memes?.iterator()?.forEach { it ->
//                memesData.add(it);
//            }
//            val adapter =MemesAdapter(memesData,this)
//            recyclerview.adapter=adapter
//        })

        GlobalScope.launch {
          memesViewModel.stateFlow.collect{
              Log.d("FLOWDATA",it.toString())
              it.data?.memes?.iterator()?.forEach { it ->
                memesData.add(it);
              }
              runOnUiThread {
                  val adapter =MemesAdapter(memesData,this@MainActivity)
                  recyclerview.adapter=adapter
              }
          }
        }
    }

    private fun linkXML(){
        recyclerview = findViewById(R.id.memesRecycler)
        recyclerview.layoutManager = LinearLayoutManager(this)
    }
    private fun initUI(){
        memesData = ArrayList()
        apiInterface=ApiUtilities.getInstance().create(RetrofitApiInterface::class.java)
        memesRepository=MemesRepository(apiInterface)
        memesViewModel= ViewModelProvider(this, MemesViewModelFactory(memesRepository))[MemesViewModel::class.java]
    }
}
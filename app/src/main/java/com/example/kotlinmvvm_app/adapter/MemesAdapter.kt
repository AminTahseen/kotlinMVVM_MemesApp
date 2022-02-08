package com.example.kotlinmvvm_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinmvvm_app.R
import com.example.kotlinmvvm_app.model.Meme

class MemesAdapter(private val memesList: List<Meme>,private val context:Context) : RecyclerView.Adapter<MemesAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.meme_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val memeItem=memesList[position]
        holder.memeContent.text=memeItem.name
        Glide.with(context)
            .load(memeItem.url)
            .override(memeItem.width, memeItem.height)
            .into(holder.memeImage)

    }

    override fun getItemCount(): Int {
        return memesList.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val memeContent:TextView=itemView.findViewById(R.id.memeContent)
        val memeImage:ImageView=itemView.findViewById(R.id.memeImage)
    }
}
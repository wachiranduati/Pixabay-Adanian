package com.example.pixabay_adanian.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pixabay_adanian.R

class PixaBayAdapter(private val images: List<String>) : RecyclerView.Adapter<PixaBayAdapter.MyViewHolder>() {
    private val adanianImage = "https://static.wixstatic.com/media/097f77_3f22812242f64acea91972fc17343420~mv2.jpg/v1/fill/w_2500,h_2500,al_c/097f77_3f22812242f64acea91972fc17343420~mv2.jpg"

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagePlaceHolder : ImageView = view.findViewById(R.id.ImagePreviewsImageView)
        val author : TextView = view.findViewById(R.id.attribution_TextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.pixabay_image_recycler_item_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currImage = images[position]
        holder.author.text = "$position"
        Glide.with(holder.author.context).load(adanianImage).into(holder.imagePlaceHolder)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}
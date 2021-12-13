package com.example.pixabay_adanian.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pixabay_adanian.R
import com.example.pixabay_adanian.models.Hit

class PixaBayAdapter(private var images: List<Hit>) : RecyclerView.Adapter<PixaBayAdapter.MyViewHolder>() {
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
        val currImage = images[position]
        holder.author.text = currImage.user
        holder.imagePlaceHolder.layoutParams.height = currImage.imageHeight/3 //reseting the image height to create the staggered effect - 3 is likeable
        Glide.with(holder.author.context).load(currImage.webformatURL)
            .error(adanianImage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imagePlaceHolder)
        //implemented the diskcachestrategy to ensure all the images are saved to disk especially seeing as the dog result is on by default
        val bundle = bundleOf("Hit" to images[position])
        holder.imagePlaceHolder.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainScreenFragment_to_imageDetailsFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun changeDataSet(searchData : List<Hit>){
        images = searchData
    }
}
package com.example.pixabay_adanian.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pixabay_adanian.R
import com.example.pixabay_adanian.models.Hit

class ImageDetailsFragment : Fragment() {
    lateinit var currentImage : Hit
    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_image_details, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentImage = arguments?.getParcelable("Hit")!!
        populateView(currentImage)
    }

    private fun populateView(currentImage: Hit) {
        Glide.with(root.context).load(currentImage.webformatURL).into(root.findViewById(R.id.imageInfoImageView))
        root.findViewById<TextView>(R.id.collectionsTextView).text = "Collections: ${currentImage.collections}"
        root.findViewById<TextView>(R.id.commentsTextView).text = "Comments: ${currentImage.comments}"
        root.findViewById<TextView>(R.id.downloadsTextView).text = "Downloads: ${currentImage.downloads}"
        root.findViewById<TextView>(R.id.idTextView).text = "ID: ${currentImage.id}"
        root.findViewById<TextView>(R.id.imageHeightTextView).text = "ImageHeight: ${currentImage.imageHeight}"
        root.findViewById<TextView>(R.id.imageSizeTextView).text = "ImageSize: ${currentImage.imageSize}"
        root.findViewById<TextView>(R.id.imageWidthTextView).text = "ImageWidth: ${currentImage.imageWidth}"
        root.findViewById<TextView>(R.id.largeImageURLTextView).text = "LargeImageURL: ${currentImage.largeImageURL}"
        root.findViewById<TextView>(R.id.likesTextView).text = "Likes: ${currentImage.likes}"
        root.findViewById<TextView>(R.id.pageURLTextView).text = "PageURL: ${currentImage.pageURL}"
        root.findViewById<TextView>(R.id.previewHeightTextView).text = "PreviewHeight: ${currentImage.previewHeight}"
        root.findViewById<TextView>(R.id.previewURLTextView).text = "PreviewURL: ${currentImage.previewURL}"
        root.findViewById<TextView>(R.id.previewWidthTextView).text = "PreviewWidth: ${currentImage.previewWidth}"
        root.findViewById<TextView>(R.id.tagsTextView).text = "Tags: ${currentImage.tags}"
        root.findViewById<TextView>(R.id.typeTextView).text = "Type: ${currentImage.type}"
        root.findViewById<TextView>(R.id.userTextView).text = "User: ${currentImage.user}"
        root.findViewById<TextView>(R.id.userImageURLTextView).text = "UserImageURL: ${currentImage.userImageURL}"
        root.findViewById<TextView>(R.id.user_idTextView).text = "User_id: ${currentImage.user_id}"
        root.findViewById<TextView>(R.id.viewsTextView).text = "View: ${currentImage.views}"
        root.findViewById<TextView>(R.id.webformatHeightTextView).text = "WebFormatHeight: ${currentImage.webformatHeight}"
        root.findViewById<TextView>(R.id.webformatURLTextView).text = "WebFormatURL: ${currentImage.webformatURL}"
        root.findViewById<TextView>(R.id.webformatWidthTextView).text = "WebFormatWidth: ${currentImage.webformatWidth}"
    }
}
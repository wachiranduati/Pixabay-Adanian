package com.example.pixabay_adanian.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pixabay_adanian.R
import com.example.pixabay_adanian.adapters.PixaBayAdapter

class MainScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclMain : RecyclerView = view.findViewById(R.id.mainRecyclerView)
        val adp = PixaBayAdapter(listOf("1","2","3","4","5","6","7","8","9","10"))
        val lytmng = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclMain.apply {
            adapter = adp
            setHasFixedSize(true)
            layoutManager = lytmng
        }
    }

}
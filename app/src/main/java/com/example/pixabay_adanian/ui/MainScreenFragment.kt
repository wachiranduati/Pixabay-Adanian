package com.example.pixabay_adanian.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pixabay_adanian.R
import com.example.pixabay_adanian.adapters.PixaBayAdapter
import com.example.pixabay_adanian.api.PixabayApiRetriever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import timber.log.Timber

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
        GlobalScope.launch(Dispatchers.IO) {
            val call = PixabayApiRetriever().retrieveSearchResults()
            //todo handle network call issues i.e server error or app offline
            GlobalScope.launch(Dispatchers.Main) {
//                Toast.makeText(activity, "data retreived ${call.total}", Toast.LENGTH_SHORT).show()
                val adp = PixaBayAdapter(call.hits)
                val lytmng = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                recyclMain.apply {
                    adapter = adp
                    setHasFixedSize(true)
                    layoutManager = lytmng
                }
            }
        }
    }

}
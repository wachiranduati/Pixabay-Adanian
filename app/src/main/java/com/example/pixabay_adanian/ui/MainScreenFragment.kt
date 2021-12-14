package com.example.pixabay_adanian.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pixabay_adanian.R
import com.example.pixabay_adanian.adapters.PixaBayAdapter
import com.example.pixabay_adanian.api.PixabayApiRetriever
import com.example.pixabay_adanian.models.Hit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import timber.log.Timber

class MainScreenFragment : Fragment() {
    var pixabayResults = ArrayList<Hit>()
    lateinit var recyclMain : RecyclerView
    lateinit var adp : PixaBayAdapter
    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_main_screen, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclMain = view.findViewById(R.id.mainRecyclerView)
        val searchVw = view.findViewById<SearchView>(R.id.searchView)
        val searchVal = if(searchVw.query.toString().isNotEmpty()) searchVw.query.toString() else "dog"
        //todo handle back navigation after search to cache previously searched term

        GlobalScope.launch(Dispatchers.IO) {
            //to preserve back navigation on the proper search term as from default...check whether searchview has a value and use it instead else fall back on the default
            val call = PixabayApiRetriever().retrieveSearchResults(searchVal)
            pixabayResults = call.hits as ArrayList<Hit>
            //todo handle network call issues i.e server error or app offline
            GlobalScope.launch(Dispatchers.Main) {
                adp = PixaBayAdapter(pixabayResults)
                val lytmng = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                recyclMain.apply {
                    adapter = adp
                    setHasFixedSize(true)
                    layoutManager = lytmng
                }
            }
        }
        searchVw.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                GlobalScope.launch(Dispatchers.IO) {
                    val call = query?.let { PixabayApiRetriever().retrieveSearchResults(it) }
                    GlobalScope.launch(Dispatchers.Main) {
                        pixabayResults.clear()
                        if (call != null) {
                            pixabayResults = call.hits as ArrayList<Hit>
                            adp.changeDataSet(pixabayResults)
                            adp.notifyDataSetChanged()
                        }
                    }
                }
                return true
            }
        })
    }

}
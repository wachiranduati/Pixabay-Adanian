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
import com.example.pixabay_adanian.models.PixaResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Dispatcher
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

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

        val call = PixabayApiRetriever().retrieveSearchResults(searchVal)
        call.enqueue(object : retrofit2.Callback<PixaResponse>{
            override fun onResponse(
                call: retrofit2.Call<PixaResponse>,
                response: retrofit2.Response<PixaResponse>
            ) {
                pixabayResults = response.body()?.hits as ArrayList<Hit>
                adp = PixaBayAdapter(pixabayResults)
                val lytmng = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                recyclMain.apply {
                    adapter = adp
                    setHasFixedSize(true)
                    layoutManager = lytmng
                }
            }

            override fun onFailure(call: retrofit2.Call<PixaResponse>, t: Throwable) {
                Snackbar.make(recyclMain, t.localizedMessage, Snackbar.LENGTH_SHORT).show()
            }

        })

        searchVw.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    val call = PixabayApiRetriever().retrieveSearchResults(query)
                    call.enqueue(object : retrofit2.Callback<PixaResponse>{
                        override fun onResponse(
                            call: retrofit2.Call<PixaResponse>,
                            response: retrofit2.Response<PixaResponse>
                        ) {
                            pixabayResults.clear()
                            pixabayResults = response.body()?.hits as ArrayList<Hit>
                            adp.changeDataSet(pixabayResults)
                            adp.notifyDataSetChanged()
                        }

                        override fun onFailure(call: retrofit2.Call<PixaResponse>, t: Throwable) {
                            Snackbar.make(recyclMain, t.localizedMessage, Snackbar.LENGTH_SHORT).show()
                        }

                    })

                }
                return true
            }
        })
    }

}
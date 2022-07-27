package org.sp.paging_example_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

//https://medium.com/swlh/paging3-recyclerview-pagination-made-easy-333c7dfa8797
class MainActivity : AppCompatActivity() {

    private lateinit var rvDoggoRemote: RecyclerView
    private lateinit var remoteViewModel: PhotoViewModel
    private lateinit var adapter: PagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = PagingAdapter()
        remoteViewModel = getPhotoViewModel(this)
        setUpViews()
        remoteViewModel.fetchPhotoLiveData().observe(this, Observer {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }

    private fun setUpViews() {
        rvDoggoRemote = findViewById(R.id.rvDoggoRemote)
        rvDoggoRemote.layoutManager = LinearLayoutManager(this)
        rvDoggoRemote.adapter = adapter
    }
}
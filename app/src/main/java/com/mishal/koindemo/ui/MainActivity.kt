package com.mishal.koindemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mishal.koindemo.data.model.Repo
import com.mishal.koindemo.adapters.ListPostAdapter
import com.mishal.koindemo.BR
import com.mishal.koindemo.R
import com.mishal.koindemo.data.NetworkUtils
import com.mishal.koindemo.databinding.ActivityMainBinding
import com.mishal.koindemo.utils.EndlessRecyclerViewScrollListener
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ListPostAdapter
    private val listPostViewModel: ListPostViewModel by viewModel()
    lateinit var binding: ActivityMainBinding
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        adapter = ListPostAdapter()
        binding.rvList.adapter = adapter
        listPostViewModel.fetchRepos()
        observeData()

        scrollListener = object :
            EndlessRecyclerViewScrollListener(binding.rvList.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                listPostViewModel.fetchRepos()
            }

        }
        binding.rvList.addOnScrollListener(scrollListener)

    }

    private fun observeData() {
        if (!NetworkUtils.isInternetAvailable(this)) {
            Toast.makeText(
                this@MainActivity,
                "Please check your internet connection",
                Toast.LENGTH_LONG
            ).show()
            return
        }
        listPostViewModel.getRepos().observe(this, Observer<Repo> {
            if (it != null) {
                binding.setVariable(BR.isError, false)
                binding.setVariable(BR.isLoading, false)
                adapter.addPostData(it.userArray)
                adapter.setCustomClickListener(object : ListPostAdapter.CustomClickListener {
                    override fun onClick(view: View, position: Int) {
                        Toast.makeText(this@MainActivity, position.toString(), Toast.LENGTH_LONG)
                            .show()
                    }

                })
            }
        })


        listPostViewModel.getLoading().observe(this, Observer<Boolean> {
            if (it != null) {
                binding.setVariable(BR.isError, false)
                binding.setVariable(BR.isLoading, true)
            }
        })

        listPostViewModel.getError().observe(this, Observer<Boolean> {
            if (it != null) {
                binding.setVariable(BR.isError, it)
                binding.setVariable(BR.isLoading, false)
            }
        })
    }

}
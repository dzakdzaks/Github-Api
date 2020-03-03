package com.dzakdzaks.github_api.ui

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzakdzaks.github_api.R
import com.dzakdzaks.github_api.base.ViewModelActivity
import com.dzakdzaks.github_api.common.toast
import com.dzakdzaks.github_api.entity.entities.Users
import com.utsman.recycling.setupAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_users.view.*

class MainActivity : ViewModelActivity(), SearchView.OnQueryTextListener {

    private val viewModel: MainActivityViewModel by injectViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView.setOnQueryTextListener(this)

        observeLoading()
        observeMessage()
    }

    private fun observeMessage() = this.viewModel.message.observe(this) { toast(it) }

    private fun observeLoading() = this.viewModel.isLoading().observe(this) {
        if (it) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun setupRecycler(q: String?) {
        list.setupAdapter<Users>(R.layout.item_users) { adapter, context, list ->

            bind { itemView, position, item ->
                itemView.tv.text = item?.login
            }

            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            setLayoutManager(linearLayoutManager)

            this@MainActivity.viewModel.searchUsers(q!!).observe(this@MainActivity) {
                submitList(it)
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        setupRecycler(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        setupRecycler(newText)
        return false
    }
}

package com.dzakdzaks.github_api.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.request.RequestOptions
import com.dzakdzaks.github_api.R
import com.dzakdzaks.github_api.base.ViewModelActivity
import com.dzakdzaks.github_api.common.GlideApp
import com.dzakdzaks.github_api.common.toast
import com.dzakdzaks.github_api.entity.entities.Users
import com.dzakdzaks.github_api.ui.user_detail.UserDetailActivity
import com.utsman.recycling.extentions.Recycling
import com.utsman.recycling.setupAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_users.view.*


class MainActivity : ViewModelActivity(), SearchView.OnQueryTextListener {

    private val viewModel: MainActivityViewModel by injectViewModels()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerFetch()
        observeMessage()
    }

    private fun observeMessage() = this.viewModel.message.observe(this) { toast(it) }

    private fun setupRecyclerSearch(q: String?) {
        list.setupAdapter<Users>(R.layout.item_users) { adapter, context, list ->

            bind { itemView, position, item ->
                itemView.tvName.text = item?.login
                GlideApp.with(itemView.imageProfile.context)
                    .load(item?.avatarUrl)
                    .error(
                        ContextCompat.getDrawable(
                            itemView.imageProfile.context,
                            R.drawable.ic_launcher_background
                        )
                    )
                    .apply(RequestOptions().circleCrop())
                    .into(itemView.imageProfile)
                itemView.parentLayout.setOnClickListener {
                    val intent = Intent(Intent(this@MainActivity, UserDetailActivity::class.java))
                    intent.putExtra("user", item)
                    startActivity(intent)
                }
            }

            addLoader(R.layout.item_loader) {
                idLoader = R.id.progress_circular
                idTextError = R.id.error_text_view
            }

            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            setLayoutManager(linearLayoutManager)

            setSearchData(this, q!!, 1)

            onPagingListener(linearLayoutManager) { page, itemCount ->
                setSearchData(this@setupAdapter, q, page + 1)
            }

        }
    }

    private fun setSearchData(recycling: Recycling<Users>, q: String?, page: Int) {
        this.viewModel.searchUsers(q!!, page, 10).observe(this) {
            recycling.submitList(it)
        }

        this.viewModel.networkState().observe(this) {
            recycling.submitNetworkState(it)
        }
    }

    private fun setupRecyclerFetch() {
        list.setupAdapter<Users>(R.layout.item_users) { adapter, context, list ->
            bind { itemView, position, item ->
                itemView.tvName.text = item?.login
                GlideApp.with(itemView.imageProfile.context)
                    .load(item?.avatarUrl)
                    .error(
                        ContextCompat.getDrawable(
                            itemView.imageProfile.context,
                            R.drawable.ic_launcher_background
                        )
                    )
                    .apply(RequestOptions().circleCrop())
                    .into(itemView.imageProfile)
                itemView.parentLayout.setOnClickListener {
                    val intent = Intent(Intent(this@MainActivity, UserDetailActivity::class.java))
                    intent.putExtra("user", item)
                    startActivity(intent)
                }
            }

            addLoader(R.layout.item_loader) {
                idLoader = R.id.progress_circular
                idTextError = R.id.error_text_view
            }

            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            setLayoutManager(linearLayoutManager)

            setFetchData(this)

            onPagingListener(linearLayoutManager) { page, itemCount ->
                setFetchData(this@setupAdapter)
            }
        }
    }

    private fun setFetchData(recycling: Recycling<Users>) {
        this.viewModel.fetchUsers().observe(this) {
            recycling.submitList(it)
        }

        this.viewModel.networkState().observe(this) {
            recycling.submitNetworkState(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.searchBar)

        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search People"
        searchView.setOnQueryTextListener(this)
        searchView.isIconified = false
        searchView.maxWidth = Int.MAX_VALUE

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        setupRecyclerSearch(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        setupRecyclerSearch(newText)
        return false
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
        } else {
            super.onBackPressed()
        }
    }
}

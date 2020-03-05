package com.dzakdzaks.github_api.ui.main

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.request.RequestOptions
import com.dzakdzaks.github_api.R
import com.dzakdzaks.github_api.base.ViewModelActivity
import com.dzakdzaks.github_api.common.GlideApp
import com.dzakdzaks.github_api.common.NetworkChangesReceiver
import com.dzakdzaks.github_api.entity.entities.Users
import com.dzakdzaks.github_api.ui.user_detail.UserDetailActivity
import com.google.android.material.snackbar.Snackbar
import com.utsman.recycling.extentions.Recycling
import com.utsman.recycling.setupAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_users.view.*


class MainActivity : ViewModelActivity(), SearchView.OnQueryTextListener,
    NetworkChangesReceiver.NetworkChangesCallback {

    private val viewModel: MainActivityViewModel by injectViewModels()
    private lateinit var searchView: SearchView
    private val networkChangesReceiver = NetworkChangesReceiver(this)
    private var q = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerFetch()
        observeMessageSearch()
        observeMessageFetch()
    }

    private fun observeMessageSearch() = this.viewModel.messageSearch.observe(this) {
        Snackbar.make(parentMain, it, Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry", View.OnClickListener {
                setupRecyclerSearch(q)
            }).show()
    }

    private fun observeMessageFetch() = this.viewModel.messageFetch.observe(this) {
        Snackbar.make(parentMain, it, Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry", View.OnClickListener {
                setupRecyclerFetch()
            }).show()
    }

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
        searchView.isIconified = true
        searchView.maxWidth = Int.MAX_VALUE

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        this.q = query!!
        if (query != "") {
            setupRecyclerSearch(query)
        } else {
            setupRecyclerFetch()
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        this.q = newText!!
        if (newText != "") {
            setupRecyclerSearch(newText)
        } else {
            setupRecyclerFetch()
        }
        return false
    }

    override fun onNetworkChanged(isOnline: Boolean) {
        if (!isOnline) {
            Snackbar.make(
                parentMain,
                "No Internet Connection\nPlease Check Your Internet Connection",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter: IntentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangesReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(networkChangesReceiver)
    }
}

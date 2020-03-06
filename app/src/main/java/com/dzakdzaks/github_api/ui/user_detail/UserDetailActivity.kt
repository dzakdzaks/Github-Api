package com.dzakdzaks.github_api.ui.user_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.observe
import com.bumptech.glide.request.RequestOptions
import com.dzakdzaks.github_api.R
import com.dzakdzaks.github_api.base.ViewModelActivity
import com.dzakdzaks.github_api.common.GlideApp
import com.dzakdzaks.github_api.common.toast
import kotlinx.android.synthetic.main.activity_user_detail.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("SetTextI18n")
class UserDetailActivity : ViewModelActivity() {

    private val viewModel: UserDetailViewModel by injectViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val username = intent.getStringExtra("username")
        setUserDetail(username ?: "dzakdzaks")
        observeLoading()
        observeMessage()
    }

    private fun observeMessage() = this.viewModel.message.observe(this) { toast(it) }

    private fun observeLoading() = this.viewModel.isLoading.observe(this) {
        if (it) {
            progressBarDetailUser.visibility = View.VISIBLE
        } else {
            progressBarDetailUser.visibility = View.GONE
        }
    }

    private fun setUserDetail(username: String) {
        this.viewModel.fetchUserDetail(username).observe(this) {
            GlideApp.with(imageProfileDetail.context)
                .load(it.avatarUrl)
                .error(
                    ContextCompat.getDrawable(
                        imageProfileDetail.context,
                        R.drawable.ic_launcher_background
                    )
                )
                .apply(RequestOptions().circleCrop())
                .into(imageProfileDetail)

            tvNameDetail.text = "${it.name} ( ${it.login} )"
            tvBioDetail.text = it.bio
        }
    }
}

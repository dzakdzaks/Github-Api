package com.dzakdzaks.github_api.ui.user_detail

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.request.RequestOptions
import com.dzakdzaks.github_api.R
import com.dzakdzaks.github_api.base.ViewModelActivity
import com.dzakdzaks.github_api.common.GlideApp
import com.dzakdzaks.github_api.entity.entities.Users
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : ViewModelActivity() {

    private val viewModel: UserDetailViewModel by injectViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val user = intent.getParcelableExtra<Users>("user")
        tvNameDetail.text = user?.login
        GlideApp.with(imageProfileDetail.context)
            .load(user?.avatarUrl)
            .error(
                ContextCompat.getDrawable(
                    imageProfileDetail.context,
                    R.drawable.ic_launcher_background
                )
            )
            .apply(RequestOptions().circleCrop())
            .into(imageProfileDetail)

    }
}

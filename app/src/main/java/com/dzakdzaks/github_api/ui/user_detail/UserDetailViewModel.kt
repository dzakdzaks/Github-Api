package com.dzakdzaks.github_api.ui.user_detail

import androidx.lifecycle.ViewModel
import com.dzakdzaks.github_api.repository.UserRepository
import javax.inject.Inject

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Wednesday, 04 March 2020 at 10:59.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.ui.user_detail
 * ==================================//==================================
 * ==================================//==================================
 */

class UserDetailViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel()
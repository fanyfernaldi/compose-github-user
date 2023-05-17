package com.example.composegithubuser.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val login: String,
    val avatarUrl: String,
    val email: String,
    val followers: Int,
    val following: Int,
    val name: String,
    val isFavorite: Boolean,
) : Parcelable
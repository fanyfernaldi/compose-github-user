package com.example.composegithubuser.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "github_user")
data class GithubUserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "followers")
    var followers: Int,

    @ColumnInfo(name = "following")
    var following: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
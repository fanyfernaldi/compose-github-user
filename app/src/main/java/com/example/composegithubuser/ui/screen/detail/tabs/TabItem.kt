package com.example.composegithubuser.ui.screen.detail.tabs

import androidx.compose.runtime.Composable

data class TabItem(
    val title: String,
    val screen: @Composable () -> Unit
)
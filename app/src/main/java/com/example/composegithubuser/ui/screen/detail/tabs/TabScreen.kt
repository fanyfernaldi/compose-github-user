package com.example.composegithubuser.ui.screen.detail.tabs

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composegithubuser.core.data.Resource
import com.example.composegithubuser.core.domain.model.GithubUser
import com.example.composegithubuser.ui.components.UserItem
import com.example.composegithubuser.ui.screen.showLoadingScreen

@Composable
fun TabScreen(
    tabDataState: Resource<List<GithubUser>>?,
) {
    tabDataState?.let { tabData ->
        when (tabData) {
            is Resource.Loading -> {
                Log.w("LOGW_TEST", "Tab Screen: loading list following state")
                showLoadingScreen()
            }

            is Resource.Success -> {
                Log.w("LOGW_TEST", "Tab Screen: success list following state")
                showLoadingScreen(false)
                tabData.data?.let {
                    TabContent(it)
                }
            }

            is Resource.Error -> {
                Log.w("LOGW_TEST", "Tab Screen: error list following state")
                showLoadingScreen(false)
            }
        }
    }
}

@Composable
fun TabContent(
    listGithubUser: List<GithubUser>,
    modifier: Modifier = Modifier,
) {
    LazyColumn {
        items(listGithubUser) { githubUser ->
            UserItem(
                image = githubUser.avatarUrl,
                username = githubUser.login,
                modifier = modifier,
                onClick = {}
            )
        }
    }
}

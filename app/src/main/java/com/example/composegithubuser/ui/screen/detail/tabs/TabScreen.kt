package com.example.composegithubuser.ui.screen.detail.tabs

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composegithubuser.core.data.Resource
import com.example.composegithubuser.core.domain.model.GithubUser
import com.example.composegithubuser.ui.components.UserItem
import com.example.composegithubuser.ui.screen.detail.DetailViewModel
import com.example.composegithubuser.ui.screen.showLoadingScreen

@Composable
fun TabScreen(
    content: String,
    username: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    if (content == "Following") {

        LaunchedEffect(true) {
            viewModel.getFollowing(username)
        }

        viewModel.listFollowingState.collectAsState(initial = Resource.Loading()).value.let { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.w("LOGW_TEST", "Tab Screen: loading list following state")
                    showLoadingScreen()
                }

                is Resource.Success -> {
                    Log.w("LOGW_TEST", "Tab Screen: success list following state")
                    showLoadingScreen(false)
                    resource.data?.let {
                        TabContent(it)
                    }
                }

                is Resource.Error -> {
                    Log.w("LOGW_TEST", "Tab Screen: error list following state")
                    showLoadingScreen(false)
                }
            }
        }

    } else {

        LaunchedEffect(true) {
            viewModel.getFollowers(username)
        }

        viewModel.listFollowerState.collectAsState(initial = Resource.Loading()).value.let { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.w("LOGW_TEST", "Tab Screen: loading list follower state")
                    showLoadingScreen()
                }

                is Resource.Success -> {
                    Log.w("LOGW_TEST", "Tab Screen: success list follower state")
                    showLoadingScreen(false)
                    resource.data?.let {
                        TabContent(it)
                    }
                }

                is Resource.Error -> {
                    Log.w("LOGW_TEST", "Tab Screen: error list follower state")
                }
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

@Composable
@Preview
fun PreviewTabScreen() {
    TabScreen(content = "Tab Screen", username = "fanyfernaldi")
}
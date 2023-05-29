package com.example.composegithubuser.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composegithubuser.core.R
import com.example.composegithubuser.core.data.Resource
import com.example.composegithubuser.core.domain.model.GithubUser
import com.example.composegithubuser.ui.components.UserItem
import com.example.composegithubuser.ui.misc.toJsonApi
import com.example.composegithubuser.ui.screen.showLoadingScreen
import com.google.gson.Gson

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
    navigateToProfile: (String) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = Resource.Loading()).value.let { resource ->
        when (resource) {
            is Resource.Loading -> {
                Log.w("LOGW_TEST", "Home Screen: loading ui state")
                showLoadingScreen()
            }

            is Resource.Success -> {
                Log.w("LOGW_TEST", "Home Screen: success ui state")
                showLoadingScreen(false)
                if (resource.data?.isEmpty() == true) // showEmpty
                else resource.data?.let {
                    HomeContent(
                        listGithubUser = it,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                        navigateToProfile = navigateToProfile,
                    )
                }
            }

            is Resource.Error -> {
                Log.w("LOGW_TEST", "Home Screen: error ui state")
                showLoadingScreen(false)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    listGithubUser: List<GithubUser>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    navigateToProfile: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.menu_home),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            actions = {
                IconButton(
                    onClick = {

                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "favorite",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    onClick = {
                        val githubUser = GithubUser(
                            login = "test",
                            avatarUrl = "https://avatars.githubusercontent.com/u/36123691?v=4",
                            email = "test",
                            followers = 10,
                            following = 20,
                            name = "test",
                            isFavorite = false,
                        )
                        val githubUserJsonString = githubUser.toJsonApi()
                        if (githubUserJsonString != null) {
                            navigateToProfile(githubUserJsonString)
                        }
                    }) {

                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "profile",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        )
        LazyColumn {
            items(listGithubUser) { githubUser ->
                UserItem(
                    image = githubUser.avatarUrl,
                    username = githubUser.login,
                    modifier = modifier,
                    onClick = {
                        navigateToDetail(githubUser.login)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    HomeContent(
        listGithubUser = listOf(
            GithubUser(
                login = "test",
                avatarUrl = "https://avatars.githubusercontent.com/u/36123691?v=4",
                email = "test",
                followers = 10,
                following = 20,
                name = "test",
                isFavorite = false,
            )
        ),
        navigateToDetail = {},
        navigateToProfile = {},
    )
}
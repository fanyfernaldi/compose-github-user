package com.example.composegithubuser.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.composegithubuser.core.R
import com.example.composegithubuser.core.data.Resource
import com.example.composegithubuser.core.domain.model.GithubUser
import com.example.composegithubuser.ui.screen.showLoadingScreen

@Composable
fun DetailScreen(
    githubUsername: String,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,

) {

    LaunchedEffect(true) {
        viewModel.getDetailUser(githubUsername)
    }

    viewModel.githubUserState.collectAsState(initial = Resource.Loading()).value.let { resource ->
        when (resource) {
            is Resource.Loading -> {
                Log.w("LOGW_TEST", "Detail Screen: loading github user state")
                showLoadingScreen()
            }

            is Resource.Success -> {
                Log.w("LOGW_TEST", "Detail Screen: success github user state")
                showLoadingScreen(false)
                resource.data?.let {
                    DetailContent(githubUser = it, onBackClick = navigateBack)
                }
            }

            is Resource.Error -> {
                Log.w("LOGW_TEST", "Detail Screen: error github user state")
                showLoadingScreen(false)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    githubUser: GithubUser,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(id = R.string.menu_detail),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = modifier.fillMaxWidth()
        )
        AsyncImage(
            model = githubUser.avatarUrl,
            contentDescription = null,
            modifier = modifier
                .padding(top = 16.dp)
                .size(144.dp)
                .clip(
                    CircleShape
                )
        )
        Text(
            text = githubUser.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = githubUser.login,
            fontStyle = FontStyle.Italic,
            modifier = modifier.padding(top = 8.dp)
        )
        TabLayoutScreen(username = githubUser.login)
    }
}


package com.example.composegithubuser.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composegithubuser.core.R
import com.example.composegithubuser.core.domain.model.GithubUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    githubUser: GithubUser,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = navigateBack) {
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = githubUser.avatarUrl,
                contentDescription = null,
                modifier = modifier
                    .size(160.dp)
                    .clip(CircleShape)
            )
            Text(
                text = githubUser.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(top = 16.dp)
            )
            Text(text = githubUser.email, style = TextStyle(fontStyle = FontStyle.Italic))
        }
    }
}

package com.example.composegithubuser.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.TabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.composegithubuser.ui.screen.detail.tabs.TabItem
import com.example.composegithubuser.ui.screen.detail.tabs.TabScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayoutScreen(
    username: String
) {

    Log.w("LOGW_TEST", "IAM CALLEDX")

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val tabItems = listOf(
        TabItem(
            title = "Following",
            screen = { TabScreen(username = username, content = "Following") }
        ),
        TabItem(
            title = "Followers",
            screen = { TabScreen(username = username, content = "Followers") }
        )
    )

    Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == pagerState.currentPage,
                    enabled = true,
                    text = { Text(text = item.title) },
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } }
                )
            }
        }
        HorizontalPager(
            pageCount = tabItems.size,
            state = pagerState
        ) {
            tabItems[pagerState.currentPage].screen()
        }
    }


}
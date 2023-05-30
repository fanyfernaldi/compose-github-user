package com.example.composegithubuser.ui.screen.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.TabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composegithubuser.ui.screen.detail.tabs.TabScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayoutScreen(
    username: String,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val tabDataStates = viewModel.tabDataStates
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        viewModel.fetchDataForTabs(username)
    }

    Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabDataStates.keys.forEachIndexed { index, tabType ->
                Tab(
                    selected = index == pagerState.currentPage,
                    text = { Text(text = tabType.name) },
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            pageCount = tabDataStates.size
        ) { page ->
            val tabType = viewModel.getTabTypeForPage(page)
            val tabDataState = tabDataStates[tabType]?.collectAsState()?.value

            TabScreen(tabDataState = tabDataState)
        }
    }


}
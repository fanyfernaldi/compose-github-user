package com.example.composegithubuser.ui.screen.detail

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegithubuser.core.data.Resource
import com.example.composegithubuser.core.domain.model.GithubUser
import com.example.composegithubuser.core.domain.usecase.GithubUseCase
import com.example.composegithubuser.ui.screen.detail.tabs.TabType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val githubUseCase: GithubUseCase) : ViewModel() {

    private val _githubUserState: MutableStateFlow<Resource<GithubUser>> =
        MutableStateFlow(Resource.Loading())
    val githubUserState: StateFlow<Resource<GithubUser>> = _githubUserState

    private val _tabDataStates = mutableStateMapOf(
        TabType.FOLLOWING to MutableStateFlow<Resource<List<GithubUser>>>(Resource.Loading()),
        TabType.FOLLOWERS to MutableStateFlow<Resource<List<GithubUser>>>(Resource.Loading())
    )
    val tabDataStates: Map<TabType, StateFlow<Resource<List<GithubUser>>>> = _tabDataStates


    fun fetchDataForTabs(username: String) {
        val tabs = TabType.values()
        tabs.forEach { tabType ->
            viewModelScope.launch {
                if (tabType == TabType.FOLLOWING) {
                    githubUseCase.getFollowing(username).collect { resourceListGithubUSer ->
                        _tabDataStates[tabType]?.value = resourceListGithubUSer
                    }
                } else {
                    githubUseCase.getFollowers(username).collect { resourceListGithubUSer ->
                        _tabDataStates[tabType]?.value = resourceListGithubUSer
                    }
                }
            }
        }
    }

    fun getDetailUser(username: String) {
        viewModelScope.launch {
            githubUseCase.getDetailUser(username).collect { resourceGithubUser ->
                _githubUserState.value = resourceGithubUser
            }
        }
    }

    fun getTabTypeForPage(page: Int): TabType {
        return when (page) {
            0 -> TabType.FOLLOWING
            1 -> TabType.FOLLOWERS
            else -> throw IllegalArgumentException("Invalid page index")
        }
    }

}
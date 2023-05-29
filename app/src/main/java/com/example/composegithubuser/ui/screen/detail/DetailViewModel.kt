package com.example.composegithubuser.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegithubuser.core.data.Resource
import com.example.composegithubuser.core.domain.model.GithubUser
import com.example.composegithubuser.core.domain.usecase.GithubUseCase
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

    private val _listFollowingState: MutableStateFlow<Resource<List<GithubUser>>> =
        MutableStateFlow(Resource.Loading())
    val listFollowingState: StateFlow<Resource<List<GithubUser>>> = _listFollowingState

    private val _listFollowerState: MutableStateFlow<Resource<List<GithubUser>>> =
        MutableStateFlow(Resource.Loading())
    val listFollowerState: StateFlow<Resource<List<GithubUser>>> = _listFollowerState

    fun getDetailUser(username: String) {
        viewModelScope.launch {
            githubUseCase.getDetailUser(username).collect { resourceGithubUser ->
                _githubUserState.value = resourceGithubUser
            }
        }
    }

    fun getFollowing(username: String) {
        viewModelScope.launch {
            githubUseCase.getFollowing(username).collect { resourceListGithubUSer ->
                _listFollowingState.value = resourceListGithubUSer
            }
        }
    }

    fun getFollowers(username: String) {
        viewModelScope.launch {
            githubUseCase.getFollowers(username).collect { resourceListGithubUser ->
                _listFollowerState.value = resourceListGithubUser
            }
        }
    }

}
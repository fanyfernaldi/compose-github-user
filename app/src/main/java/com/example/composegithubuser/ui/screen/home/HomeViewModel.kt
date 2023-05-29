package com.example.composegithubuser.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegithubuser.core.data.Resource
import com.example.composegithubuser.core.domain.model.GithubUser
import com.example.composegithubuser.core.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

// since we need to tell how a ViewModel was created, you need to make a Constructor Injection on Each ViewModel
//
// Hilt is integrated with Jetpack, so you no longer need to create multi bindings using ViewModelFactory and ViewModelKey.
// You can replace it simply by adding the @HiltViewModel annotation to each ViewModel
@HiltViewModel
class HomeViewModel @Inject constructor(private val githubUseCase: GithubUseCase) : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _uiState: MutableStateFlow<Resource<List<GithubUser>>> = MutableStateFlow(Resource.Loading())
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<Resource<List<GithubUser>>> = _uiState

    init {
        getAllGithubUser()
    }

    fun getAllGithubUser(q: String = "Arif") {
        viewModelScope.launch {
            githubUseCase.getAllGithubUser(q).collect { resourceListGithubUser ->
                _uiState.value = resourceListGithubUser
            }
        }
    }

}
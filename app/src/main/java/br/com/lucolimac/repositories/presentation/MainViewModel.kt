package br.com.lucolimac.repositories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucolimac.repositories.data.model.Repository
import br.com.lucolimac.repositories.domain.ListUserRepositoriesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val userRepositoriesUseCase: ListUserRepositoriesUseCase) :
    ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
    fun getRepoList(user: String) {
        viewModelScope.launch {
            userRepositoriesUseCase(user)
                .onStart { _state.postValue(State.Loading) }
                .catch { _state.postValue(State.Failure(it)) }
                .collect {
                    _state.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val list: List<Repository>) : State()
        data class Failure(val error: Throwable) : State()
    }
}
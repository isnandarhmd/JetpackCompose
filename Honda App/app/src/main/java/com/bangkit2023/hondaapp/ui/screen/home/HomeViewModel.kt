package com.bangkit2023.hondaapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit2023.hondaapp.data.local.MotorcycleEntity
import com.bangkit2023.hondaapp.data.repository.Repository
import com.bangkit2023.hondaapp.utils.MotorcycleData
import com.bangkit2023.hondaapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _allmotorcycle = MutableStateFlow<UiState<List<MotorcycleEntity>>>(UiState.Loading)
    val allmotorcycle = _allmotorcycle.asStateFlow()

    private val _homeState = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllmotorcycle().collect { motorcycle ->
                when (motorcycle.isEmpty()) {
                    true -> repository.insertAllmotorcycle(MotorcycleData.dummy)
                    else -> _allmotorcycle.value = UiState.Success(motorcycle)
                }
            }
        }
    }

    private fun searchmotorcycle(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchmotorcycle(query)
                .catch { _allmotorcycle.value = UiState.Error(it.message.toString()) }
                .collect { _allmotorcycle.value = UiState.Success(it) }
        }
    }

    fun updateFavoritemotorcycle(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoritemotorcycle(id, isFavorite)
        }
    }

    fun onQueryChange(query: String) {
        _homeState.value = _homeState.value.copy(query = query)
        searchmotorcycle(query)
    }
}
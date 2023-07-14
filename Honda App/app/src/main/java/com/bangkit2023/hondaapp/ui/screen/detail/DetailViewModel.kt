package com.bangkit2023.hondaapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit2023.hondaapp.data.local.MotorcycleEntity
import com.bangkit2023.hondaapp.data.repository.Repository
import com.bangkit2023.hondaapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _motorcycle = MutableStateFlow<UiState<MotorcycleEntity>>(UiState.Loading)
    val motorcycle = _motorcycle.asStateFlow()

    fun getmotorcycle(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getmotorcycle(id)
                .catch { _motorcycle.value = UiState.Error(it.message.toString()) }
                .collect { _motorcycle.value = UiState.Success(it) }
        }
    }

    fun updateFavoritemotorcycle(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoritemotorcycle(id, isFavorite)
        }
    }
}
package com.bangkit2023.hondaapp.ui.screen.favorite

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bangkit2023.hondaapp.data.local.MotorcycleEntity
import com.bangkit2023.hondaapp.ui.components.*
import com.bangkit2023.hondaapp.utils.UiState

@Composable
fun FavoriteScreen(navController: NavController, scaffoldState: ScaffoldState) {
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

    favoriteViewModel.allFavoritemotorcycle.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Error -> ErrorContent()
            is UiState.Success -> {
                FavoriteContent(
                    listFavoritemotorcycle = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    onUpdateFavoritemotorcycle = favoriteViewModel::updateFavoritemotorcycle
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listFavoritemotorcycle: List<MotorcycleEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoritemotorcycle: (id: Int, isFavorite: Boolean) -> Unit
) {
    when (listFavoritemotorcycle.isEmpty()) {
        true -> EmptyContent()
        false -> AvailableContent(listFavoritemotorcycle, navController, scaffoldState, onUpdateFavoritemotorcycle)
    }
}
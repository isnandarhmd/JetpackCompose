package com.bangkit2023.hondaapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bangkit2023.hondaapp.data.local.MotorcycleEntity
import com.bangkit2023.hondaapp.ui.components.*
import com.bangkit2023.hondaapp.utils.UiState

@Composable
fun HomeScreen(navController: NavController, scaffoldState: ScaffoldState) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val homeState by homeViewModel.homeState

    homeViewModel.allmotorcycle.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Error -> ErrorContent()
            is UiState.Success -> {
                HomeContent(
                    listmotorcycle = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    query = homeState.query,
                    onQueryChange = homeViewModel::onQueryChange,
                    onUpdateFavoritemotorcycle = homeViewModel::updateFavoritemotorcycle
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    listmotorcycle: List<MotorcycleEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String,
    onQueryChange: (String) -> Unit,
    onUpdateFavoritemotorcycle: (id: Int, isFavorite: Boolean) -> Unit
) {
    Column {
        SearchBar(query = query, onQueryChange = onQueryChange)
        when (listmotorcycle.isEmpty()) {
            true -> EmptyContent()
            false -> AvailableContent(listmotorcycle, navController, scaffoldState, onUpdateFavoritemotorcycle)
        }
    }
}

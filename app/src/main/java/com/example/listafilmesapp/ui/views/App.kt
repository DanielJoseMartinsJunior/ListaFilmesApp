package com.example.listafilmesapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listafilmesapp.viewmodels.FilmeListVewModel

//Este arquivo define a estrutura principal do aplicativo e a navegação entre as diferentes telas.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    modifier: Modifier = Modifier
) {
    val viewModel: FilmeListVewModel = viewModel()

    // Cria um NavController para lidar com a navegação entre as telas
    val navController = rememberNavController()

    // Coleta o estado da UI do ViewModel
    val uiState by viewModel.appUIState.collectAsState()

    // Define a estrutura básica do aplicativo
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = uiState.title))
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.navigate(navController = navController)
            }) {
                Image(
                    painter = painterResource(id = uiState.fabIcon),
                    contentDescription = stringResource(
                        id = uiState.iconContentDescription)
                )
            }
        }
    ) {
        // Define o NavHost para controlar a navegação entre as telas
        NavHost(
            navController = navController,
            startDestination = AppScreens.FilmeList.name,
            modifier = modifier.padding(it)
        ) {
            composable(route = AppScreens.FilmeList.name) {
                FilmeList(
                    viewModel = viewModel, navController = navController,
                )
            }
            composable(route = AppScreens.InsertFilme.name) {
                InsertFilme(
                    viewModel = viewModel, navController = navController,
                )
            }
        }

    }
}

enum class AppScreens {
    FilmeList,
    InsertFilme,
}
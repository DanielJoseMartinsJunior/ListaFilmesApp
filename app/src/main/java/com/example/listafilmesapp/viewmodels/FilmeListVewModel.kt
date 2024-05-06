package com.example.listafilmesapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.listafilmesapp.R
import com.example.listafilmesapp.data.createFilmesList
import com.example.listafilmesapp.models.Filme
import com.example.listafilmesapp.ui.views.AppScreens
import com.example.listafilmesapp.ui.views.AppUIState
import com.example.listafilmesapp.ui.views.FilmeListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FilmeListVewModel : ViewModel(){

    private val _filmeListUIState: MutableStateFlow<FilmeListUIState> =
        MutableStateFlow(FilmeListUIState(createFilmesList()))

    val filmeListUIState: StateFlow<FilmeListUIState> =
        _filmeListUIState.asStateFlow()

    private val _appUIState: MutableStateFlow<AppUIState> =
        MutableStateFlow(AppUIState())

    val appUIState: StateFlow<AppUIState> =
        _appUIState.asStateFlow()

    fun navigate(navController: NavController) {
        if(_appUIState.value.title == R.string.lista_de_filmes) {

            _appUIState.update { currentState ->
                currentState.copy(
                    title = R.string.insira_um_novo_filme,
                    fabIcon = R.drawable.baseline_check_24,
                    iconContentDescription = R.string.confirm,
                )
            }

            navController.navigate(AppScreens.InsertFilme.name)

        } else{

            _appUIState.update {
                AppUIState()
            }

            navController.navigate(AppScreens.FilmeList.name) {
                popUpTo(AppScreens.FilmeList.name) {
                    inclusive = true
                }
            }

        }
    }

    fun navigateBack(navController: NavController) {
        _appUIState.update {
            AppUIState()
        }
        navController.popBackStack()
    }

    fun deleteFilme(filme: Filme){
        val filmes: MutableList<Filme> = _filmeListUIState.value.filmeList.toMutableList()
        filmes.remove(filme)
        _filmeListUIState.value = _filmeListUIState.value.copy(
            filmeList = filmes.toList())
    }
}
package com.example.listafilmesapp.viewmodels

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.listafilmesapp.R
import com.example.listafilmesapp.data.createFilmesList
import com.example.listafilmesapp.models.Filme
import com.example.listafilmesapp.ui.views.AppScreens
import com.example.listafilmesapp.ui.views.AppUIState
import com.example.listafilmesapp.ui.views.FilmeListUIState
import com.example.listafilmesapp.ui.views.InsertFormUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FilmeListVewModel : ViewModel(){

    private val _filmeListUIState: MutableStateFlow<FilmeListUIState> =
        MutableStateFlow(FilmeListUIState(createFilmesList()))

    val filmeListUIState: StateFlow<FilmeListUIState> =
        _filmeListUIState.asStateFlow()

    private val _insertFilmeUIState: MutableStateFlow<InsertFormUIState> =
        MutableStateFlow(InsertFormUIState())

    val insertFilmeUIState: StateFlow<InsertFormUIState> =
        _insertFilmeUIState.asStateFlow()

    private val _appUIState: MutableStateFlow<AppUIState> =
        MutableStateFlow(AppUIState())

    val appUIState: StateFlow<AppUIState> =
        _appUIState.asStateFlow()

    private var filme: Filme = Filme()
    private var editFilme: Boolean =  false

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

            if(editFilme) {

            } else {
                val filmes: MutableList<Filme> = _filmeListUIState.value.filmeList.toMutableList()
                filmes.add(
                    Filme(
                        foto = _insertFilmeUIState.value.foto,
                        nome = _insertFilmeUIState.value.nome,
                        descricao = _insertFilmeUIState.value.descricao,
                    )
                )
                _insertFilmeUIState.update {
                    InsertFormUIState()
                }
                _filmeListUIState.update { currentState ->
                    currentState.copy(
                        filmeList = filmes.toList()
                    )
                }

            }

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

    fun onFotoChange(@DrawableRes foto: Int) {
        _insertFilmeUIState.update { currentState ->
            currentState.copy(foto = foto)
        }
    }

    fun onNomeChange(nome: String) {
        _insertFilmeUIState.update { currentState ->
            currentState.copy(nome = nome)
        }
    }

    fun onDescricaoChange(descricao: String) {
        _insertFilmeUIState.update { currentState ->
            currentState.copy(descricao = descricao)
        }
    }

}
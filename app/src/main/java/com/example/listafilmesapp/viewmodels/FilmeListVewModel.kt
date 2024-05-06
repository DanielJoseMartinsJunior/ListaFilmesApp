package com.example.listafilmesapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.listafilmesapp.data.createFilmesList
import com.example.listafilmesapp.models.Filme
import com.example.listafilmesapp.ui.views.FilmeListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FilmeListVewModel : ViewModel(){

    private val _uiState: MutableStateFlow<FilmeListUIState> =
        MutableStateFlow(FilmeListUIState(createFilmesList()))

    val uiState: StateFlow<FilmeListUIState> =
        _uiState.asStateFlow()

    fun deleteFilme(filme: Filme){
        val filmes: MutableList<Filme> = _uiState.value.filmeList.toMutableList()
        filmes.remove(filme)
        _uiState.value = _uiState.value.copy(
            filmeList = filmes.toList())
    }
}
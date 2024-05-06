package com.example.listafilmesapp.ui.views

import com.example.listafilmesapp.models.Filme

// A classe FilmeListUIState representa o estado da interface do usuário da lista de filmes.
data class FilmeListUIState(
    val filmeList: List<Filme> = listOf()
)

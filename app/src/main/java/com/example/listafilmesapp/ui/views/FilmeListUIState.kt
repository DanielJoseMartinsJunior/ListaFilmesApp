package com.example.listafilmesapp.ui.views

import com.example.listafilmesapp.models.Filme

data class FilmeListUIState(
    val filmeList: List<Filme> = listOf()
)

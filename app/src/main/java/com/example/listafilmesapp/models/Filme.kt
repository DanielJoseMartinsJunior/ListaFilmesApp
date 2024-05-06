package com.example.listafilmesapp.models

import androidx.annotation.DrawableRes

data class Filme(
    @DrawableRes val foto: Int,
    val nome: String,
    val descricao: String,
)

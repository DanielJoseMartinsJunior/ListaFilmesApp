package com.example.listafilmesapp

import androidx.annotation.DrawableRes

data class Filme(
    @DrawableRes val foto: Int,
    val nome: String,
    val descricao: String,
)

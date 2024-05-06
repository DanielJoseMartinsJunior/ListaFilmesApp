package com.example.listafilmesapp.models

import androidx.annotation.DrawableRes
import com.example.listafilmesapp.R

data class Filme(
    @DrawableRes val foto: Int = R.drawable.acao,
    val nome: String = "",
    val descricao: String = "",
)

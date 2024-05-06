package com.example.listafilmesapp.models

import androidx.annotation.DrawableRes
import com.example.listafilmesapp.R

//Este arquivo é necessário porque define a estrutura de dados para um filme
data class Filme(
    @DrawableRes val foto: Int = R.drawable.acao,
    val nome: String = "",
    val descricao: String = "",
)

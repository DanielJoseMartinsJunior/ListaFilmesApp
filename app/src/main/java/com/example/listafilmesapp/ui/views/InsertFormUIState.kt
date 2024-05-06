package com.example.listafilmesapp.ui.views

import androidx.annotation.DrawableRes

data class InsertFormUIState(
    @DrawableRes val foto: Int,
    val nome: String,
    val descricao: String,

)

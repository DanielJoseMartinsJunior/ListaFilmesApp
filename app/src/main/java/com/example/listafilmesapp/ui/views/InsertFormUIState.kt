package com.example.listafilmesapp.ui.views

import androidx.annotation.DrawableRes
import com.example.listafilmesapp.R

data class InsertFormUIState(
    @DrawableRes val foto: Int = R.drawable.acao,
    val nome: String = "",
    val descricao: String = "",

    )

package com.example.listafilmesapp.ui.views

import androidx.annotation.DrawableRes
import com.example.listafilmesapp.R

// A classe InsertFormUIState representa o estado da interface do usuário do formulário de inserção de um novo filme.
data class InsertFormUIState(
    @DrawableRes val foto: Int = R.drawable.acao,
    val nome: String = "",
    val descricao: String = "",

    )

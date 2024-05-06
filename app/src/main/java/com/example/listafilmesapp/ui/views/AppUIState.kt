package com.example.listafilmesapp.ui.views

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.listafilmesapp.R

// A classe AppUIState representa o estado geral da interface do usuário do aplicativo.
data class AppUIState(
    @StringRes val title: Int = R.string.lista_de_filmes,
    @DrawableRes val fabIcon: Int = R.drawable.baseline_add_24,
    @StringRes val iconContentDescription: Int = R.string.insira_um_novo_filme,
)

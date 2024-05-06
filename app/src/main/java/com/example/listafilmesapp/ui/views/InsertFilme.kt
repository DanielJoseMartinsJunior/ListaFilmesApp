package com.example.listafilmesapp.ui.views

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.listafilmesapp.R
import com.example.listafilmesapp.viewmodels.FilmeListVewModel

// Este arquivo é responsável por definir a interface do usuário (UI) para a inserção de um novo filme

@Composable
fun InsertFilme(
    viewModel: FilmeListVewModel, //ViewModel que contém a lógica de negócios
    navController: NavController
) {
    // Manipula o evento de pressionar o botão de voltar
    BackHandler {
        viewModel.navigateBack(navController)
    }
    // Coleta o estado do formulário de inserção do ViewModel
    val uiState by viewModel.insertFilmeUIState.collectAsState()
    // Exibe o formulário de inserção
    InsertForm(
        foto = uiState.foto,
        nome = uiState.nome,
        descricao = uiState.descricao,
        onUpdateFoto = viewModel::onFotoChange,
        onUpdateNome = viewModel::onNomeChange,
        onUpdateDescricao = viewModel::onDescricaoChange,
    )
}

@Composable
fun InsertForm(
    @DrawableRes foto: Int,
    nome: String,
    descricao: String,
    onUpdateFoto: (Int) -> Unit,
    onUpdateNome: (String) -> Unit,
    onUpdateDescricao: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    val imageList = listOf(
        R.drawable.acao,
        R.drawable.ficcao_cientifica,
        R.drawable.romance,
        R.drawable.suspense
    )
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Exibe as imagens disponíveis em uma grade vertical preguiçosa
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxWidth()
        ) {
            // Para cada imagem na lista, exibe a imagem em uma caixa
            items(imageList) { image ->
                Box(
                    modifier = modifier
                        .size(150.dp)
                        .padding(8.dp)
                        .background(if (image == foto) Color.LightGray else Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = modifier
                            .size(150.dp)
                            .clickable {
                                onUpdateFoto(image)
                            }
                    )
                }
            }
        }

        Spacer(modifier = modifier.height(4.dp))

        TextField(
            value = nome,
            onValueChange = onUpdateNome,
            label = { Text(text = "Nome")},
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
        )

        Spacer(modifier = modifier.height(16.dp))

        TextField(
            value = descricao,
            onValueChange = onUpdateDescricao,
            label = { Text(text = "Descrição")},
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
        )

    }
}

@Preview(showBackground = true)
@Composable
fun InsertFormPreview() {
    InsertForm(
        R.drawable.acao,
        "Velozes e Furiosos",
        "Corridas ilegais de carros.",
        {},
        {},
        {},
    )
}
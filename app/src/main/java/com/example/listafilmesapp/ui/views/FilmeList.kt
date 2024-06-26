package com.example.listafilmesapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.listafilmesapp.R
import com.example.listafilmesapp.models.Filme
import com.example.listafilmesapp.ui.theme.ListaFilmesAppTheme
import com.example.listafilmesapp.viewmodels.FilmeListVewModel

// Este arquivo é responsável por definir a interface do usuário (UI) para a lista de filmes e o card de filme

// Componente que exibe a lista de filmes
@Composable
fun FilmeList(
    viewModel: FilmeListVewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    // Coleta o estado da lista de filmes do ViewModel
    val uiState by viewModel.filmeListUIState.collectAsState()

    // Exibe a lista de filmes em uma coluna preguiçosa
    LazyColumn{
        items(uiState.filmeList){ filme ->
            FilmeCard(
                filme = filme,
                onDelete = viewModel::deleteFilme, // Função para deletar o filme
                // Função para editar o filme
                onEditFilme = {
                    viewModel.onEditFilme(
                        filme = filme,
                        navController = navController,
                    )
                },
                modifier = modifier
            )
        }
    }
}

// Componente que exibe um único filme em um cartão
@Composable
fun FilmeCard(
    modifier: Modifier = Modifier,
    filme: Filme,
    onDelete: (Filme) -> Unit = {},
    onEditFilme: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable {
                // Quando o cartão é clicado, a função de edição do filme é chamada
                onEditFilme()
            },
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Image(
                modifier = modifier
                    .size(50.dp)
                    .padding(start = 4.dp, end = 4.dp),
                painter = painterResource(id = filme.foto),
                contentDescription = null,
            )
            Column {
                Text(
                    text = filme.nome,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Text(
                    text = filme.descricao,
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp,
                    color = Color.Black,
                    maxLines = 2,
                    modifier = modifier.width(260.dp)
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                contentDescription = "delete",
                modifier
                    .padding(end = 4.dp)
                    .clickable { onDelete(filme) }
            )
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun ListPreview() {
    ListaFilmesAppTheme {
        FilmeList(viewModel())
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    ListaFilmesAppTheme {
        FilmeCard(
            filme = Filme(R.drawable.acao,
                "Velozes e Furiosos",
                "Corridas ilegais de carros."
            )
        )
    }
}
*/

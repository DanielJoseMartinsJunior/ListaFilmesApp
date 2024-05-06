package com.example.listafilmesapp.viewmodels

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.listafilmesapp.R
import com.example.listafilmesapp.data.createFilmesList
import com.example.listafilmesapp.models.Filme
import com.example.listafilmesapp.ui.views.AppScreens
import com.example.listafilmesapp.ui.views.AppUIState
import com.example.listafilmesapp.ui.views.FilmeListUIState
import com.example.listafilmesapp.ui.views.InsertFormUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// A classe FilmeListVewModel é o ViewModel do aplicativo. Ela contém a lógica de negócios e gerencia o estado da UI.
class FilmeListVewModel : ViewModel(){

    // Estado da lista de filmes
    private val _filmeListUIState: MutableStateFlow<FilmeListUIState> =
        MutableStateFlow(FilmeListUIState(createFilmesList()))

    // Exposição do estado da lista de filmes como um StateFlow imutável
    val filmeListUIState: StateFlow<FilmeListUIState> =
        _filmeListUIState.asStateFlow()

    // Estado do formulário de inserção de filme
    private val _insertFilmeUIState: MutableStateFlow<InsertFormUIState> =
        MutableStateFlow(InsertFormUIState())

    // Exposição do estado do formulário de inserção de filme como um StateFlow imutável
    val insertFilmeUIState: StateFlow<InsertFormUIState> =
        _insertFilmeUIState.asStateFlow()

    // Estado geral da UI do aplicativo
    private val _appUIState: MutableStateFlow<AppUIState> =
        MutableStateFlow(AppUIState())

    // Exposição do estado geral da UI do aplicativo como um StateFlow imutável
    val appUIState: StateFlow<AppUIState> =
        _appUIState.asStateFlow()

    // Filme que está sendo editado
    private var filmeToEdit: Filme = Filme()
    // Flag para indicar se um filme está sendo editado
    private var editFilme: Boolean =  false

    // Função para navegar entre as telas
    fun navigate(navController: NavController) {
        // Se a tela atual é a lista de filmes
        if(_appUIState.value.title == R.string.lista_de_filmes) {
            // Atualiza o estado da UI para a tela de inserção de filme
            _appUIState.update { currentState ->
                currentState.copy(
                    title = R.string.insira_um_novo_filme,
                    fabIcon = R.drawable.baseline_check_24,
                    iconContentDescription = R.string.confirm,
                )
            }
            // Navega para a tela de inserção de filme
            navController.navigate(AppScreens.InsertFilme.name)
        } else{
            // Se a tela atual é a tela de inserção de filme
            val filmes: MutableList<Filme> =
                _filmeListUIState.value.filmeList.toMutableList()
            if(editFilme) {
                // Se um filme está sendo editado
                val pos = filmes.indexOf(filmeToEdit)
                filmes.removeAt(pos)
                filmes.add(pos, Filme(
                    _insertFilmeUIState.value.foto,
                    _insertFilmeUIState.value.nome,
                    _insertFilmeUIState.value.descricao,
                ))

                filmeToEdit = Filme()
                editFilme = false

            } else {
                // Se um novo filme está sendo inserido
                filmes.add(
                    Filme(
                        foto = _insertFilmeUIState.value.foto,
                        nome = _insertFilmeUIState.value.nome,
                        descricao = _insertFilmeUIState.value.descricao,
                    )
                )
            }

            // Atualiza a lista de filmes
            _filmeListUIState.update { currentState ->
                currentState.copy(
                    filmeList = filmes.toList()
                )
            }

            // Limpa o estado do formulário de inserção de filme
            _insertFilmeUIState.update {
                InsertFormUIState()
            }

            // Restaura o estado da UI para a lista de filmes
            _appUIState.update {
                AppUIState()
            }

            // Navega de volta para a lista de filmes
            navController.navigate(AppScreens.FilmeList.name) {
                popUpTo(AppScreens.FilmeList.name) {
                    inclusive = true
                }
            }

        }
    }

    // Função para navegar de volta para a tela anterior
    fun navigateBack(navController: NavController) {
        // Restaura o estado da UI para a lista de filmes
        _appUIState.update {
            AppUIState()
        }
        // Navega de volta para a tela anterior
        navController.popBackStack()
    }

    // Função para deletar um filme
    fun deleteFilme(filme: Filme){
        val filmes: MutableList<Filme> = _filmeListUIState.value.filmeList.toMutableList()
        filmes.remove(filme)
        _filmeListUIState.value = _filmeListUIState.value.copy(
            filmeList = filmes.toList())
    }

    // Função para editar um filme
    fun onEditFilme(filme: Filme, navController: NavController) {
        editFilme = true
        filmeToEdit =  filme

        // Atualiza o estado do formulário de inserção de filme com os dados do filme a ser editado
        _insertFilmeUIState.update { currentState ->
            currentState.copy(
                foto = filmeToEdit.foto,
                nome = filmeToEdit.nome,
                descricao = filmeToEdit.descricao,
            )
        }

        // Atualiza o estado da UI para a tela de edição de filme
        _appUIState.update { currentState ->
            currentState.copy(
                title = R.string.editar_filme,
                fabIcon = R.drawable.baseline_check_24,
                iconContentDescription = R.string.confirm,
            )
        }

        // Navega para a tela de edição de filme
        navController.navigate(route = AppScreens.InsertFilme.name)

    }

    // Função para atualizar a foto do filme no formulário de inserção de filme
    fun onFotoChange(@DrawableRes foto: Int) {
        _insertFilmeUIState.update { currentState ->
            currentState.copy(foto = foto)
        }
    }

    // Função para atualizar o nome do filme no formulário de inserção de filme
    fun onNomeChange(nome: String) {
        _insertFilmeUIState.update { currentState ->
            currentState.copy(nome = nome)
        }
    }

    // Função para atualizar a descrição do filme no formulário de inserção de filme
    fun onDescricaoChange(descricao: String) {
        _insertFilmeUIState.update { currentState ->
            currentState.copy(descricao = descricao)
        }
    }

}
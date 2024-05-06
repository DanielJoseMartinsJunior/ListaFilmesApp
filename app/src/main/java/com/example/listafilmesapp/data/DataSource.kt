package com.example.listafilmesapp.data

import com.example.listafilmesapp.models.Filme
import com.example.listafilmesapp.R

fun createFilmesList(): List<Filme> {
    val fotos = listOf(
        R.drawable.acao,
        R.drawable.ficcao_cientifica,
        R.drawable.romance,
        R.drawable.suspense
    )

    val caracteres = listOf(
        "Velozes e Furiosos" to "Corridas ilegais de carros.",
        "Interstellar" to "Viagem interdimensional.",
        "Titanic" to "Amor em meio a tragédia.",
        "Seven" to "Detetive caça serial killer.",
        "John Wick" to "Ex-assassino busca vingança.",
        "Matrix" to "Realidade é uma simulação.",
        "O Diário de uma Paixão" to "Amor desafiando o tempo.",
        "O Silêncio dos Inocentes" to "Perfiladora caça canibal.",
        "Missão Impossível" to "Agente secreto em missão.",
        "Blade Runner" to "Caçador de androides.",
        "Orgulho e Preconceito" to "Amor na Inglaterra vitoriana.",
        "O Iluminado" to "Terror em hotel isolado.",
        "Mad Max" to "Sobrevivência em mundo pós-apocalíptico.",
        "Star Wars" to "Batalha intergaláctica.",
        "Romeu e Julieta" to "Amor trágico entre famílias rivais.",
        "Psicose" to "Mistério em motel deserto.",
        "Duro de Matar" to "Policial enfrenta terroristas.",
        "Uma Odisseia no Espaço" to "Viagem espacial misteriosa.",
        "A Culpa é das Estrelas" to "Amor entre jovens com câncer.",
        "O Sexto Sentido" to "Garoto que vê pessoas mortas.",
        "007: Cassino Royale" to "Espião em missão de jogo.",
        "Avatar" to "Conflito em planeta alienígena.",
        "Antes do Amanhecer" to "Romance em uma noite em Viena.",
        "Zodíaco" to "Busca por serial killer.",
        "Os Vingadores" to "Super-heróis salvam o mundo.",
        "Ex Machina" to "IA passa no teste de Turing.",
        "Casablanca" to "Amor e sacrifício durante a guerra.",
        "O Código Da Vinci" to "Mistério envolvendo o Santo Graal.",
        "Homem de Ferro" to "Bilionário se torna super-herói.",
        "Star Trek" to "Exploração espacial no futuro.",
        "E o Vento Levou" to "Amor e perda na Guerra Civil.",
        "O Bebê de Rosemary" to "Gravidez resulta em terror.",
        "Capitão América" to "Soldado se torna super-herói.",
        "E.T. - O Extraterrestre" to "Amizade entre menino e alienígena.",
        "Romeu + Julieta" to "Amor trágico em cenário moderno.",
        "O Iluminado" to "Família isolada em hotel assombrado.",
        "Thor" to "Deus do trovão é exilado.",
        "Contato" to "Cientista recebe mensagem alienígena.",
        "Moulin Rouge" to "Amor e tragédia no cabaré.",
        "Se7en" to "Caça do assassino dos sete pecados.",
        "Guardiões da Galáxia" to "Aventura espacial com equipe improvável.",
        "A Origem" to "Espionagem em sonhos compartilhados.",
        "Um Amor para Recordar" to "Amor entre jovem rebelde e garota religiosa.",
        "O Silêncio dos Inocentes" to "Perfiladora do FBI caça serial killer.",
        "Gladiador" to "Gladiador busca vingança contra imperador.",
        "De Volta para o Futuro" to "Viagem no tempo para consertar o passado.",
        "Crepúsculo" to "Amor entre humana e vampiro.",
        "O Código Da Vinci" to "Mistério envolvendo o Santo Graal.",
        "300" to "Espartanos enfrentam exército persa.",
        "A Ameaça Fantasma" to "Início da saga de Anakin Skywalker.",
        "O Diário de uma Paixão" to "Amor desafiando as diferenças sociais.",
        "O Sexto Sentido" to "Psicólogo ajuda garoto que vê mortos.",
        "Vingadores: Guerra Infinita" to "Super-heróis contra Thanos.",
        "Matrix" to "Realidade é uma simulação.",
        "Romeu + Julieta" to "Amor trágico em cenário moderno.",
    )


    val filmes = caracteres.mapIndexed { index, (nome, descricao) ->
        Filme(
            foto = fotos[index % fotos.size],
            nome = nome,
            descricao = descricao
        )
    }
    return filmes
}
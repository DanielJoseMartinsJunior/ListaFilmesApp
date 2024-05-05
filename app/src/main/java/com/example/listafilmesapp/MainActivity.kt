package com.example.listafilmesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listafilmesapp.ui.theme.ListaFilmesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaFilmesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FilmesApp()
                }
            }
        }
    }
}

fun createFilmesList(): List<Filme> {
    val fotos = listOf(
        R.drawable.acao,
        R.drawable.ficcao_cientifica,
        R.drawable.romance,
        R.drawable.suspense
    )

    val caracteres = listOf(
        "Mad Max: Estrada da Fúria" to "Um épico pós-apocalíptico onde um guerreiro solitário luta para sobreviver em um mundo desolado e caótico.",
        "Interestelar" to "Uma equipe de astronautas viaja através de um buraco de minhoca em busca de um novo lar para a humanidade, enfrentando paradoxos temporais e fenômenos cósmicos.",
        "Orgulho e Preconceito" to "Um romance intemporal sobre o conflito entre Elizabeth Bennet e o arrogante Sr. Darcy, em meio à sociedade britânica do século XIX.",
        "Garota Exemplar" to "Quando a esposa de um escritor desaparece, as evidências começam a sugerir que ele pode estar envolvido em seu sumiço, desencadeando uma investigação cheia de reviravoltas.",
        "John Wick" to "Um ex-assassino busca vingança após o brutal assassinato de seu cachorro, mergulhando em um submundo de crime violento.",
        "Blade Runner 2049" to "Em um futuro distópico, um caçador de replicantes descobre um segredo que pode abalar a sociedade, desencadeando uma jornada existencial.",
        "La La Land: Cantando Estações" to "Um pianista e uma atriz se apaixonam em Los Angeles, perseguindo seus sonhos enquanto enfrentam os desafios do sucesso e do relacionamento.",
        "Prisioneiros" to "Quando duas meninas desaparecem, um pai desesperado e um detetive obsessivo mergulham em uma busca angustiante por respostas, revelando segredos sombrios.",
        "Duro de Matar" to "Um policial destemido luta contra terroristas em um arranha-céu, tornando-se um ícone do cinema de ação dos anos 80.",
        "Matrix" to "Um hacker descobre que a realidade que ele conhece é uma simulação controlada por máquinas, desencadeando uma guerra entre humanos e inteligências artificiais.",
        "Diário de uma Paixão" to "Um homem idoso conta a história de seu romance proibido com uma jovem de família rica durante o verão de 1940.",
        "Seven: Os Sete Crimes Capitais" to "Dois detetives perseguem um assassino em série que comete crimes baseados nos sete pecados mortais, levando-os a um confronto aterrorizante.",
        "Operação Invasão" to "Em uma operação policial em um prédio controlado por gangues, um esquadrão de elite enfrenta uma luta desesperada pela sobrevivência.",
        "O Exterminador do Futuro 2: O Julgamento Final" to "Um ciborgue é enviado do futuro para proteger um jovem líder da resistência contra um novo e mortal exterminador.",
        "Simplesmente Acontece" to "Dois amigos de infância percebem que estão destinados a ficar juntos, apesar dos obstáculos e das reviravoltas da vida.",
        "Clube da Luta" to "Um homem desiludido e um carismático vendedor formam um clube secreto onde homens lutam como forma de catarse, mas as coisas saem do controle.",
        "A Chegada" to "Uma linguista é recrutada para se comunicar com extraterrestres que chegaram à Terra, desvendando a complexidade da linguagem e do tempo.",
        "Titanic" to "Um romance épico entre um jovem casal a bordo do RMS Titanic, que enfrenta um destino trágico quando o navio colide com um iceberg.",
        "O Silêncio dos Inocentes" to "Uma agente do FBI busca a ajuda de um brilhante, porém instável, psiquiatra para capturar um assassino em série conhecido como 'Buffalo Bill'.",
        "Kill Bill: Volume 1" to "Uma noiva ex-assassina busca vingança contra seus antigos colegas de gangue, que tentaram matá-la no dia do seu casamento.",
        "Interestelar" to "Uma equipe de astronautas viaja através de um buraco de minhoca em busca de um novo lar para a humanidade, enfrentando paradoxos temporais e fenômenos cósmicos.",
        "Crepúsculo" to "Um romance proibido floresce entre uma humana e um vampiro, desafiando convenções e colocando suas vidas em perigo.",
        "O Sexto Sentido" to "Um menino que vê e fala com os mortos é ajudado por um psicólogo infantil atormentado por seus próprios demônios.",
        "Pulp Fiction: Tempo de Violência" to "Uma narrativa fragmentada segue um grupo de personagens interligados, explorando temas de violência, redenção e moralidade.",
        "Operação Fronteira" to "Um grupo de ex-soldados se reúne para roubar um cartel de drogas na tríplice fronteira entre Brasil, Paraguai e Argentina.",
        "O Quinto Elemento" to "Em um futuro distante, um taxista se envolve em uma missão para salvar a Terra de uma força do mal com a ajuda de um ser supremo.",
        "La La Land: Cantando Estações" to "Um pianista e uma atriz se apaixonam em Los Angeles, perseguindo seus sonhos enquanto enfrentam os desafios do sucesso e do relacionamento.",
        "Amor e Outras Drogas" to "Um representante farmacêutico mulherengo se apaixona por uma mulher com mal de Parkinson, desafiando suas visões sobre relacionamentos e compromisso.",
        "O Código Da Vinci" to "Um simbologista e uma criptógrafa seguem pistas ocultas em obras de arte para desvendar um segredo que pode abalar as bases do cristianismo.",
        "O Iluminado" to "Um escritor aceita um emprego como zelador em um hotel isolado durante o inverno, mas sua sanidade começa a se deteriorar quando forças sobrenaturais o cercam.",
        "Esquadrão Suicida" to "Um grupo de supervilões é recrutado pelo governo para realizar missões perigosas em troca de redução de suas penas.",
        "Guerra Mundial Z" to "Um ex-investigador da ONU é enviado ao redor do mundo para investigar e deter uma pandemia de zumbis que ameaça a civilização.",
        "Um Amor para Recordar" to "Dois jovens se apaixonam durante o verão, mas seus destinos são desafiados quando segredos do passado são revelados.",
        "O Cisne Negro" to "Uma bailarina ambiciosa é consumida pela obsessão pela perfeição ao interpretar o papel principal em 'O Lago dos Cisnes'.",
        "A Origem" to "Um ladrão especializado em roubar segredos corporativos através do subconsciente é contratado para implantar uma ideia na mente de um CEO.",
        "Antes do Amanhecer" to "Um americano e uma francesa se conhecem em um trem e passam uma noite em Viena, explorando a cidade e se apaixonando.",
        "O Chamado" to "Uma jornalista investiga uma fita de vídeo amaldiçoada que mata quem a assiste sete dias depois.",
        "Gladiador" to "Um general romano é traído e forçado a se tornar um gladiador, buscando vingança contra o imperador que matou sua família.",
        "Planeta dos Macacos: A Origem" to "Um cientista luta pela sobrevivência em um mundo onde macacos inteligentes dominam sobre os humanos.",
        "Uma Linda Mulher" to "Um magnata dos negócios contrata uma prostituta para ser sua acompanhante por uma semana, mas o que começa como um acordo de negócios se transforma em algo mais.",
        "Cisne Negro" to "Uma bailarina ambiciosa é consumida pela obsessão pela perfeição ao interpretar o papel principal em 'O Lago dos Cisnes'.",
        "Fragmentado" to "Um homem com múltiplas personalidades sequestra três adolescentes, levando-as a uma luta desesperada pela sobrevivência.",
        "A Culpa é das Estrelas" to "Dois adolescentes com câncer se apaixonam enquanto enfrentam suas próprias mortes iminentes."
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

@Composable
fun FilmesApp() {
    var filmes by remember {
        mutableStateOf(
            createFilmesList()
        )
    }
    FilmeList(filmes = filmes)
}


@Composable
fun FilmeList(
    filmes: List<Filme>,
    modifier: Modifier = Modifier,
) {
    LazyColumn{
        items(filmes){ filme ->
            FilmeCard(filme = filme)
        }
    }
}

@Composable
fun FilmeCard(
    filme: Filme,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp)
        ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
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
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    ListaFilmesAppTheme {
        FilmeCard(
            filme = Filme(
                R.drawable.acao,
                "Velozes e Furiosos",
                "Teste da descrição do filme velozes e furiosos"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    ListaFilmesAppTheme {
        FilmeList(filmes = listOf(
            Filme(
                R.drawable.acao,
                "Velozes e Furiosos",
                "Teste da descrição do filme velozes e furiosos"
            ),
            Filme(
                R.drawable.acao,
                "Velozes e Furiosos",
                "Teste da descrição do filme velozes e furiosos"
            ),
            Filme(
                R.drawable.acao,
                "Velozes e Furiosos",
                "Teste da descrição do filme velozes e furiosos"
            ),
            Filme(
                R.drawable.acao,
                "Velozes e Furiosos",
                "Teste da descrição do filme velozes e furiosos"
            )


        ))
    }
}
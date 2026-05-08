package carvalho.zanini.ponderada1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

import androidx.compose.ui.tooling.preview.Preview
import carvalho.zanini.ponderada1.ui.theme.Ponderada1Theme

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LancadorDeDadosApp()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LancadorDeDadosApp() {
    var dadoSelecionado by remember { mutableStateOf("D6") }
    var resultado by remember { mutableStateOf("Clique no botão para lançar o dado") }
    var imagemDado by remember { mutableStateOf(R.drawable.dice_six_faces_one) }

    val dados = listOf("D6", "D10", "D20", "D100")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Lançador de Dados",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Escolha o tipo de dado:")

        dados.forEach { dado ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = dadoSelecionado == dado,
                    onClick = {
                        dadoSelecionado = dado

                        resultado = "Clique no botão para lançar o dado"

                        imagemDado = R.drawable.dice_six_faces_one
                    }
                )
                Text(text = dado)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val valorSorteado = when (dadoSelecionado) {

                    "D6" -> {
                        val numero = Random.nextInt(1, 7)

                        imagemDado = when (numero) {
                            1 -> R.drawable.dice_six_faces_one
                            2 -> R.drawable.dice_six_faces_two
                            3 -> R.drawable.dice_six_faces_three
                            4 -> R.drawable.dice_six_faces_four
                            5 -> R.drawable.dice_six_faces_five
                            else -> R.drawable.dice_six_faces_six
                        }

                        "Resultado visual do D6"
                    }

                    "D10" -> Random.nextInt(1, 11).toString()

                    "D20" -> Random.nextInt(1, 21).toString()

                    "D100" -> Random.nextInt(1, 101).toString()

                    else -> "0"
                }

                resultado = "Resultado do $dadoSelecionado: $valorSorteado"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Lançar dado")
        }

        Spacer(modifier = Modifier.height(24.dp))


        if (dadoSelecionado == "D6") {

            Image(
                painter = painterResource(id = imagemDado),
                contentDescription = "Face do dado",
                modifier = Modifier.size(200.dp)
            )

        } else {

            Text(
                text = resultado,
                fontSize = 20.sp
            )
        }
    }
}
package medication.smartpatient.tictacjetpackcompse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import medication.smartpatient.tictacjetpackcompse.ui.theme.TicTacJetpackCompseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacJetpackCompseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    tttScreen()
                }
            }
        }
    }
}

@Composable
fun tttScreen() {
    // true means player turn false means ai tern
    var playerTurn = remember {
        mutableStateOf(true)
    }
    var moves = remember {
        mutableStateListOf<Boolean?>(true, false, null, false, true, null, null, null)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Tic Tac Tok")
        Head(playerTurn.value)
        Board(moves)
    }


}

@Composable
fun Head(playerTurn: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        var playerBoxColor = if (playerTurn) Color.Blue else Color.Red
        var aiBoxColor = if (playerTurn) Color.Gray else Color.Green

        Box(
            modifier = Modifier
                .width(95.dp)
                .background(playerBoxColor)
        ) {
            Text(
                text = "Player",
                Modifier
                    .padding(9.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(44.dp))

        Box(
            modifier = Modifier
                .width(95.dp)
                .background(aiBoxColor)
        ) {
            Text(
                text = "Ai",
                Modifier
                    .padding(9.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun Board(moves: List<Boolean?>) {

    Row(
        modifier = Modifier
            .aspectRatio(1f)
            .background(Color.LightGray)
            .padding(32.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize(1f)
        ) {
            Row(modifier = Modifier
                .height(2.dp)
                .fillMaxSize(1f)
                .background(Color.Black)) {

            }
            Row(modifier = Modifier
                .height(2.dp)
                .fillMaxSize(1f)
                .background(Color.Black)) {

            }
        }

    }
}


package medication.smartpatient.tictacjetpackcompse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import medication.smartpatient.tictacjetpackcompse.ui.theme.TicTacJetpackCompseTheme
import kotlin.random.Random

enum class Win {
    Player, AI,
    Draw
}

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
        mutableStateListOf<Boolean?>(
            true, null, false, null, true, false, null, null, null
        )
    }

    val win = remember {
        mutableStateOf<Win?>(null)
    }
    val onTap: (Offset) -> Unit = {
        if (playerTurn.value) {
            val x = (it.x / 333).toInt()
            val y = (it.y / 333).toInt()
            val posInMoves = y * 3 + x;
            if (moves[posInMoves] == null) {
                moves[posInMoves] = true
                playerTurn.value = false
            }
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Tic Tac Tok")
        Head(playerTurn.value)
        Board(moves, onTap)

        if (!playerTurn.value) {
            CircularProgressIndicator(color = Color.Red, modifier = Modifier.padding(13.dp))

            val coroutineScope = rememberCoroutineScope()

            LaunchedEffect(key1 = Unit)
            {
                coroutineScope {
                    delay(1500L)
                    while (true) {
                        val i = Random.nextInt(9)
                        if (moves[i] == null) {
                            moves[i] = false
                            playerTurn.value = true
                            win.value = checkEnd(moves)
                            break
                        }
                    }
                }
            }

        }
        if (win.value != null) {
            when (win.value) {
                Win.Draw -> {
                    Text(text = "DRAW")

                }
                Win.Player -> {
                    Text(text = "Player has won")
                }
                Win.AI -> {
                    Text(text = "AI has won")

                }
                else -> {}
            }
        }
    }

}


fun checkEnd(m: List<Boolean?>): Win? {
    var win: Win? = null
    if ((m[0] == true && m[1] == true && m[2] == true) ||
        (m[3] == true && m[4] == true && m[5] == true) ||
        (m[6] == true && m[7] == true && m[8] == true) ||
        (m[0] == true && m[3] == true && m[6] == true) ||
        (m[1] == true && m[4] == true && m[7] == true) ||
        (m[2] == true && m[5] == true && m[8] == true) ||
        (m[0] == true && m[4] == true && m[8] == true) ||
        (m[2] == true && m[4] == true && m[6] == true)

    )
        win = Win.Player
    if ((m[0] == false && m[1] == false && m[2] == false) ||
        (m[3] == false && m[4] == false && m[5] == false) ||
        (m[6] == false && m[7] == false && m[8] == false) ||
        (m[0] == false && m[3] == false && m[6] == false) ||
        (m[1] == false && m[4] == false && m[7] == false) ||
        (m[2] == false && m[5] == false && m[8] == false) ||
        (m[0] == false && m[4] == false && m[8] == false) ||
        (m[2] == false && m[4] == false && m[6] == false)

    )
        win = Win.AI

    if (win == null) {
        var available = false
        for (i in 0..8) {
            if (m[i] == null) {
                available = true
            }
            if (!available) {
                win = Win.Draw
            }
        }
    }
    return win
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
fun Board(moves: List<Boolean?>, onTap: (Offset) -> Unit) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(32.dp)
            .background(Color.LightGray)
            .pointerInput(Unit) {
                detectTapGestures(onTap = onTap)
            }

    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize(1f)
        ) {
            Row(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxSize(1f)
                    .background(Color.Black)
            ) {

            }
            Row(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxSize(1f)
                    .background(Color.Black)
            ) {

            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize(1f)
        ) {
            Column(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxSize(1f)
                    .background(Color.Black)
            ) {
            }
            Column(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxSize(1f)
                    .background(Color.Black)
            ) {

            }

        }

        Column(modifier = Modifier.fillMaxSize(1f)) {
            for (i in 0..2) {
                Row(modifier = Modifier.weight(1f)) {
                    for (j in 0..2) {
                        Column(modifier = Modifier.weight(1f)) {
                            getComposableFromMove(moves = moves[i * 2 + j])
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun getComposableFromMove(moves: Boolean?) {
    when (moves) {
        true -> Image(
            painter = painterResource(id = R.drawable.x),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(1f),
            colorFilter = ColorFilter.tint(Color.Blue)
        )
        false -> Image(
            painter = painterResource(id = R.drawable.o),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(1f),
            colorFilter = ColorFilter.tint(Color.Red)
        )
        null -> Image(
            painter = painterResource(id = R.drawable.nu),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(1f),
            colorFilter = ColorFilter.tint(Color.Red)
        )
    }

}


package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.inc

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                App()
            }
        }
    }
}

@Preview
@Composable
fun App(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                modifier = Modifier
                    .background(Color(0xFFFFEB3B))
                    .fillMaxWidth()
                    .padding(top = 36.dp, bottom = 20.dp),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.app_name),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.SemiBold
            )
        }
        var state by remember { mutableStateOf(1) }
        var image = when (state) {
            1 -> painterResource(R.drawable.lemon_tree)
            2 -> painterResource(R.drawable.lemon_squeeze)
            3 -> painterResource(R.drawable.lemon_drink)
            else -> painterResource(R.drawable.lemon_restart)
        }
        var imageDesc = when (state) {
            1 -> "Lemon tree"
            2 -> "Lemon"
            3 -> "Glass of lemonade"
            else -> "Empty glass"
        }
        var desc = when (state) {
            1 -> stringResource(R.string.lemon_tree_desc)
            2 -> stringResource(R.string.lemon_desc)
            3 -> stringResource(R.string.glass_of_lemonade_desc)
            else -> stringResource(R.string.empty_glass_of_lemonade)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LemonadeButton(image, imageDesc) { state = if (state in 1..3) ++state else 1 }
            Spacer(Modifier.height(16.dp))
            Text(
                text = desc,
                color = MaterialTheme.colorScheme.inverseSurface,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }
    }
}

@Composable
fun LemonadeButton(
    image: Painter,
    imageDescription: String,
    action: () -> Unit
) {
    Button(
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC1D8C3)),
        onClick = action,
    ) {
        Image(
            painter = image,
            contentDescription = imageDescription,
            alignment = Alignment.Center,
        )
    }
}

package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

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

enum class Lemonade { TREE, LEMON, JUICE, REPEAT; }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name), fontWeight = FontWeight.Bold
                    )
                }, colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Yellow
                )
            )
        }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            var state by remember { mutableStateOf(Lemonade.TREE) }
            var squeezeCount by remember { mutableStateOf(0) }
            var imageId = when (state) {
                Lemonade.TREE -> R.drawable.lemon_tree
                Lemonade.LEMON -> R.drawable.lemon_squeeze
                Lemonade.JUICE -> R.drawable.lemon_drink
                else -> R.drawable.lemon_restart
            }
            var imageDescId = when (state) {
                Lemonade.TREE -> R.string.lemon_tree_image_desc
                Lemonade.LEMON -> R.string.lemon_image_desc
                Lemonade.JUICE -> R.string.glass_of_lemonade_image_desc
                else -> R.string.empty_glass_of_lemonade_image_desc
            }
            var descId = when (state) {
                Lemonade.TREE -> R.string.lemon_tree_desc
                Lemonade.LEMON -> R.string.lemon_desc
                Lemonade.JUICE -> R.string.glass_of_lemonade_desc
                else -> R.string.empty_glass_of_lemonade_desc
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                LemonadeButton(imageId, imageDescId) {
                    when (state) {
                        Lemonade.TREE -> {
                            state = Lemonade.LEMON
                            squeezeCount = (2..4).random()
                        }

                        Lemonade.LEMON -> {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                state = Lemonade.JUICE
                            }
                        }

                        Lemonade.JUICE -> {
                            state = Lemonade.REPEAT
                        }

                        else -> {
                            state = Lemonade.TREE
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(descId),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun LemonadeButton(
    imageId: Int, imageDescriptionId: Int, action: () -> Unit
) {
    Button(
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC1D8C3)),
        onClick = action,
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = stringResource(imageDescriptionId),
            alignment = Alignment.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        App()
    }
}

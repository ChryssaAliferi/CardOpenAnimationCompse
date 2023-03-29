package com.example.cardopenanimationcompse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cardopenanimationcompse.ui.theme.CardOpenAnimationCompseTheme
import com.example.cardopenanimationcompse.ui.theme.Purple700
import com.example.cardopenanimationcompse.ui.theme.Teal200
import com.example.cardopenanimationcompse.ui.theme.White

private const val INITIAL_VALUE = 0.0F

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardOpenAnimationCompseTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    GameBackground()
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        IntroCard(-17F)
                        IntroCard(12F)
                        IntroCard(-10F)
                        IntroCard()
                    }

                }
            }
        }
    }
}

@Composable
fun BackgroundNoise() {
    Icon(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.noise),
        contentDescription = null,
        tint = Color.Unspecified
    )
}

@Composable
fun GameBackground(
    starColor: Color = Purple700,
    endColor: Color = Teal200
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(starColor, endColor)
                )
            )
            .systemBarsPadding()

    ) {
        BackgroundNoise()
    }
}


@Composable
fun IntroCard(degrees: Float = INITIAL_VALUE) {
    val rotation = remember { Animatable(initialValue = INITIAL_VALUE) }
    LaunchedEffect(false) {
        rotation.animateTo(
            targetValue = degrees,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearOutSlowInEasing
            ),
        )
    }
    Card(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.card_height))
            .width(dimensionResource(id = R.dimen.card_width))
            .rotate(rotation.value),
        backgroundColor = White,
        elevation = dimensionResource(id = R.dimen.elevation)
    ) {}
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CardOpenAnimationCompseTheme {
        IntroCard()
    }
}

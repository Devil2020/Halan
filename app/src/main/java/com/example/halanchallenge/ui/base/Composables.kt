package com.example.halanchallenge.ui.base

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.halanchallenge.BuildConfig
import com.example.halanchallenge.ui.theme.DarkBlue
import com.example.halanchallenge.ui.theme.LightGreen
import com.example.halanchallenge.ui.theme.LoadingIndicatorBackgroundColor
import com.example.halanchallenge.ui.theme.LoadingIndicatorColor

@Composable
fun CircleView(modifier: Modifier, circleColor: Color) {
    Canvas(modifier = modifier) {
        drawCircle(color = circleColor)
    }
}

@Composable
fun LinearProgressBar(modifier: Modifier, color: Color, backgroundColor: Color) {
    LinearProgressIndicator(
        color = LoadingIndicatorColor,
        backgroundColor = LoadingIndicatorBackgroundColor,
        modifier = modifier
    )
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun HalanLogoView(modifier: Modifier) {
    ConstraintLayout(modifier = modifier) {
        val (circle, arabic, english) = createRefs()
        CircleView(
            modifier = Modifier
                .fillMaxWidth(0.4F)
                .fillMaxHeight(0.2F)
                .constrainAs(circle) {
                    top.linkTo(parent.top, 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, circleColor = LightGreen
        )

        Text(
            text = BuildConfig.RIGHT,
            style = MaterialTheme.typography.h1,
            color = DarkBlue,
            fontSize = TextUnit(55F, TextUnitType.Sp),
            modifier = Modifier.constrainAs(arabic) {
                top.linkTo(circle.top, (-60).dp)
                bottom.linkTo(circle.bottom)
                end.linkTo(circle.absoluteRight, (-70).dp)
            }
        )

        Text(
            text = BuildConfig.LEFT,
            style = MaterialTheme.typography.h1,
            color = DarkBlue,
            fontSize = TextUnit(45F, TextUnitType.Sp),
            modifier = Modifier
                .constrainAs(english) {
                    top.linkTo(circle.top, 30.dp)
                    bottom.linkTo(circle.bottom)
                    linkTo(
                        start = circle.absoluteLeft,
                        end = circle.absoluteRight,
                        bias = 0F,
                        startMargin = (-125).dp
                    )
                }
        )

    }
}
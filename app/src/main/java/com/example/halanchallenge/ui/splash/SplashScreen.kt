package com.example.halanchallenge.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.halanchallenge.BuildConfig
import com.example.halanchallenge.app.coordinator.Directions
import com.example.halanchallenge.app.coordinator.HalanCoordinator
import com.example.halanchallenge.ui.base.CircleView
import com.example.halanchallenge.ui.base.LinearProgressBar
import com.example.halanchallenge.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalUnitApi::class)
@Composable
fun SplashScreen(coordinator: HalanCoordinator) {
    val scope = rememberCoroutineScope()
    val scaleCircleAnimation = rememberInfiniteTransition()
    val circleWidthSize = scaleCircleAnimation.animateFloat(
        initialValue = 0.0F,
        targetValue = 0.7F,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val circleHeightSize = scaleCircleAnimation.animateFloat(
        initialValue = 0.0F,
        targetValue = 0.8F,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    scope.launch {
        coordinator.navigateAfter(
            5000L,
            direction = Directions.AuthenticationDirection,
            closeCurrent = true,
            restoringState = false,
            launchingSingleTop = true
        )
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {
        val (progressbar, circle, arabic, english) = createRefs()

        CircleView(
            modifier = Modifier
                .constrainAs(circle) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        bottom = parent.bottom,
                        top = parent.top,
                        verticalBias = 0.4F
                    )

                    width = Dimension.percent(circleWidthSize.value)
                    height = Dimension.percent(circleHeightSize.value)
                },
            circleColor = LightGreen
        )

        Text(text = BuildConfig.RIGHT,
            color = White,
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .constrainAs(arabic) {
                    end.linkTo(circle.absoluteRight, margin = (-130).dp, goneMargin = 140.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom, 130.dp)
                }
        )

        Text(text = BuildConfig.LEFT,
            color = White,
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .constrainAs(english) {
                    linkTo(
                        start = circle.absoluteLeft,
                        startMargin = (-20).dp,
                        top = circle.top,
                        bottom = parent.bottom,
                        end = parent.absoluteRight,
                        verticalBias = 0.5F,
                        horizontalBias = 0.35F
                    )
                })

        LinearProgressBar(modifier = Modifier
            .constrainAs(progressbar) {
                bottom.linkTo(parent.bottom, 50.dp)
                start.linkTo(parent.start, 30.dp)
                end.linkTo(parent.end, 30.dp, 30.dp)
                width = Dimension.fillToConstraints
            }
            .clip(CircleShape),
            color = LoadingIndicatorColor, backgroundColor = LoadingIndicatorBackgroundColor)

    }
}


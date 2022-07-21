package com.example.halanchallenge.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.halanchallenge.ui.theme.DarkBlue
import com.example.halanchallenge.ui.theme.LoadingIndicatorBackgroundColor
import com.example.halanchallenge.ui.theme.LoadingIndicatorColor

@Composable
fun SplashScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {

        val (progressbar, centercircule, arabicword, englishword) = createRefs()
        LinearProgressIndicator(
            color = LoadingIndicatorColor,
            backgroundColor = LoadingIndicatorBackgroundColor,
            modifier = Modifier.constrainAs(progressbar) {
                bottom.linkTo(parent.bottom, 50.dp)
                start.linkTo(parent.start, 30.dp)
                end.linkTo(parent.end, 30.dp, 30.dp)
                width = Dimension.fillToConstraints
            }
                .clip(CircleShape)
        )

    }
}
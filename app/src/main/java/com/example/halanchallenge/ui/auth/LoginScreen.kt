package com.example.halanchallenge.ui.auth

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.halanchallenge.BuildConfig
import com.example.halanchallenge.R
import com.example.halanchallenge.app.coordinator.HalanCoordinator
import com.example.halanchallenge.ui.splash.CircleView
import com.example.halanchallenge.ui.theme.DarkBlue
import com.example.halanchallenge.ui.theme.EditTextColor
import com.example.halanchallenge.ui.theme.LabelTextColor
import com.example.halanchallenge.ui.theme.LightGreen
import kotlin.math.log

@OptIn(ExperimentalUnitApi::class)
@Composable
fun LoginScreen(coordinator: HalanCoordinator) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (halan, username, password, loginButton) = createRefs()
        val usernameValue = remember {
            TextFieldValue()
        }
        val passwordValue = remember {
            TextFieldValue()
        }
        val passwordVisibility = remember { mutableStateOf(true) }

        HalanLogoView(modifier = Modifier.constrainAs(halan) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        OutlinedTextField(usernameValue,
            { value: TextFieldValue ->
            },
            textStyle = MaterialTheme.typography.h1,
            label = {
                Text(
                    text = "Username",
                    style = MaterialTheme.typography.subtitle1,
                    color = EditTextColor,
                    fontSize = TextUnit(17F, TextUnitType.Sp)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LightGreen,
                unfocusedBorderColor = LightGreen,
                cursorColor = LightGreen
            ),
            placeholder = {
                Text(
                    text = "Likes , MohammedMorse",
                    style = MaterialTheme.typography.subtitle1,
                    color = LabelTextColor,
                    fontSize = TextUnit(15F, TextUnitType.Sp)
                )
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .constrainAs(username) {
                    linkTo(
                        top = halan.bottom,
                        bottom = loginButton.top,
                        verticalBias = 0.3F,
                        start = parent.start,
                        end = parent.end,
                    )
                    height = Dimension.percent(0.1F)
                }
                .fillMaxWidth(0.9F)
        )
        OutlinedTextField(passwordValue,
            { value: TextFieldValue ->
            },
            textStyle = MaterialTheme.typography.h1,
            label = {
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.subtitle1,
                    color = EditTextColor,
                    fontSize = TextUnit(17F, TextUnitType.Sp)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LightGreen,
                unfocusedBorderColor = LightGreen,
                cursorColor = LightGreen
            ),
            trailingIcon = {
                val icon = if (passwordVisibility.value) {
                    R.drawable.ic_show_password_fill
                } else {
                    R.drawable.ic_hide_password_fill
                }

                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "Visibility",
                        tint = Color(0xff717171)
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Password
            ),
            placeholder = {
                Text(
                    text = "123xxxxxxxx",
                    style = MaterialTheme.typography.subtitle1,
                    color = LabelTextColor,
                    fontSize = TextUnit(15F, TextUnitType.Sp)
                )
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .constrainAs(password) {
                    top.linkTo(username.bottom, 10.dp)
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    height = Dimension.percent(0.1F)
                }
                .fillMaxWidth(0.9F)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .constrainAs(loginButton) {
                    bottom.linkTo(parent.bottom, 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, onClick = { }, shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = LightGreen,
                disabledBackgroundColor = Color.Gray
            )
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.button,
                fontSize = TextUnit(20F, TextUnitType.Sp),
                color = Color.White
            )
        }
    }
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
                end.linkTo(circle.end, (-20).dp)
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
                        start = circle.start,
                        end = circle.end,
                        bias = 0F,
                        startMargin = (-20).dp
                    )
                }
        )

    }
}



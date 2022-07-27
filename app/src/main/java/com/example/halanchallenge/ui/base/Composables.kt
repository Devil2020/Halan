package com.example.halanchallenge.ui.base

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.halanchallenge.BuildConfig
import com.example.halanchallenge.R
import com.example.halanchallenge.ui.theme.*

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
                        startMargin = (-30).dp
                    )
                }
        )

    }
}

@Composable
fun OutlinedEditText(
    input: MutableState<TextFieldValue> = remember {
        mutableStateOf(TextFieldValue())
    },
    error: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    validationFunc: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            input.value,
            { value: TextFieldValue ->
                input.value = value
                error.value = !validationFunc.invoke()
            },
            textStyle = MaterialTheme.typography.body2,
            label = {
                Text(
                    text = stringResource(id = R.string.user_name),
                    style = MaterialTheme.typography.subtitle1,
                    color = EditTextColor,
                    fontSize = _17SP
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LightGreen,
                unfocusedBorderColor = LightGreen,
                cursorColor = LightGreen,
                textColor = EditTextColor,
            ),
            keyboardActions = KeyboardActions { },
            isError = error.value,
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.user_name_example),
                    style = MaterialTheme.typography.subtitle1,
                    color = LabelTextColor,
                    fontSize = _15SP
                )
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        )
        if (error.value) {
            println("---> Error On Email")
            Text(
                text = stringResource(id = R.string.wrong_username_label),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun OutlinedPasswordEditText(
    input: MutableState<TextFieldValue> = remember {
        mutableStateOf(TextFieldValue())
    },
    passwordVisibility: MutableState<Boolean> = remember {
        mutableStateOf(true)
    },
    error: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    validationFunc: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            input.value,
            { value: TextFieldValue ->
                input.value = value
                error.value = !validationFunc.invoke()
            },
            textStyle = MaterialTheme.typography.body2,
            label = {
                Text(
                    text = stringResource(id = R.string.password),
                    style = MaterialTheme.typography.subtitle1,
                    color = EditTextColor,
                    fontSize = _17SP
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LightGreen,
                unfocusedBorderColor = LightGreen,
                cursorColor = LightGreen,
                textColor = EditTextColor,
            ),
            trailingIcon = {
                val icon = if (passwordVisibility.value) {
                    R.drawable.ic_show_password_fill
                } else {
                    R.drawable.ic_hide_password_fill
                }

                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "Visibility",
                        tint = Color(0xff717171)
                    )
                }
            },
            keyboardActions = KeyboardActions { },
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Password
            ),
            isError = error.value,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.password_example),
                    style = MaterialTheme.typography.subtitle1,
                    color = LabelTextColor,
                    fontSize = _15SP
                )
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        )

        if (error.value) {
            println("---> Error On Password")
            Text(
                text = stringResource(id = R.string.wrong_password_label),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
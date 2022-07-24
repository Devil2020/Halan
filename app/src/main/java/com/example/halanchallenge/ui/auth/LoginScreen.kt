package com.example.halanchallenge.ui.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.halanchallenge.R
import com.example.halanchallenge.app.coordinator.HalanCoordinator
import com.example.halanchallenge.ui.base.HalanLogoView
import com.example.halanchallenge.ui.theme.*
import kotlin.math.sin

@OptIn(ExperimentalUnitApi::class)
@Composable
fun LoginScreen(coordinator: HalanCoordinator) {
    val vm: LoginViewModel = viewModel()
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (halan, username, password, loginButton) = createRefs()
        HalanLogoView(modifier = Modifier.constrainAs(halan) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        OutlinedTextField(vm.usernameValue.value,
            { value: String ->
                vm.usernameValue.value = value
            },
            textStyle = MaterialTheme.typography.h1,
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
            ),
            isError = vm.usernameError.value,
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
            modifier = Modifier
                .constrainAs(username) {
                    linkTo(
                        top = halan.bottom,
                        bottom = loginButton.top,
                        verticalBias = 0.15F,
                        start = parent.start,
                        end = parent.end,
                    )
                    height = Dimension.percent(0.09F)
                }
                .fillMaxWidth(0.9F)
        )

        OutlinedTextField(vm.passwordValue.value,
            { value: String ->
                vm.passwordValue.value = value
            },
            textStyle = MaterialTheme.typography.h1,
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
                cursorColor = LightGreen
            ),
            trailingIcon = {
                val icon = if (vm.passwordVisibility.value) {
                    R.drawable.ic_show_password_fill
                } else {
                    R.drawable.ic_hide_password_fill
                }

                IconButton(onClick = {
                    vm.passwordVisibility.value = !vm.passwordVisibility.value
                }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "Visibility",
                        tint = Color(0xff717171)
                    )
                }
            },
            visualTransformation = if (vm.passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Password
            ),
            isError = vm.passwordError.value,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.password_example),
                    style = MaterialTheme.typography.subtitle1,
                    color = LabelTextColor,
                    fontSize = _15SP
                )
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .constrainAs(password) {
                    top.linkTo(username.bottom, 10.dp)
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    height = Dimension.percent(0.09F)
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
                }, onClick = { }, shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = LightGreen,
                disabledBackgroundColor = Color.Gray
            )
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.button,
                fontSize = _20SP,
                color = Color.White
            )
        }
    }
}
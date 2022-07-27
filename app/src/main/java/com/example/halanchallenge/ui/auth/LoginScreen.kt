package com.example.halanchallenge.ui.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.halanchallenge.R
import com.example.halanchallenge.app.coordinator.HalanCoordinator
import com.example.halanchallenge.ui.base.HalanLogoView
import com.example.halanchallenge.ui.base.OutlinedEditText
import com.example.halanchallenge.ui.base.OutlinedPasswordEditText
import com.example.halanchallenge.ui.theme.*
import com.example.halanchallenge.utils.validator.InputValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalUnitApi::class)
@Composable
fun LoginScreen(coordinator: HalanCoordinator) {
    val vm: LoginViewModel = getViewModel()
    val scope : CoroutineScope = rememberCoroutineScope()
    val usernameValue: MutableState<TextFieldValue> = remember {
        mutableStateOf(TextFieldValue())
    }
    val passwordValue: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue()) }
    val passwordVisibility: MutableState<Boolean> = remember { mutableStateOf(true) }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (halan, username, password, loginButton) = createRefs()
        HalanLogoView(modifier = Modifier.constrainAs(halan) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        OutlinedEditText(
            usernameValue,
            validationFunc = { InputValidator.isUsernameValid(usernameValue.value.text) },
            modifier = Modifier
                .constrainAs(username) {
                    linkTo(
                        top = halan.bottom,
                        bottom = loginButton.top,
                        verticalBias = 0.15F,
                        start = parent.start,
                        end = parent.end,
                    )
                })

        OutlinedPasswordEditText(
            passwordValue,
            validationFunc = { InputValidator.isPasswordValid(passwordValue.value.text) },
            modifier = Modifier
                .constrainAs(password) {
                    top.linkTo(username.bottom, 10.dp)
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                })

        Button(
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .constrainAs(loginButton) {
                    bottom.linkTo(parent.bottom, 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onClick = { vm.logIn(usernameValue.value.text, passwordValue.value.text) },
            shape = MaterialTheme.shapes.small,
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
package com.nikhilsai.playfield.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikhilsai.playfield.R
import com.nikhilsai.playfield.Playfieldcomponents.ButtonComponent
import com.nikhilsai.playfield.Playfieldcomponents.ClickableLoginTextComponent
import com.nikhilsai.playfield.Playfieldcomponents.DividerTextComponent
import com.nikhilsai.playfield.Playfieldcomponents.HeadingTextComponent
import com.nikhilsai.playfield.Playfieldcomponents.MyTextFieldComponent
import com.nikhilsai.playfield.Playfieldcomponents.PasswordTextFieldComponent
import com.nikhilsai.playfield.Playfielddata.Playfieldsigninvm
import com.nikhilsai.playfield.Playfielddata.Playfieldlogin.Playfieldevent
import com.nikhilsai.playfield.navigation.AppRouter
import com.nikhilsai.playfield.navigation.Screen
import com.nikhilsai.playfield.navigation.SystemBackButtonHandler


@Composable
fun LoginScreen(loginViewModel: Playfieldsigninvm = viewModel()) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column( modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.size(350.dp),
                    painter = painterResource(id = R.drawable.pf),
                    contentDescription = null)
                Spacer(modifier = Modifier.height(0.dp))
                HeadingTextComponent(value = "Login")
                Spacer(modifier = Modifier.height(10.dp))

                MyTextFieldComponent(labelValue = stringResource(id = com.nikhilsai.playfield.R.string.email),
                    painterResource(id = com.nikhilsai.playfield.R.drawable.message),
                    onTextChanged = { loginViewModel.onEvent(Playfieldevent.EmailChanged(it)) },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id =com.nikhilsai.playfield. R.string.password),
                    painterResource(id = com.nikhilsai.playfield.R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.onEvent(Playfieldevent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(10.dp))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = com.nikhilsai.playfield.R.string.login),
                    onButtonClicked = {
                       loginViewModel.onEvent(Playfieldevent.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    AppRouter.navigateTo(Screen.SignUpScreen)
                })
            }
        }

        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }

    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.SignUpScreen)
    }

}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}


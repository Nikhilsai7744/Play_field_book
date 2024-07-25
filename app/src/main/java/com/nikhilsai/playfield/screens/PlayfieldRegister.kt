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
import com.nikhilsai.playfield.Playfieldcomponents.ButtonComponent
import com.nikhilsai.playfield.Playfieldcomponents.CheckboxComponent
import com.nikhilsai.playfield.Playfieldcomponents.ClickableLoginTextComponent
import com.nikhilsai.playfield.Playfieldcomponents.DividerTextComponent
import com.nikhilsai.playfield.Playfieldcomponents.HeadingTextComponent
import com.nikhilsai.playfield.Playfieldcomponents.MyTextFieldComponent
import com.nikhilsai.playfield.Playfieldcomponents.PasswordTextFieldComponent
import com.nikhilsai.playfield.Playfielddata.Playfieldregister.PlayfieldRegisterEvent
import com.nikhilsai.playfield.Playfielddata.Playfieldregister.PLayfieldviewmodel
import com.nikhilsai.playfield.navigation.AppRouter
import com.nikhilsai.playfield.navigation.Screen


@Composable
fun SignUpScreen(signupViewModel: PLayfieldviewmodel = viewModel()) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,

    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center)
            {
                Image(
                    modifier = Modifier.size(280.dp),
                    painter = painterResource(id = com.nikhilsai.playfield.R.drawable.pf),
                    contentDescription = null)
                  HeadingTextComponent(value = "Playfield  Booking")
                  Spacer(modifier = Modifier.height(1.dp))
                  MyTextFieldComponent(
                    labelValue = stringResource(id = com.nikhilsai.playfield.R.string.first_name),
                    painterResource(id =com.nikhilsai.playfield. R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(PlayfieldRegisterEvent.FirstNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = com.nikhilsai.playfield.R.string.last_name),
                    painterResource = painterResource(id =com.nikhilsai.playfield. R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(PlayfieldRegisterEvent.LastNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id =com.nikhilsai.playfield. R.string.email),
                    painterResource = painterResource(id = com.nikhilsai.playfield.R.drawable.message),
                    onTextChanged = {
                        signupViewModel.onEvent(PlayfieldRegisterEvent.EmailChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = com.nikhilsai.playfield.R.string.password),
                    painterResource = painterResource(id = com.nikhilsai.playfield.R.drawable.ic_lock),
                    onTextSelected = {
                        signupViewModel.onEvent(PlayfieldRegisterEvent.PasswordChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.passwordError
                )

                CheckboxComponent(value = stringResource(id = com.nikhilsai.playfield.R.string.terms_and_conditions),
                    onTextSelected = {
                        AppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                    },
                    onCheckedChange = {
                        signupViewModel.onEvent(PlayfieldRegisterEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                ButtonComponent(
                    value = stringResource(id =com.nikhilsai.playfield. R.string.register),
                    onButtonClicked = {
                        signupViewModel.onEvent(PlayfieldRegisterEvent.RegisterButtonClicked)
                    },
                    isEnabled = signupViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(10.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    AppRouter.navigateTo(Screen.LoginScreen)
                })
            }
        }


        if(signupViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }

}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}
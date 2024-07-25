package com.nikhilsai.playfield.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikhilsai.playfield.Playfielddata.Playfieldhome.HomeViewModel
import com.nikhilsai.playfield.navigation.AppRouter
import com.nikhilsai.playfield.navigation.Screen
import com.nikhilsai.playfield.screens.HomeScreen
import com.nikhilsai.playfield.screens.LoginScreen
import com.nikhilsai.playfield.screens.SignUpScreen
import com.nikhilsai.playfield.screens.TermsAndConditionsScreen


@Composable
fun PlayfieldApps(homeViewModel: HomeViewModel = viewModel()) {

    homeViewModel.checkForActiveSession()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {

        if (homeViewModel.isUserLoggedIn.value == true) {
            AppRouter.navigateTo(Screen.HomeScreen)
        }

        Crossfade(targetState = AppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }
            }
        }

    }
}
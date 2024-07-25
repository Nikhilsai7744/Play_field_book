package com.nikhilsai.playfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nikhilsai.playfield.app.PlayfieldApps


class PlayfieldMain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayfieldApps()
        }
    }
}




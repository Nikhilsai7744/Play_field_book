package com.nikhilsai.playfield.Playfielddata

import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val title: String,
    val description: String,
    val itemId: String,
    val icon: ImageVector
)
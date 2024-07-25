package com.nikhilsai.playfield.Playfielddata.Playfieldlogin

sealed class Playfieldevent{

    data class EmailChanged(val email:String): Playfieldevent()
    data class PasswordChanged(val password: String) : Playfieldevent()

    object LoginButtonClicked : Playfieldevent()
}

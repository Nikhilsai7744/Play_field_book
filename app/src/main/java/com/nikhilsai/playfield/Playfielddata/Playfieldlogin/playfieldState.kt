package com.nikhilsai.playfield.Playfielddata

data class playfieldState(
    var email  :String = "",
    var password  :String = "",

    var emailError :Boolean = false,
    var passwordError : Boolean = false

)

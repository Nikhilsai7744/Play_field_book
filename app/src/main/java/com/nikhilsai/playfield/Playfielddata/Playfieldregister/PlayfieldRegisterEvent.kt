package  com.nikhilsai.playfield.Playfielddata.Playfieldregister

sealed class PlayfieldRegisterEvent{

    data class FirstNameChanged(val firstName:String) : PlayfieldRegisterEvent()
    data class LastNameChanged(val lastName:String) : PlayfieldRegisterEvent()
    data class EmailChanged(val email:String): PlayfieldRegisterEvent()
    data class PasswordChanged(val password: String) : PlayfieldRegisterEvent()

    data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : PlayfieldRegisterEvent()

    object RegisterButtonClicked : PlayfieldRegisterEvent()
}

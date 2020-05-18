package com.joaostanzione.iddog.robot

import com.joaostanzione.iddog.CustomActions
import com.joaostanzione.iddog.R

open class LoginRobot : BaseRobot() {

    fun enterLoginValidEmail() = apply {
        enterText(R.id.loginEmailEditText, "emai@email.com")
    }

    fun checkSignInButtonIsDisplayed() = apply {
        checkViewIsDisplayed(R.id.loginButton)
    }

    fun enterLoginInvalidEmail() = apply {
        enterText(R.id.loginEmailEditText, "emai@email")
    }

    fun clickSignInButton() = apply {
        clickOnView(R.id.loginButton)
    }

    fun checkSignInButtonIsNotDisplayed() = apply {
        checkViewIsNotDisplayed(R.id.loginButton)
    }

    fun checkLoginErrorIsDisplayed() = apply {
        CustomActions.waitFor(30000)
        checkViewIsDisplayed(R.id.loginError)
    }
}

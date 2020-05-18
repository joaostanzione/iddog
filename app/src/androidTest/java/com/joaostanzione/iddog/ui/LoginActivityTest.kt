package com.joaostanzione.iddog.ui

import androidx.test.espresso.intent.rule.IntentsTestRule
import com.joaostanzione.iddog.BaseTest
import com.joaostanzione.iddog.robot.DogsRobot
import com.joaostanzione.iddog.robot.LoginRobot
import com.joaostanzione.iddog.ui.login.LoginActivity
import org.junit.Rule
import org.junit.Test

internal class LoginActivityTest : BaseTest() {

    @Rule
    @JvmField
    val intentsTestRule = IntentsTestRule(LoginActivity::class.java, false, false)

    private lateinit var activity: LoginActivity

    @Test
    fun shouldDisplaySignInButton_withValidEmailTyped() {
        activity = intentsTestRule.launchActivity(null)

        LoginRobot()
            .enterLoginValidEmail()
            .checkSignInButtonIsDisplayed()
    }

    @Test
    fun shouldNotDisplaySignInButton_withInvalidEmailTyped() {
        activity = intentsTestRule.launchActivity(null)

        LoginRobot()
            .enterLoginInvalidEmail()
            .checkSignInButtonIsNotDisplayed()
    }

    @Test
    fun shouldLoadDogsScreen_afterSuccessfulLogin() {
        setupLoginMock()
        activity = intentsTestRule.launchActivity(null)

        LoginRobot()
            .enterLoginValidEmail()
            .clickSignInButton()

        DogsRobot()
            .checkDogsMenuIsDisplayed()
    }
}

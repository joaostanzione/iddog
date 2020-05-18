package com.joaostanzione.iddog.ui

import androidx.test.espresso.intent.rule.IntentsTestRule
import com.joaostanzione.iddog.BaseTest
import com.joaostanzione.iddog.robot.DogsRobot
import com.joaostanzione.iddog.ui.dogs.DogsActivity
import org.junit.Rule
import org.junit.Test

internal class DogsActivityTest : BaseTest() {

    @Rule
    @JvmField
    val intentsTestRule = IntentsTestRule(DogsActivity::class.java, false, false)

    private lateinit var activity: DogsActivity

    @Test
    fun shouldDisplayDogsScreenSuccessfully_onSuccessfulApiCall() {
        setupDogsMock()
        activity = intentsTestRule.launchActivity(null)

        DogsRobot()
            .checkDogsMenuIsDisplayed()
            .checkDogsRecyclerViewIsDisplayed()
    }

    @Test
    fun shouldDisplayErrorScreenMessage_onApiError() {
        setupDogsError()
        activity = intentsTestRule.launchActivity(null)

        DogsRobot()
            .checkDogsMenuIsDisplayed()
            .checkDogsErrorMessageDisplayed()
    }

    @Test
    fun shouldDisplayEmptyScreenMessage_onEmptyApiList() {
        setupDogsMock(DOGS_JSON_EMPTY)
        activity = intentsTestRule.launchActivity(null)

        DogsRobot()
            .checkDogsMenuIsDisplayed()
            .checkDogsEmptyMessageDisplayed()
    }
}

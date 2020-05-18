package com.joaostanzione.iddog.ui

import androidx.test.espresso.intent.rule.IntentsTestRule
import com.joaostanzione.iddog.BaseTest
import com.joaostanzione.iddog.infrastructure.extension.recordScreenshot
import com.joaostanzione.iddog.ui.dogs.DogsActivity
import org.junit.Rule
import org.junit.Test

internal class DogsActivityScreenshotTest : BaseTest() {

    @Rule
    @JvmField
    val intentsTestRule = IntentsTestRule(DogsActivity::class.java, false, false)

    private lateinit var activity: DogsActivity

    @Test
    fun should_display_dogs_screen_successfully() {
        setupDogsMock()
        activity = intentsTestRule.launchActivity(null)

        activity.recordScreenshot("Dogs success screen")
    }

    @Test
    fun should_display_dogs_empty_screen() {
        setupDogsMock(DOGS_JSON_EMPTY)
        activity = intentsTestRule.launchActivity(null)

        activity.recordScreenshot("Dogs empty screen")
    }

    @Test
    fun should_display_dogs_error_screen() {
        setupDogsError()
        activity = intentsTestRule.launchActivity(null)

        activity.recordScreenshot("Dogs error screen")
    }
}

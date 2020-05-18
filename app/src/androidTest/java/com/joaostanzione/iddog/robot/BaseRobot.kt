package com.joaostanzione.iddog.robot

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers

open class BaseRobot {

    fun checkViewIsDisplayed(@IdRes viewId: Int) = apply {
        Espresso.onView(withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun checkViewIsNotDisplayed(@IdRes viewId: Int) = apply {
        Espresso.onView(withId(viewId))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    fun enterText(@IdRes viewId: Int, text: String) = apply {
        Espresso.onView(Matchers.allOf(withId(viewId)))
            .perform(replaceText(text))
    }

    fun clickOnView(@IdRes viewId: Int) = apply {
        Espresso.onView(withId(viewId))
            .perform(click())
    }

    fun checkTextIsDisplayed(@StringRes stringResId: Int) = apply {
        Espresso.onView(withText(stringResId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}

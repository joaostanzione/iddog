package com.joaostanzione.iddog.robot

import com.joaostanzione.iddog.R

open class DogsRobot : BaseRobot() {

    fun checkDogsMenuIsDisplayed() = apply {
        checkViewIsDisplayed(R.id.dogsTabs)
    }

    fun checkDogsRecyclerViewIsDisplayed() = apply {
        checkViewIsDisplayed(R.id.dogsRecyclerView)
    }

    fun checkDogsErrorMessageDisplayed() = apply {
        checkViewIsDisplayed(R.id.dogsErrorView)
        checkTextIsDisplayed(R.string.error_message)
    }

    fun checkDogsEmptyMessageDisplayed() = apply {
        checkViewIsDisplayed(R.id.dogsEmpty)
        checkTextIsDisplayed(R.string.empty_message)
    }
}

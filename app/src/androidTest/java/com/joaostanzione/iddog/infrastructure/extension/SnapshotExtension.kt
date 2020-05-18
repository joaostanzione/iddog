package com.joaostanzione.iddog.infrastructure.extension

import android.app.Activity
import androidx.test.espresso.Espresso.onIdle
import com.facebook.testing.screenshot.Screenshot

fun Activity.recordScreenshot(fileName: String) {
    onIdle()
    Screenshot.snapActivity(this).setName(fileName).record()
}

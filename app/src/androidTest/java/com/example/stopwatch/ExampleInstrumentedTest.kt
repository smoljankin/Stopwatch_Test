package com.example.stopwatch

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*

import androidx.test.espresso.matcher.ViewMatchers.withId

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule

import androidx.test.espresso.action.ViewActions.click

import androidx.test.filters.LargeTest

import org.junit.Rule




/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.stopwatch", appContext.packageName)
    }

    @Test
    fun startButton_Click() {

        val scenario = activityScenarioRule.scenario

        // Виконуємо дії з UI
        onView(withId(R.id.startButton)).perform(click())

    }

    @Test
    fun start_stop_Button_Click() {

        val scenario = activityScenarioRule.scenario

        // Виконуємо дії з UI
        onView(withId(R.id.startButton)).perform(click())
        Thread.sleep(5000);
        onView(withId(R.id.stopButton)).perform(click())


    }
}
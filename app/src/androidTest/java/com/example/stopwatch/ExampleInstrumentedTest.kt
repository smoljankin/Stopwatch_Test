package com.example.stopwatch

import android.os.Handler
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*

import androidx.test.espresso.matcher.ViewMatchers.withId

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.ext.junit.rules.ActivityScenarioRule

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withText

import androidx.test.filters.LargeTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not

import org.junit.Rule
import java.util.concurrent.TimeUnit


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

        onView(withId(R.id.startButton)).perform(click())
    }

    @Test
    fun timer_Text() {
        // Отримуємо посилання на активність через сценарій
        val scenario = activityScenarioRule.scenario

        //перевірка чи  текст  "00:00:00.00"

        onView(withId(R.id.timerText)).check(matches(withText("00:00:00.00")))
    }
    @Test
    fun testStartButtonActivation() {
        // Перевірка, що кнопка "Start" активна перед кліком
        onView(withId(R.id.startButton))
            .check(matches(isEnabled()))

        // Клік на кнопку "Start"
        onView(withId(R.id.startButton))
            .perform(click())

        // Перевірка, що кнопка "Start" стала неактивною після кліку
        onView(withId(R.id.startButton))
            .check(matches(not(isEnabled())))

        // Перевірка, що кнопка "Stop" стала активною після кліку
        onView(withId(R.id.stopButton))
            .check(matches(isEnabled()))
    }

    @Test
    fun testTimerButton() {
        val scenario = activityScenarioRule.scenario

       onView(withId(R.id.startButton)).perform(click())

       Thread.sleep(5000)

        onView(withId(R.id.stopButton)).perform(click())
        onView(withId(R.id.timerText)).check(matches(withText("00:00:00.00"))) // Assuming the timer shows "1" after 1 second
    }





}


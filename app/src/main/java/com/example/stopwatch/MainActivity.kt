package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var storeButton: Button
    private lateinit var storedItems: ListView

    private val storedItemsData: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.timerText)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)
        storeButton = findViewById(R.id.storeButton)
        storedItems = findViewById(R.id.storedItems)
    }

    private fun startTimer() {
        resetButton.isEnabled = true
        stopButton.isVisible = true
        startButton.isVisible = false
    }

    private fun stopTimer() {
        stopButton.isVisible = false
        startButton.isVisible = true
    }

    private fun resetTimer() {
        stopButton.isVisible = false
        startButton.isVisible = true
        resetButton.isEnabled = false
    }

    private fun storeCurrentTime() {

    }
}
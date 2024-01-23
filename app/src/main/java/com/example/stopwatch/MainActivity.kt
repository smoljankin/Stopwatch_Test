package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
    private lateinit var adapter: ArrayAdapter<String>

    private var isTimerRunning = false
    private var startTime: Long = 0
    private var elapsedTime: Long = 0

    private val handler = Handler(Looper.getMainLooper())
    private val updateTask = object : Runnable {
        override fun run() {
            if (isTimerRunning) {
                elapsedTime = System.currentTimeMillis() - startTime
                updateChronoMeter(elapsedTime)
                handler.postDelayed(this, 10)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.timerText)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)
        storeButton = findViewById(R.id.storeButton)
        storedItems = findViewById(R.id.storedItems)

        stopButton.isVisible = false
        resetButton.isEnabled = false
        storeButton.isEnabled = false

        adapter = object : ArrayAdapter<String>(
            this,
            R.layout.text_and_button,
            R.id.item_text,
            storedItemsData
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val closeButton: Button = view.findViewById(R.id.close_button)

                // Handle button click
                closeButton.setOnClickListener {
                    storedItemsData.removeAt(position)
                    this.notifyDataSetChanged()
                }

                return view
            }
        }

        storedItems.adapter = adapter

        startButton.setOnClickListener {
            startTimer()
        }

        stopButton.setOnClickListener {
            stopTimer()
        }

        resetButton.setOnClickListener {
            resetTimer()
        }

        storeButton.setOnClickListener {
            storeCurrentTime()
        }

    }

    private fun startTimer() {
        resetButton.isEnabled = true
        stopButton.isVisible = true
        storeButton.isEnabled = true
        startButton.isVisible = false

        if (!isTimerRunning) {
            startTime = System.currentTimeMillis() - elapsedTime
            isTimerRunning = true
            resetButton.isEnabled = true
            handler.postDelayed(updateTask, 0)
        }
    }

    private fun stopTimer() {
        stopButton.isVisible = false
        startButton.isVisible = true

        if (isTimerRunning) {
            isTimerRunning = false
            handler.removeCallbacks(updateTask)
        }
    }

    private fun resetTimer() {
        stopButton.isVisible = false
        startButton.isVisible = true
        resetButton.isEnabled = false
        storeButton.isEnabled = false

        storedItemsData.clear()
        adapter.notifyDataSetChanged()

        elapsedTime = 0
        isTimerRunning = false
        updateChronoMeter(elapsedTime)
    }

    private fun storeCurrentTime() {
        storedItemsData.add(timerText.text.toString())
        adapter.notifyDataSetChanged()
    }
    private fun updateChronoMeter(elapsedTime: Long) {
        timerText.text = formatElapsedTime(elapsedTime)
    }

    private fun formatElapsedTime(elapsedTime: Long): String {
        val seconds = elapsedTime / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val millis = (elapsedTime % 1000) / 10

        return String.format("%02d:%02d:%02d.%02d", hours, minutes % 60, seconds % 60, millis)
    }

}
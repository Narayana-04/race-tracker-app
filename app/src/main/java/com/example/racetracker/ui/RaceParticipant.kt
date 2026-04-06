package com.example.racetracker.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay

class RaceParticipant(
    val name: String,
    private val progressIncrement: Int = 1,
    val maxProgress: Int = 100,
    private val progressDelayMillis: Long = 100L,
    private val initialProgress: Int = 0
) {

    var currentProgress by mutableStateOf(initialProgress)
        private set

    val progressFactor: Float
        get() = currentProgress / maxProgress.toFloat()

    suspend fun run() {
        try {
            while (currentProgress < maxProgress) {
                delay(progressDelayMillis)
                currentProgress += progressIncrement
            }
        } catch (e: CancellationException) {
            Log.e("RaceParticipant", "$name cancelled")
            throw e
        }
    }

    fun reset() {
        currentProgress = initialProgress
    }
}
package com.example.questify

import com.example.questify.util.formatToWeeksAndDays
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.UUID
import kotlin.math.max


data class QuestModel (
    val id: UUID = UUID.randomUUID(),
    var name: String = "Base Quest",
    var description: String = "",
    val startDate: LocalDate = LocalDate.now(),
    var timeLimit: Int? = null,
    var endDate: LocalDate? = null,
    var targetReps: Int = 20,
    var entryList: MutableList<EntryModel> = mutableListOf(),
) {
    fun addEntry(entry: EntryModel) {
        entryList.add(entry)
    }

    val currentReps:Int get() { return entryList.size}

    fun getStartDateString(): String = startDate.toString()

    fun getTimeRemainingString(): String {
        val remainingTime = getTimeRemaining()

        return formatToWeeksAndDays(remainingTime)
    }

    private fun getTimePassed(): Long {
        val currentDate = LocalDate.now()
        return ChronoUnit.DAYS.between(currentDate, startDate)
    }

    private fun getTimeRemaining(): Long? {
        return timeLimit?.minus(getTimePassed())
    }

    fun getPredictedStatus(): Status {
        if(timeLimit == null) {
            return Status.NO_LIMIT
        }
        if(currentReps == 0 && getTimePassed() >= timeLimit!! / targetReps) {
            return Status.BEHIND
        }
        if(currentReps == 0) {
            return Status.ON_TARGET
        }

        val daysToComplete = predictDaysToComplete()

        return when {
            timeLimit!! / daysToComplete > Status.AHEAD.threshold -> Status.AHEAD
            timeLimit!! / daysToComplete > Status.ON_TARGET.threshold -> Status.ON_TARGET
            else -> Status.BEHIND
        }
    }

    private fun predictDaysToComplete(): Long {
        val daysPassed = max(getTimePassed(), 1)
        val completionSpeed = 1.0 * currentReps / daysPassed
        val daysToComplete = (targetReps / completionSpeed).toLong()

        return daysToComplete
    }

}

enum class Status(val text: String, val threshold: Double) {
    NO_LIMIT("no limit set", 0.0),
    AHEAD("ahead", 1.2),
    ON_TARGET("on target", 0.8),
    BEHIND("behind", 0.0)
}
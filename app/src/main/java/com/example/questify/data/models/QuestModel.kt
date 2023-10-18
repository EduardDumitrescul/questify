package com.example.questify.data.models

import com.example.questify.util.formatToWeeksAndDays
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.UUID
import kotlin.math.max


data class QuestModel (
    val id: UUID = UUID.randomUUID(),
    var name: String = "",
    var description: String = "",
    val startDate: LocalDate = LocalDate.now(),
    var timeLimit: Int? = null,
    var endDate: LocalDate? = null,
    var targetReps: Int = 0,
    var entryList: MutableList<EntryModel> = mutableListOf(),
    var status: Status = Status.ACTIVE,
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

    fun getPredictedStatus(): ProgressStatus {
        if(timeLimit == null) {
            return ProgressStatus.NO_LIMIT
        }
        if(currentReps == 0 && getTimePassed() >= timeLimit!! / targetReps) {
            return ProgressStatus.BEHIND
        }
        if(currentReps == 0) {
            return ProgressStatus.ON_TARGET
        }

        val daysToComplete = predictDaysToComplete()

        return when {
            timeLimit!! / daysToComplete > ProgressStatus.AHEAD.threshold -> ProgressStatus.AHEAD
            timeLimit!! / daysToComplete > ProgressStatus.ON_TARGET.threshold -> ProgressStatus.ON_TARGET
            else -> ProgressStatus.BEHIND
        }
    }

    private fun predictDaysToComplete(): Long {
        val daysPassed = max(getTimePassed(), 1)
        val completionSpeed = 1.0 * currentReps / daysPassed
        val daysToComplete = (targetReps / completionSpeed).toLong()



        return daysToComplete
    }

    fun getStreak(): Long {
        val dates = (entryList.map { it.date }).reversed()
        if(dates.isEmpty() || ChronoUnit.DAYS.between(dates[0], LocalDate.now()) > 1) {
            return 0
        }
        var i = 0
        while(i < dates.size-1) {
            val daysBetween = ChronoUnit.DAYS.between(dates[i+1], dates[i])
            if(daysBetween > 1) {
                break
            }
            i ++
        }

        var days = ChronoUnit.DAYS.between(dates[i], LocalDate.now())
        if(ChronoUnit.DAYS.between(dates[0], LocalDate.now()) == 0L) {
            days ++
        }
        return days

    }

}

enum class Status(val id: Int, val text: String) {
    ACTIVE(0, "active"),
    ARCHIVED(1, "archived"),
    COMPLETED(2, "completed"),
}

enum class ProgressStatus(val text: String, val threshold: Double) {
    NO_LIMIT("no limit set", 0.0),
    AHEAD("ahead", 1.2),
    ON_TARGET("on target", 0.8),
    BEHIND("behind", 0.0)
}
package com.example.questify

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.UUID


data class QuestModel (
    val id: UUID = UUID.randomUUID(),
    var name: String = "Base Quest",
    var description: String = "",
    val startDate: LocalDate = LocalDate.now(),
    var timeLimit: Int? = null,
    var endDate: LocalDate? = null,
    var targetReps: Int = 20,
    var currentReps: Int = 0
) {
    fun performRep() {
        currentReps += 1
    }

    fun getStartDateString(): String = startDate.toString()

    fun getTimeRemainingString(): String {
        val currentDate = LocalDate.now()
        val days =  ChronoUnit.DAYS.between(currentDate, startDate)

        if(timeLimit == null) {
            return "no limit set"
        }

        return formatToWeeksAndDays(timeLimit!! - days)
    }


}

private fun formatToWeeksAndDays(days: Long): String {
    val weeks = days / 7
    val daysReduced = days % 7

    var output = ""
    when(weeks) {
        0L -> {}
        1L -> {
            output += "1 week"
        }
        else -> {
            output += "$weeks weeks"
        }
    }

    if(daysReduced > 0 && weeks > 0) {
        output += ','
    }

    when(daysReduced) {
        0L -> {}
        1L -> {
            output += "1 day"
        }
        else -> {
            output += "$daysReduced days"
        }
    }

    return output
}
package com.example.questify

import com.example.questify.util.formatToWeeksAndDays
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

        val remainingTime = timeLimit!! - days

        return formatToWeeksAndDays(remainingTime)
    }


}
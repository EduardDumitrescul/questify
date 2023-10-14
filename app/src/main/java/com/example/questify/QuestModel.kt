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
    var entryList: MutableList<EntryModel> = mutableListOf(),
) {
    fun addEntry(entry: EntryModel) {
        entryList.add(entry)
    }

    val currentReps:Int get() { return entryList.size}

    fun getStartDateString(): String = startDate.toString()

    fun getTimeRemainingString(): String {
        val currentDate = LocalDate.now()
        val days =  ChronoUnit.DAYS.between(currentDate, startDate)

        val remainingTime = timeLimit?.minus(days)

        return formatToWeeksAndDays(remainingTime)
    }
}
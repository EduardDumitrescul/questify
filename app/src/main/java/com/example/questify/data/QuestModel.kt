@file:Suppress("SameReturnValue", "SameReturnValue")

package com.example.questify.data

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.UUID
import kotlin.math.ceil


private const val PREDICTION_WEIGHT_FRACTION = 0.8

/**
 * @param deadline number of days from dateCreated
 */
data class QuestModel(
    val id: UUID = UUID.randomUUID(),
    var name: String = "Base Quest",
    var description: String = "",
    var target: Int = 0,
    var requirementTime: Int = 0,
//    val type: Type = Type.RepTarget,
    val dateCreated: LocalDate = LocalDate.now(),
    val hasEnded: Boolean = false,
    val dateEnded: LocalDate = LocalDate.now(),
    val hasDeadline: Boolean = false,
    var deadline: Int = 0,

    var entryList: List<EntryModel> = listOf(),
) {

    companion object {
        enum class Type(type: Int) {
            RepTarget(0),
            TimeTarget(1)
        }
    }


    // function to check if this quest has been completed for today
    @Suppress("SameReturnValue")
    fun isCompleted(): Boolean {
        return entryList.size >= target
    }

    private fun formatDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yy")
        return date.format(formatter)
    }

    fun getDateCreatedFormatted(): String = formatDate(dateCreated)

    fun getDateEndedFormatted(): String = if (hasEnded) formatDate(dateEnded) else "still active"

    fun getDeadlineFormatted(): String = if(deadline > 0) "TODO" else "none"

    fun getRepRequirements(): String = if(requirementTime > 0) "$requirementTime min" else "none"
    fun getTimeRemaining(): String {
        val daysLeft = daysUntilDeadline()
        return when {
            daysLeft > 6 * 30 -> {
                (daysLeft / 30).toString() + " months"
            }
            daysLeft >= 4 * 7 -> {
                (daysLeft / 7).toString() + " weeks"
            }
            daysLeft > 0 -> {
                "$daysLeft days"
            }
            else -> "no deadline"
        }
    }

    private fun deadlineDate(): LocalDate = dateCreated.plusDays(deadline.toLong())
    private fun daysFromStart(): Int = Period.between(dateCreated, LocalDate.now()).days
    private fun daysUntilDeadline(): Int = Period.between(LocalDate.now(), deadlineDate()).days

    fun getTargetStatus(): String {
        if(deadline == 0) {
            return "No deadline set"
        }

        val predictedDaysToComplete = Period.between(dateCreated, predictedEndDate()).days
        val fraction = predictedDaysToComplete.toDouble() / deadline.toDouble()
        return when {
            fraction < 0.7 -> "Well ahead"
            fraction < 0.95 -> "Ahead"
            fraction < 1.06 -> "On target"
            fraction < 1.3 -> "Behind"
            else -> "Well behind"
        }
    }

    fun predictedEndDate(): LocalDate {
        val entryCount: Double = entryList.size.toDouble()
        val entriesPerDay = entryCount / Period.between(dateCreated, LocalDate.now()).days.toDouble()

        var repRate = entriesPerDay

        if(daysFromStart() > 7) {
            var lastWeekEntryCount = 0.0
            entryList.forEach {
                if (Period.between(it.date, LocalDate.now()).days <= 7)
                    lastWeekEntryCount++
            }
            repRate =
                (1.0 - PREDICTION_WEIGHT_FRACTION) * repRate + PREDICTION_WEIGHT_FRACTION * (lastWeekEntryCount / 7.0)
        }

        val repsRemaining = target - entryCount
        val predictedDays = ceil(repsRemaining / repRate).toLong()

        return LocalDate.now().plusDays(predictedDays)
    }

}
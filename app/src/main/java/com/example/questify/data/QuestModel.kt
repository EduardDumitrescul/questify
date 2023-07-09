@file:Suppress("SameReturnValue", "SameReturnValue")

package com.example.questify.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class QuestModel(
    val id: UUID = UUID.randomUUID(),
    var name: String = "Base Quest",
    var description: String = "",
    var target: Int = 0,
    var requirementTime: Int = 0,
//    val type: Type = Type.RepTarget,
    val dateCreated: Date = Date(),
    val hasEnded: Boolean = false,
    val dateEnded: Date = Date(),
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
    @Suppress("SameReturnValue", "SameReturnValue", "SameReturnValue")
    fun isCompleted(): Boolean {
        //TODO
        return false
    }

    private fun formatDate(date: Date): String {
        val pattern = "dd/mm/yy"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale("English"))
        return simpleDateFormat.format(date)
    }

    fun getDateCreatedFormatted(): String = formatDate(dateCreated)

    fun getDateEndedFormatted(): String = if (hasEnded) formatDate(dateEnded) else "still active"

    fun getDeadlineFormatted(): String = if(deadline > 0) "TODO" else "none"

    fun getRepRequirements(): String = if(requirementTime > 0) "$requirementTime min" else "none"
    fun getTimeRemaining(): String {
        // TODO
        return "todo"
    }

    fun getTargetStatus(): String {
    // TODO
        return "todo"
    }

}
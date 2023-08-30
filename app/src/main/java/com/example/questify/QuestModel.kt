package com.example.questify

import java.time.LocalDate
import java.util.UUID


data class QuestModel (
    val id: UUID = UUID.randomUUID(),
    var name: String = "Base Quest",
    var description: String = "",
    var startDate: LocalDate = LocalDate.now(),
    val deadlineDate: LocalDate? = null,
    var endDate: LocalDate? = null,
    val targetReps: Int = 20,
    var currentReps: Int = 0
) {

}
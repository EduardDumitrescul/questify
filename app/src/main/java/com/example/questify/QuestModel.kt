package com.example.questify

import java.time.LocalDate
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

}
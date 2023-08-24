package com.example.questify

import java.util.UUID


data class QuestModel(
    val id: UUID = UUID.randomUUID(),
    var name: String = "Base Quest",
    var description: String = ""
) {

}
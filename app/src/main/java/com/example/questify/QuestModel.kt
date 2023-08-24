package com.example.questify

import java.util.UUID


class QuestModel(
    val id: UUID = UUID.randomUUID(),
    val name: String = "Base Quest",
    val description: String = ""
) {

}
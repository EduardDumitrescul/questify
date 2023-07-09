package com.example.questify.data

import java.util.Date
import java.util.UUID


/** @param duration holds duration of this quest entry, in minutes, or 0 if the quest doesn't have requirements
 *
 */
data class EntryModel(
    val questId: UUID,
    val entryId: UUID = UUID.randomUUID(),
    val date: Date  = Date(),
    private val duration: Int = 0
) {
}
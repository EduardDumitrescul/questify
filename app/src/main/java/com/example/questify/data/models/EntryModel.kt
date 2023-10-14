package com.example.questify.data.models

import java.time.LocalDate
import java.util.UUID

data class EntryModel (
    var id: UUID = UUID.randomUUID(),
    var date: LocalDate = LocalDate.now()
) {
}
package com.example.questify.data

import androidx.room.TypeConverter
import java.time.LocalDate
import javax.annotation.Nullable


class LocalDateConverter {
    @TypeConverter
    fun fromLong(@Nullable epoch: Long?): LocalDate? {
        return if (epoch == null) null else LocalDate.ofEpochDay(epoch)
    }

    @TypeConverter
    fun localDateToEpoch(@Nullable localDate: LocalDate?): Long? {
        return localDate?.toEpochDay()
    }

}
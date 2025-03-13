package fr.eni.ecole.eni_shop.utils

import androidx.room.TypeConverter
import java.util.Date

class DateRoomConverter {
    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromLongToDate(millis: Long): Date {
        return Date(millis)
    }

}
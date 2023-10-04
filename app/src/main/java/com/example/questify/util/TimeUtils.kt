package com.example.questify.util


fun formatToWeeksAndDays(days: Int?) = formatToWeeksAndDays(days?.toLong())
fun formatToWeeksAndDays(days: Long?): String {
    if(days == null) {
        return "no limit set"
    }

    val weeks = days / 7
    val daysReduced = days % 7

    var output = ""
    when(weeks) {
        0L -> {}
        1L -> {
            output += "1 week"
        }
        else -> {
            output += "$weeks weeks"
        }
    }

    if(daysReduced > 0 && weeks > 0) {
        output += ','
    }

    when(daysReduced) {
        0L -> {}
        1L -> {
            output += "1 day"
        }
        else -> {
            output += "$daysReduced days"
        }
    }

    return output
}
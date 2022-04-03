package com.seniah.news.helper

import android.text.format.DateUtils
import android.text.format.DateUtils.getRelativeTimeSpanString
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String?.relativeTime(): String {
    if (this == null) return ""
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getDefault()

        getRelativeTimeSpanString(
            dateFormat.parse(this)!!.time,
            Calendar.getInstance().timeInMillis,
            DateUtils.MINUTE_IN_MILLIS
        ).toString()
    } catch (e: ParseException) {
        ""
    }
}

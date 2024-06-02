package com.devchew.kartadrogowa.models

import java.time.LocalTime

class TimeStruct(
    var h: Int = -1,
    var m: Int = -1,
    var s: Int = -1,
    var tenths: Int = -1 // 1/10 of a second
) {
    fun isSet(): Boolean {
        return h != -1 || m != -1 || s != -1 || tenths != -1
    }

    fun toSeconds(): Int {
        if (!isSet()) {
            return -1
        }
        return h * 3600 + m * 60 + s
    }

    fun toMs(): Long {
        if (!isSet()) {
            return -1
        }
        return ((h * 3600 + m * 60 + s) * 1000L) + tenths
    }

    fun fromMs(timestamp: Long) {
        if (timestamp < 0) {
            h = -1
            m = -1
            s = -1
            tenths = -1
            return
        }
        h = (timestamp / 3600000).toInt()
        m = ((timestamp % 3600000) / 60000).toInt()
        s = ((timestamp % 60000) / 1000).toInt()
        tenths = (timestamp % 1000).toInt()
    }

    fun fromSeconds(seconds: Int) {
        if (seconds < 0) {
            h = -1
            m = -1
            s = -1
            return
        }
        h = seconds / 3600
        m = (seconds % 3600) / 60
        s = seconds % 60
        tenths = seconds % 10
    }

    override fun toString(): String {
        if (!isSet()) {
            return "--:--"
        }
        if (h == 0) {
            return String.format("%02d:%02d", m, s)
        }
        return String.format("%02d:%02d:%02d", h, m, s)
    }
}

fun sumTime(times: List<TimeStruct>): TimeStruct {
    var sum = times[0].toSeconds()
    for (i in 1 until times.size) {
        sum += times[i].toSeconds()
    }
    return TimeStruct().apply { fromSeconds(sum) }
}

fun subtractTime(times: List<TimeStruct>): TimeStruct {
    var sum = times[0].toSeconds()
    for (i in 1 until times.size) {
        sum -= times[i].toSeconds()
    }
    return TimeStruct().apply { fromSeconds(sum) }
}

enum class TimeStructPart {
    HOURS,
    MINUTES,
    SECONDS,
    TENTHS
}

fun getCurrentTime(filter: List<TimeStructPart> = emptyList()): TimeStruct {
    val time = LocalTime.now()
    if (filter.isNotEmpty()) {
        return TimeStruct(
            if (filter.contains(TimeStructPart.HOURS)) time.hour else 0,
            if (filter.contains(TimeStructPart.MINUTES)) time.minute else 0,
            if (filter.contains(TimeStructPart.SECONDS)) time.second else 0,
            if (filter.contains(TimeStructPart.TENTHS)) time.nano / 1000000 else 0
        )
    }
    return TimeStruct(time.hour, time.minute, time.second, time.nano / 1000000)
}
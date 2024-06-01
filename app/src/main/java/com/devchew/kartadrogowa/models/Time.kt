package com.devchew.kartadrogowa.models

import java.time.LocalTime

class TimeStruct(
    var h: Int = -1,
    var m: Int = -1,
    var s: Int = -1,
    val tenths: Int = -1
) {
    fun isSet(): Boolean {
        return h != -1 || m != -1 || s != -1 || tenths != -1
    }

    fun toSeconds(): Int {
        if (!isSet()) {
            return 0
        }
        return h * 3600 + m * 60 + s
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

fun getCurrentTime(): TimeStruct {
    val time = LocalTime.now()
    return TimeStruct(time.hour, time.minute, time.second, time.nano / 1000000)
}
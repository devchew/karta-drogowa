package com.devchew.kartadrogowa.logic

import java.time.LocalTime

fun sumTime(times: List<TimeStruct>): TimeStruct {
    var h: Int = times[0].h
    var m: Int = times[0].m
    var s: Int = times[0].s
    var tenths: Int = times[0].tenths

    // loop through all times but skip the first one
    for (i in 1 until times.size) {
        val time = times[i]
        if (!time.isSet()) continue
        h += time.h
        m += time.m
        s += time.s
        tenths += time.tenths
    }
    return TimeStruct(h, m, s, tenths)
}

fun subtractTime(time1: TimeStruct, time2: TimeStruct): TimeStruct {
    return TimeStruct(
        time1.h - time2.h,
        time1.m - time2.m,
        time1.s - time2.s,
        time1.tenths - time2.tenths
    )
}

fun getCurrentTime(): TimeStruct {
    val currentTime = LocalTime.now()
    return TimeStruct(currentTime.hour, currentTime.minute, currentTime.second, currentTime.nano / 100000000)
}


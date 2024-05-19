package com.devchew.kartadrogowa.logic

import androidx.compose.runtime.mutableStateOf

class TimeStruct(
    val h: Int = -1,
    val m: Int = -1,
    val s: Int = -1,
    val tenths: Int = -1
) {
    fun isSet(): Boolean {
        return h != -1 || m != -1 || s != -1 || tenths != -1
    }
}

enum class OSPanelType {
    Start,
    Normal
}
class PanelLogic(
    val pkcType: OSPanelType,
    val pkc: Int,
    val name: String?,
    val duration: Float
) {

    var finishTime = mutableStateOf(TimeStruct())
    var finishResult = mutableStateOf(TimeStruct())
    var provisionalStartTime = mutableStateOf(TimeStruct())
    var realStartTime = mutableStateOf(TimeStruct())
    var idealTime = mutableStateOf(TimeStruct())
    var pkcTime = mutableStateOf(TimeStruct())
    var estimatedTime = mutableStateOf(TimeStruct())

    var finishCallback: (() -> Unit)? = null



    //finishTime
    fun finishTimeGetSuggested(): TimeStruct {
        if (finishTime.value.isSet()) {
            return finishTime.value
        }
        return getCurrentTime()
    }
    fun finishTimeChange(time: TimeStruct) {
        finishTime.value = time
    }

    //finishResult
    fun finishResultGetSuggested(): TimeStruct {
        if (finishResult.value.isSet()) {
            return finishResult.value
        }
        // get finishTime by subtracting realStartTime from finishTime
        return subtractTime(finishTime.value, realStartTime.value)
    }
    fun finishResultChange(time: TimeStruct) {
        finishResult.value = time
    }

    //provisionalStartTime
    fun provisionalStartTimeGetSuggested(): TimeStruct {
        if (provisionalStartTime.value.isSet()) {
            return provisionalStartTime.value
        }
        return getCurrentTime()
    }
    fun provisionalStartTimeChange(time: TimeStruct) {
        provisionalStartTime.value = time

        // if realStartTime is not set, set realStartTime to provisionalStartTime
        if (!realStartTime.value.isSet()) {
            realStartTimeChange(provisionalStartTime.value)
        }
    }

    /* realStartTime
    * if realStartTime is not set, return current time
    **/
    fun realStartTimeGetSuggested(): TimeStruct {
        if (realStartTime.value.isSet()) {
            return realStartTime.value
        }
        return getCurrentTime()
    }
    fun realStartTimeChange(time: TimeStruct) {
        realStartTime.value = time
        // if ideal time is set,
        // set pkcTimeSuggested to realStartTime + idealTime
        if (idealTime.value.isSet()) {
            estimatedTimeChange(sumTime(listOf(realStartTime.value, idealTime.value)))
        }

    }

    //idealTime
    fun idealTimeGetSuggested(): TimeStruct {
        if (idealTime.value.isSet()) {
            return idealTime.value
        }
        return TimeStruct(0,0,0,0)
    }
    fun idealTimeChange(time: TimeStruct) {
        idealTime.value = time
        // if realStartTime is set,
        // set pkcTimeSuggested to realStartTime + idealTime
        if (realStartTime.value.isSet()) {
            estimatedTimeChange(sumTime(listOf(realStartTime.value, idealTime.value)))
        }
    }

    //pkcTime
    fun pkcTimeGetSuggested(): TimeStruct {
        if (pkcTime.value.isSet()) {
            return pkcTime.value
        }
        // if estimatedTime is set, return estimatedTime
        if (estimatedTime.value.isSet()) {
            return estimatedTime.value
        }

        return getCurrentTime()
    }
    fun pkcTimeChange(time: TimeStruct) {
        pkcTime.value = time
        // stage should be finished, trigger finishCallback
        finishCallback?.invoke()
    }

    //estimatedTime
    fun estimatedTimeGetSuggested(): TimeStruct {
        if (estimatedTime.value.isSet()) {
            return estimatedTime.value
        }
        return TimeStruct(0,0,0,0)
    }
    fun estimatedTimeChange(time: TimeStruct) {
        estimatedTime.value = time
    }
}
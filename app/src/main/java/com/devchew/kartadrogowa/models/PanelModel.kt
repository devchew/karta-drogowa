package com.devchew.kartadrogowa.models

import androidx.compose.runtime.mutableStateOf
import com.devchew.kartadrogowa.database.Panel

class PanelModel(
    val panel: Panel
) {

    val id = panel.id
    val pkcType = mutableStateOf(panel.pkcType)
    val pkc = mutableStateOf(panel.pkc)
    val name = mutableStateOf(panel.name)
    val duration = mutableStateOf(panel.duration)
    var finishTime = mutableStateOf(TimeStruct().apply { fromSeconds(panel.finishTime) })
    var finishResult = mutableStateOf(TimeStruct().apply { fromSeconds(panel.finishResult) })
    var provisionalStartTime = mutableStateOf(TimeStruct().apply { fromSeconds(panel.provisionalStartTime) })
    var realStartTime = mutableStateOf(TimeStruct().apply { fromSeconds(panel.realStartTime) })
    var idealTime = mutableStateOf(TimeStruct().apply { fromSeconds(panel.idealTime) })
    var pkcTime = mutableStateOf(TimeStruct().apply { fromSeconds(panel.pkcTime) })
    var estimatedTime = mutableStateOf(TimeStruct().apply { fromSeconds(panel.estimatedTime) })

    var changeCallback: ((Panel) -> Unit)? = null


    private fun runChangeCallback() {
        changeCallback?.invoke(
            Panel(
            cardId = panel.cardId,
            pkcType = pkcType.value,
            pkc = pkc.value,
            name = name.value,
            duration = duration.value,
            finishTime = finishTime.value.toSeconds(),
            finishResult = finishResult.value.toSeconds(),
            provisionalStartTime = provisionalStartTime.value.toSeconds(),
            realStartTime = realStartTime.value.toSeconds(),
            idealTime = idealTime.value.toSeconds(),
            pkcTime = pkcTime.value.toSeconds(),
            estimatedTime = estimatedTime.value.toSeconds(),
            id = panel.id
        )
        )
    }


    //finishTime
    fun finishTimeGetSuggested(): TimeStruct {
        if (finishTime.value.isSet()) {
            return finishTime.value
        }
        return getCurrentTime()
    }
    fun finishTimeChange(time: TimeStruct) {
        finishTime.value = time
        runChangeCallback()
    }

    //finishResult
    fun finishResultGetSuggested(): TimeStruct {
        if (finishResult.value.isSet()) {
            return finishResult.value
        }
        // get finishTime by subtracting realStartTime from finishTime
        return subtractTime(listOf(finishTime.value, realStartTime.value))
    }
    fun finishResultChange(time: TimeStruct) {
        finishResult.value = time
        runChangeCallback()
    }

    //provisionalStartTime
    fun provisionalStartTimeGetSuggested(): TimeStruct {
        if (provisionalStartTime.value.isSet()) {
            return provisionalStartTime.value
        }
        return getCurrentTime(listOf(TimeStructPart.HOURS, TimeStructPart.MINUTES))
    }
    fun provisionalStartTimeChange(time: TimeStruct) {
        provisionalStartTime.value = time

        // if realStartTime is not set, set realStartTime to provisionalStartTime
        if (!realStartTime.value.isSet()) {
            realStartTimeChange(provisionalStartTime.value)
        }
        runChangeCallback()
    }

    /* realStartTime
    * if realStartTime is not set, return current time
    **/
    fun realStartTimeGetSuggested(): TimeStruct {
        if (realStartTime.value.isSet()) {
            return realStartTime.value
        }
        return getCurrentTime(listOf(TimeStructPart.HOURS, TimeStructPart.MINUTES))
    }
    fun realStartTimeChange(time: TimeStruct) {
        realStartTime.value = time
        // if ideal time is set,
        // set pkcTimeSuggested to realStartTime + idealTime
        if (idealTime.value.isSet()) {
            estimatedTimeChange(sumTime(listOf(realStartTime.value, idealTime.value)))
        }
        runChangeCallback()
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
        runChangeCallback()
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

        return getCurrentTime(listOf(TimeStructPart.HOURS, TimeStructPart.MINUTES))
    }
    fun pkcTimeChange(time: TimeStruct) {
        pkcTime.value = time
        runChangeCallback()
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
        runChangeCallback()
    }
}
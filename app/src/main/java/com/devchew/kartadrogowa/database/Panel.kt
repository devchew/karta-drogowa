package com.devchew.kartadrogowa.database

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class OSPanelType {
    Start,
    Normal
}

@Entity
data class Panel(
    val cardId: Int,
    val pkcType: OSPanelType = OSPanelType.Normal,
    val pkc: Int = 1,
    val name: String = "",
    val duration: Float = 0f,
    val finishTime: Int = -1,
    val finishResult: Int = -1,
    val provisionalStartTime: Int = -1,
    val realStartTime: Int = -1,
    val idealTime: Int = -1,
    val pkcTime: Int = -1,
    val estimatedTime: Int = -1,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)

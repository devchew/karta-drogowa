package com.devchew.kartadrogowa.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat

@Entity
data class Card(
    val name: String,
    val cardNumber: Int = 1,
    val date: String = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis()),
    val carNumber: Int = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)

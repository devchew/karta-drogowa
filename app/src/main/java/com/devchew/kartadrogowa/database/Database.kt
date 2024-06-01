package com.devchew.kartadrogowa.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Card::class, Panel::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun panelDao(): PanelDao
}
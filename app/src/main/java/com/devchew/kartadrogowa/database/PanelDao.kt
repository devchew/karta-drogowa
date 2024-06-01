package com.devchew.kartadrogowa.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PanelDao {
        @Query("SELECT * FROM Panel WHERE id = :id")
        suspend fun getById(id: Int): Panel
        @Upsert
        suspend fun upsert(panel: Panel): Long

        @Query("SELECT * FROM Panel WHERE cardId = :cardId ORDER BY id ASC")
        suspend fun getPanels(cardId: Int): List<Panel>
}
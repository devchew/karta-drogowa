package com.devchew.kartadrogowa.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDao {

    @Query("SELECT * FROM Card")
    suspend fun getAll(): List<Card>

    @Query("SELECT * FROM Card WHERE id = :id")
    suspend fun getById(id: Int): Card
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(card: Card): Long

    @Query("DELETE FROM Card WHERE id = :id")
    suspend fun delete(id: Int)
}
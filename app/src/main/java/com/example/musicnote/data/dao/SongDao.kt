package com.example.musicnote.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.musicnote.data.entity.Song

@Dao
interface SongDao {
    @Query("SELECT * FROM songs ORDER BY createdAt DESC")
    fun getAll(): LiveData<List<Song>>

    @Query("SELECT * FROM songs WHERE isFavorite = 1")
    fun getFavorites(): LiveData<List<Song>>

    @Query("SELECT * FROM songs WHERE id = :id")
    suspend fun getById(id: Int): Song?

    @Insert
    suspend fun insert(song: Song): Long

    @Update
    suspend fun update(song: Song)

    @Delete
    suspend fun delete(song: Song)
}
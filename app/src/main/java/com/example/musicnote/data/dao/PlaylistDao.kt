package com.example.musicnote.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.musicnote.data.entity.Playlist
import com.example.musicnote.data.entity.PlaylistSong

@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlists ORDER BY createdAt DESC")
    fun getAll(): LiveData<List<Playlist>>

    @Insert
    suspend fun insert(playlist: Playlist): Long

    @Update
    suspend fun update(playlist: Playlist)

    @Delete
    suspend fun delete(playlist: Playlist)

    @Insert
    suspend fun addSong(playlistSong: PlaylistSong)

    @Query("DELETE FROM playlist_songs WHERE playlistId = :plId AND songId = :sId")
    suspend fun removeSong(plId: Int, sId: Int)
}
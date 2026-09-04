package com.example.musicnote.data.repository

import androidx.lifecycle.LiveData
import com.example.musicnote.data.dao.PlaylistDao
import com.example.musicnote.data.entity.Playlist
import com.example.musicnote.data.entity.PlaylistSong

class PlaylistRepository(private val playlistDao: PlaylistDao) {

    val allPlaylists: LiveData<List<Playlist>> = playlistDao.getAll()

    suspend fun insert(playlist: Playlist): Long {
        return playlistDao.insert(playlist)
    }

    suspend fun update(playlist: Playlist) {
        playlistDao.update(playlist)
    }

    suspend fun delete(playlist: Playlist) {
        playlistDao.delete(playlist)
    }

    suspend fun addSong(playlistSong: PlaylistSong) {
        playlistDao.addSong(playlistSong)
    }

    suspend fun removeSong(plId: Int, sId: Int) {
        playlistDao.removeSong(plId, sId)
    }
}
package com.example.musicnote.data.repository

import androidx.lifecycle.LiveData
import com.example.musicnote.data.dao.SongDao
import com.example.musicnote.data.entity.Song

class SongRepository(private val songDao: SongDao) {

    val allSongs: LiveData<List<Song>> = songDao.getAll()
    val favoriteSongs: LiveData<List<Song>> = songDao.getFavorites()

    suspend fun insert(song: Song): Long {
        return songDao.insert(song)
    }

    suspend fun update(song: Song) {
        songDao.update(song)
    }

    suspend fun delete(song: Song) {
        songDao.delete(song)
    }

    suspend fun getById(id: Int): Song? {
        return songDao.getById(id)
    }
}
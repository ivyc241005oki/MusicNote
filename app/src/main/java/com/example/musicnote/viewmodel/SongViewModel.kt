package com.example.musicnote.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.musicnote.data.database.AppDatabase
import com.example.musicnote.data.entity.Song
import com.example.musicnote.data.repository.SongRepository
import kotlinx.coroutines.launch

class SongViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SongRepository
    val allSongs: LiveData<List<Song>>
    val favoriteSongs: LiveData<List<Song>>

    init {
        val songDao = AppDatabase.getDatabase(application).songDao()
        repository = SongRepository(songDao)
        allSongs = repository.allSongs
        favoriteSongs = repository.favoriteSongs
    }

    fun insert(song: Song) = viewModelScope.launch {
        repository.insert(song)
    }

    fun update(song: Song) = viewModelScope.launch {
        repository.update(song)
    }

    fun delete(song: Song) = viewModelScope.launch {
        repository.delete(song)
    }
}
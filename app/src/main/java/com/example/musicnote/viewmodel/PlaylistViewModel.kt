package com.example.musicnote.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.musicnote.data.database.AppDatabase
import com.example.musicnote.data.entity.Playlist
import com.example.musicnote.data.entity.PlaylistSong
import com.example.musicnote.data.repository.PlaylistRepository
import kotlinx.coroutines.launch

class PlaylistViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PlaylistRepository
    val allPlaylists: LiveData<List<Playlist>>

    init {
        val playlistDao = AppDatabase.getDatabase(application).playlistDao()
        repository = PlaylistRepository(playlistDao)
        allPlaylists = repository.allPlaylists
    }

    fun insert(playlist: Playlist) = viewModelScope.launch {
        repository.insert(playlist)
    }

    fun update(playlist: Playlist) = viewModelScope.launch {
        repository.update(playlist)
    }

    fun delete(playlist: Playlist) = viewModelScope.launch {
        repository.delete(playlist)
    }

    fun addSong(playlistSong: PlaylistSong) = viewModelScope.launch {
        repository.addSong(playlistSong)
    }

    fun removeSong(plId: Int, sId: Int) = viewModelScope.launch {
        repository.removeSong(plId, sId)
    }
}
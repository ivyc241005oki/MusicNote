package com.example.musicnote.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val artistName: String,
    val album: String = "",
    val memo: String = "",
    val rating: Int = 3,
    val isFavorite: Boolean = false,
    val createdAt: String = ""
)
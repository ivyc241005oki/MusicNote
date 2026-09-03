package com.example.musicnote.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.musicnote.data.dao.PlaylistDao
import com.example.musicnote.data.dao.SongDao
import com.example.musicnote.data.entity.*

@Database(
    entities = [Song::class, Playlist::class, Genre::class,
        PlaylistSong::class, SongGenre::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun playlistDao(): PlaylistDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "music_note_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
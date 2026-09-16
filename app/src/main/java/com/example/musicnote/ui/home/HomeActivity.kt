package com.example.musicnote.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicnote.R
import com.example.musicnote.databinding.ActivityHomeBinding
import com.example.musicnote.ui.playlist.PlaylistActivity
import com.example.musicnote.ui.song.SongAdapter
import com.example.musicnote.ui.song.SongCreateActivity
import com.example.musicnote.ui.song.SongDetailActivity
import com.example.musicnote.viewmodel.SongViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val songViewModel: SongViewModel by viewModels()
    private lateinit var favoriteAdapter: SongAdapter
    private lateinit var recentAdapter: SongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // お気に入りAdapter
        favoriteAdapter = SongAdapter { song ->
            val intent = Intent(this, SongDetailActivity::class.java)
            intent.putExtra("SONG_ID", song.id)
            startActivity(intent)
        }
        binding.rvFavorites.layoutManager = LinearLayoutManager(this)
        binding.rvFavorites.adapter = favoriteAdapter

        // 最近追加Adapter
        recentAdapter = SongAdapter { song ->
            val intent = Intent(this, SongDetailActivity::class.java)
            intent.putExtra("SONG_ID", song.id)
            startActivity(intent)
        }
        binding.rvRecentSongs.layoutManager = LinearLayoutManager(this)
        binding.rvRecentSongs.adapter = recentAdapter

        // データを観察
        songViewModel.favoriteSongs.observe(this) { songs ->
            favoriteAdapter.updateSongs(songs)
        }

        songViewModel.allSongs.observe(this) { songs ->
            recentAdapter.updateSongs(songs)
        }

        // FABボタン
        binding.fab.setOnClickListener {
            startActivity(Intent(this, SongCreateActivity::class.java))
        }

        // BottomNavigation
        binding.bottomNav.selectedItemId = R.id.nav_home
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_songs -> {
                    startActivity(Intent(this, SongCreateActivity::class.java))
                    true
                }
                R.id.nav_playlist -> {
                    startActivity(Intent(this, PlaylistActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
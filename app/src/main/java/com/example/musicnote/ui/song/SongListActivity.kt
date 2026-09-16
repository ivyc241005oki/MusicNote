package com.example.musicnote.ui.song

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicnote.databinding.ActivitySongListBinding
import com.example.musicnote.viewmodel.SongViewModel

class SongListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongListBinding
    private val songViewModel: SongViewModel by viewModels()
    private lateinit var songAdapter: SongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        songAdapter = SongAdapter { song ->
            val intent = Intent(this, SongDetailActivity::class.java)
            intent.putExtra("SONG_ID", song.id)
            startActivity(intent)
        }
        binding.rvSongs.layoutManager = LinearLayoutManager(this)
        binding.rvSongs.adapter = songAdapter

        songViewModel.allSongs.observe(this) { songs ->
            songAdapter.updateSongs(songs)
        }

        // 戻るボタン
        binding.btnBack.setOnClickListener {
            finish()
        }

        // FABボタン
        binding.fab.setOnClickListener {
            startActivity(Intent(this, SongCreateActivity::class.java))
        }

        // 検索
        binding.searchView.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = false
                override fun onQueryTextChange(newText: String?): Boolean {
                    val query = newText ?: ""
                    songViewModel.allSongs.observe(
                        this@SongListActivity
                    ) { songs ->
                        val filtered = songs.filter {
                            it.title.contains(query, ignoreCase = true) ||
                                    it.artistName.contains(query, ignoreCase = true)
                        }
                        songAdapter.updateSongs(filtered)
                    }
                    return true
                }
            }
        )
    }
}
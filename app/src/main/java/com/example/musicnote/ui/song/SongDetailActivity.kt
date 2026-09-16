package com.example.musicnote.ui.song

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.musicnote.data.entity.Song
import com.example.musicnote.databinding.ActivitySongDetailBinding
import com.example.musicnote.viewmodel.SongViewModel

class SongDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongDetailBinding
    private val songViewModel: SongViewModel by viewModels()
    private var song: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val songId = intent.getIntExtra("SONG_ID", -1)

        songViewModel.allSongs.observe(this) { songs ->
            song = songs.find { it.id == songId }
            song?.let { displaySong(it) }
        }

        // 戻るボタン
        binding.btnBack.setOnClickListener {
            finish()
        }

        // お気に入りボタン
        binding.btnFavorite.setOnClickListener {
            song?.let {
                val updated = it.copy(isFavorite = !it.isFavorite)
                songViewModel.update(updated)
            }
        }

        // 削除ボタン
        binding.btnDelete.setOnClickListener {
            song?.let {
                androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("削除確認")
                    .setMessage("「${it.title}」を削除しますか？")
                    .setPositiveButton("削除") { _, _ ->
                        songViewModel.delete(it)
                        finish()
                    }
                    .setNegativeButton("キャンセル", null)
                    .show()
            }
        }
    }

    private fun displaySong(song: Song) {
        binding.tvTitle.text = song.title
        binding.tvArtist.text = song.artistName
        binding.tvAlbum.text = song.album
        binding.tvMemo.text = song.memo
        binding.tvRating.text = "★".repeat(song.rating)
        binding.btnFavorite.text = if (song.isFavorite) "♥" else "♡"
    }
}
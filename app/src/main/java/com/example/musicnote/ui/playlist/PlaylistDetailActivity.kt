package com.example.musicnote.ui.playlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicnote.data.entity.PlaylistSong
import com.example.musicnote.databinding.ActivityPlaylistDetailBinding
import com.example.musicnote.viewmodel.PlaylistViewModel
import com.example.musicnote.viewmodel.SongViewModel

class PlaylistDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaylistDetailBinding
    private val playlistViewModel: PlaylistViewModel by viewModels()
    private val songViewModel: SongViewModel by viewModels()
    private lateinit var songAdapter: PlaylistSongAdapter
    private var playlistId: Int = -1
    private var playlistName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playlistId = intent.getIntExtra("PLAYLIST_ID", -1)
        playlistName = intent.getStringExtra("PLAYLIST_NAME") ?: ""

        binding.tvPlaylistName.text = playlistName

        songAdapter = PlaylistSongAdapter { song ->
            // 曲を削除
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("削除確認")
                .setMessage("「${song.title}」をプレイリストから削除しますか？")
                .setPositiveButton("削除") { _, _ ->
                    playlistViewModel.removeSong(playlistId, song.id)
                }
                .setNegativeButton("キャンセル", null)
                .show()
        }
        binding.rvPlaylistSongs.layoutManager = LinearLayoutManager(this)
        binding.rvPlaylistSongs.adapter = songAdapter

        // プレイリスト内の曲を表示
        songViewModel.allSongs.observe(this) { songs ->
            songAdapter.updateSongs(songs)
        }

        // 戻るボタン
        binding.btnBack.setOnClickListener {
            finish()
        }

        // 曲を追加ボタン
        binding.btnAddSong.setOnClickListener {
            showAddSongDialog()
        }
    }

    private fun showAddSongDialog() {
        songViewModel.allSongs.value?.let { songs ->
            val songTitles = songs.map { it.title }.toTypedArray()
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("曲を追加")
                .setItems(songTitles) { _, which ->
                    val song = songs[which]
                    val playlistSong = PlaylistSong(
                        playlistId = playlistId,
                        songId = song.id
                    )
                    playlistViewModel.addSong(playlistSong)
                }
                .show()
        }
    }
}
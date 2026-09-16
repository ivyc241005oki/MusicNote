package com.example.musicnote.ui.playlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicnote.databinding.ActivityPlaylistBinding
import com.example.musicnote.viewmodel.PlaylistViewModel

class PlaylistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaylistBinding
    private val playlistViewModel: PlaylistViewModel by viewModels()
    private lateinit var playlistAdapter: PlaylistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playlistAdapter = PlaylistAdapter { playlist ->
            // TODO: プレイリスト詳細画面へ遷移
        }
        binding.rvPlaylists.layoutManager = LinearLayoutManager(this)
        binding.rvPlaylists.adapter = playlistAdapter

        playlistViewModel.allPlaylists.observe(this) { playlists ->
            playlistAdapter.updatePlaylists(playlists)
        }

        // FABボタン
        binding.fab.setOnClickListener {
            showCreatePlaylistDialog()
        }
    }

    private fun showCreatePlaylistDialog() {
        val editText = android.widget.EditText(this)
        editText.hint = "プレイリスト名"
        editText.setTextColor(android.graphics.Color.WHITE)

        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("新規プレイリスト")
            .setView(editText)
            .setPositiveButton("作成") { _, _ ->
                val name = editText.text.toString()
                if (name.isNotEmpty()) {
                    playlistViewModel.insert(
                        com.example.musicnote.data.entity.Playlist(name = name)
                    )
                }
            }
            .setNegativeButton("キャンセル", null)
            .show()
    }
}
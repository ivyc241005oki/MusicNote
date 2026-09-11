package com.example.musicnote.ui.song

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.musicnote.data.entity.Song
import com.example.musicnote.databinding.ActivitySongCreateBinding
import com.example.musicnote.viewmodel.SongViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SongCreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongCreateBinding
    private val songViewModel: SongViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 戻るボタン
        binding.btnBack.setOnClickListener {
            finish()
        }

        // 保存ボタン
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val artist = binding.etArtist.text.toString()

            if (title.isEmpty() || artist.isEmpty()) {
                if (title.isEmpty()) binding.etTitle.error = "曲名を入力してください"
                if (artist.isEmpty()) binding.etArtist.error = "アーティスト名を入力してください"
                return@setOnClickListener
            }

            val song = Song(
                title = title,
                artistName = artist,
                album = binding.etAlbum.text.toString(),
                memo = binding.etMemo.text.toString(),
                rating = binding.ratingBar.rating.toInt(),
                createdAt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    .format(Date())
            )
            songViewModel.insert(song)
            finish()
        }
    }
}
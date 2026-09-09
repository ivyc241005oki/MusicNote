package com.example.musicnote.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.musicnote.databinding.ActivityHomeBinding
import com.example.musicnote.viewmodel.SongViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val songViewModel: SongViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // お気に入り曲を観察
        songViewModel.favoriteSongs.observe(this) { songs ->
            // TODO: RecyclerViewに表示
        }

        // 全曲を観察
        songViewModel.allSongs.observe(this) { songs ->
            // TODO: RecyclerViewに表示
        }
    }
}
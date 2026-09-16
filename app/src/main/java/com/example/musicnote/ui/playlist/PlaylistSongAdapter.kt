package com.example.musicnote.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicnote.data.entity.Song
import com.example.musicnote.databinding.ItemSongBinding

class PlaylistSongAdapter(
    private var songs: List<Song> = emptyList(),
    private val onDeleteClick: (Song) -> Unit
) : RecyclerView.Adapter<PlaylistSongAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.tvTitle.text = song.title
            binding.tvArtist.text = song.artistName
            binding.tvRating.text = "★".repeat(song.rating)
            binding.root.setOnLongClickListener {
                onDeleteClick(song)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSongBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(songs[position])
    }

    override fun getItemCount() = songs.size

    fun updateSongs(newSongs: List<Song>) {
        songs = newSongs
        notifyDataSetChanged()
    }
}
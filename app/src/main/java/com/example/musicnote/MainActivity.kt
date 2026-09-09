package com.example.musicnote

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.musicnote.ui.home.HomeActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
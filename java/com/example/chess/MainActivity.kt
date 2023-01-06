package com.example.chess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val createdByLogo : ImageView = findViewById(R.id.createdByLogo)
        val createdBy : TextView = findViewById(R.id.createdBy)
        val color = createdBy.currentTextColor
        if(color == resources.getColor(R.color.white)){
            createdByLogo.setImageDrawable(resources.getDrawable(R.drawable.logowhite))
        }
        if(color == resources.getColor(R.color.black)){
            createdByLogo.setImageDrawable(resources.getDrawable(R.drawable.logoblack))
        }
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, ScreenActivity::class.java)
            startActivity(intent)
        }, 2000)
    }
}
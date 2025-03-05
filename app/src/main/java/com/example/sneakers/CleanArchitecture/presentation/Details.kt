package com.example.sneakers.CleanArchitecture.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View.OnCreateContextMenuListener
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.sneakers.R

class Details: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)

        val nike_1 = findViewById<ImageView>(R.id.Nike_1)
        val nike_2 = findViewById<ImageView>(R.id.Nike_2)
        val nike_3 = findViewById<ImageView>(R.id.Nike_3)
        val nike_4 = findViewById<ImageView>(R.id.Nike_4)
        val nike_5 = findViewById<ImageView>(R.id.Nike_5)
        val sneakers = findViewById<ImageView>(R.id.imageView3)
        val back = findViewById<ImageView>(R.id.back)
        nike_1.setOnClickListener {
            sneakers.setImageResource(R.drawable.img_47)
        }
        nike_2.setOnClickListener {
            sneakers.setImageResource(R.drawable.img_51)
        }
        nike_3.setOnClickListener {
            sneakers.setImageResource(R.drawable.img_49)
        }
        nike_4.setOnClickListener {
            sneakers.setImageResource(R.drawable.img_37)
        }
        nike_5.setOnClickListener {
            sneakers.setImageResource(R.drawable.img_50)
        }

        back.setOnClickListener {
            finish()
        }
    }
}
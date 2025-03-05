package com.example.sneakers.CleanArchitecture.presentation

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.example.sneakers.R

class Splash : AppCompatActivity() {
    /**
     * gghghghgh
     * @param savedInstanceState eto
     * @return nothing
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.splash)
        object : CountDownTimer(1000, 1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                val intent = Intent(this@Splash, OnBoard::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}
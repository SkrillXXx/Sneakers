package com.example.sneakers.CleanArchitecture.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sneakers.R


/**
 * SuperClass
 * @author Kasta
 *
 */
class MainActivity : AppCompatActivity() {
    /**
     * gghghghgh
     * @param savedInstanceState eto
     * @return nothing
     */
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.butt_1)
        button.setOnClickListener(){
            val intent = Intent(this, Splash::class.java )
            startActivity(intent)
            finish()
        }
        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }
        val button_vosstanavlenie: TextView = findViewById(R.id.textView5)
        button_vosstanavlenie.setOnClickListener(){
            val intent = Intent(this, ForgotPassword::class.java )
            startActivity(intent)
            finish()
        }

        addContentView(
            crashButton, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

    }
}
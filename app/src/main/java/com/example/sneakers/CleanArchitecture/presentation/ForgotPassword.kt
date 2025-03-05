package com.example.sneakers.CleanArchitecture.presentation

import android.graphics.Color
import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import com.example.sneakers.R

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        val buttonShowDialog: Button = findViewById(R.id.butt_1)
        buttonShowDialog.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        // Применяем размытие фона перед показом диалога
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            blurBackground()
        }

        val dialog = MyDialogFragment()
        dialog.show(supportFragmentManager, "MyDialogFragment")
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun blurBackground() {
        // Устанавливаем прозрачный фон для активности
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Получаем корневой вид активности
        val decorView = window.decorView

        // Устанавливаем эффект размытия на весь фон
        decorView.setRenderEffect(RenderEffect.createBlurEffect(1f, 10f, Shader.TileMode.CLAMP))
    }
}
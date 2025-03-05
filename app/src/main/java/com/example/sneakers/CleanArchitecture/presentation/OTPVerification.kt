package com.example.sneakers.CleanArchitecture.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sneakers.R

class OTPVerification : AppCompatActivity() {

    private lateinit var countdownText: TextView
    private lateinit var restartButton: TextView
    private var countdownTime: Long = 60 * 1000 // 30 секунд в миллисекундах
    private lateinit var editTexts: List<EditText>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verification)

        // Инициализация EditText
        editTexts = listOf(
            findViewById(R.id.otp_1),
            findViewById(R.id.otp_2),
            findViewById(R.id.otp_3),
            findViewById(R.id.otp_4),
            findViewById(R.id.otp_5),
            findViewById(R.id.otp_6)
        )

        // Установка слушателей и фильтров для EditText
        editTexts.forEachIndexed { index, editText ->
            setSingleCharacterInputFilter(editText)
            editText.setOnKeyListener { _, keyCode, event ->
                handleKeyEvent(editText, if (index < editTexts.size - 1) editTexts[index + 1] else null, keyCode, event)
            }
        }

        // Инициализация таймера
        countdownText = findViewById(R.id.timer)
        restartButton = findViewById(R.id.zanovo)

        startCountdown(countdownTime)

        // Обработчик нажатия на кнопку перезапуска таймера
        restartButton.setOnClickListener {
            startCountdown(countdownTime)
        }
    }

    private fun handleKeyEvent(currentEditText: EditText, nextEditText: EditText?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.action == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DEL && currentEditText.text.isEmpty()) {
                nextEditText?.requestFocus()
            } else if (currentEditText.text.length == 1) {
                nextEditText?.requestFocus()
            }
        }
        return false
    }

    private fun setSingleCharacterInputFilter(editText: EditText) {
        editText.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            if (source.length > 1) "" else null
        })

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.length > 1) {
                    editText.setText(s.substring(0, 1))
                    editText.setSelection(1)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun startCountdown(timeInMillis: Long) {
        object : CountDownTimer(timeInMillis, 1000) {
            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                countdownText.text = String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60)
            }

            override fun onFinish() {
                countdownText.text = "Время вышло!"
            }
        }.start()
    }
}
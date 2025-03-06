package com.example.sneakers.CleanArchitecture.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
     * Автор: Я
     * Время на реализацию: 2 часа
     * @param savedInstanceState Состояние активности
     */
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val login: EditText = findViewById(R.id.editTextTextEmailAddress)
        val password: EditText = findViewById(R.id.editTextPassword)
        val button: Button = findViewById(R.id.butt_1)
        val showPasswordImageView: ImageView = findViewById(R.id.imageView4)

        // Переменная для отслеживания состояния видимости пароля
        var isPasswordVisible = false

        // Обработчик нажатия на ImageView
        showPasswordImageView.setOnClickListener {
            isPasswordVisible = !isPasswordVisible // Переключаем состояние

            if (isPasswordVisible) {
                // Показать пароль
                password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                showPasswordImageView.setImageResource(R.drawable.img_67) // Установите иконку "показать"
            } else {
                // Скрыть пароль
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                showPasswordImageView.setImageResource(R.drawable.img_55) // Установите иконку "скрыть"
            }
            // Установить курсор в конец текста
            password.setSelection(password.text.length)
        }

        button.setOnClickListener {
            // Валидация полей
            if (validateFields(login, password)) {
                val intent = Intent(this, Splash::class.java)
                startActivity(intent)
                finish()
            }
        }

        val button_vosstanavlenie: TextView = findViewById(R.id.textView5)
        button_vosstanavlenie.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
            finish()
        }

        val button_registor: TextView = findViewById(R.id.textView7)
        button_registor.setOnClickListener {
            val intent = Intent(this, Register_account::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateFields(login: EditText, password: EditText): Boolean {
        val email = login.text.toString().trim()
        val pass = password.text.toString().trim()

        return when {
            email.isEmpty() -> {
                login.error = "Email не может быть пустым"
                false
            }
            pass.isEmpty() -> {
                password.error = "Пароль не может быть пустым"
                false
            }
            else -> true
        }
    }
}
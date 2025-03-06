package com.example.sneakers.CleanArchitecture.presentation

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sneakers.R

class Register_account : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_account)

        val textView: TextView = findViewById(R.id.textView13)
        textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        val name: EditText = findViewById(R.id.editName)
        val email: EditText = findViewById(R.id.editTextTextEmailAddress)
        val password: EditText = findViewById(R.id.editTextPassword)
        val button: Button = findViewById(R.id.butt_1)

        button.setOnClickListener {

            // Валидация полей
            if (validateFields(name, email, password)) {
                // Здесь можно добавить логику для регистрации пользователя
                // Например, отправка данных на сервер или сохранение в локальной базе данных
                // Для примера просто выведем сообщение
                Toast.makeText(this, "Регистрация успешна!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Splash::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateFields(name: EditText, email: EditText, password: EditText): Boolean {
        val userName = name.text.toString().trim()
        val userEmail = email.text.toString().trim()
        val userPassword = password.text.toString().trim()

        return when {
            userName.isEmpty() -> {
                name.error = "Имя не может быть пустым"
                false
            }
            userEmail.isEmpty() -> {
                email.error = "Email не может быть пустым"
                false
            }
            !isValidEmail(userEmail) -> {
                showErrorDialog("Некорректный email. Формат: name@domenname.ru")
                false
            }
            userPassword.isEmpty() -> {
                password.error = "Пароль не может быть пустым"
                false
            }
            else -> true
        }
    }

    private fun isValidEmail(email: String): Boolean {
        // Регулярное выражение для проверки email
        val emailPattern = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$"
        return email.matches(Regex(emailPattern))
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Ошибка")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
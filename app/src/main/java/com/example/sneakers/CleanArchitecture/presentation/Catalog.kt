package com.example.sneakers.CleanArchitecture.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sneakers.CleanArchitecture.data.Sneakers
import com.example.sneakers.CleanArchitecture.domain.CardAdapter
import com.example.sneakers.R


class Catalog : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter
    private val favoriteItems = mutableListOf<Sneakers>() // Список избранных карточек

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog)


        // Получаем название каталога из Intent
        val catalogName = intent.getStringExtra("CATALOG_NAME")
        val textView: TextView = findViewById(R.id.Catalo)
        val Vse: TextView = findViewById(R.id.Vse1)
        val Outdoor: TextView = findViewById(R.id.Outdoor2)
        val Tennis: TextView = findViewById(R.id.Tennis1)
        val Running: TextView = findViewById(R.id.Running1)

        // Устанавливаем обработчики нажатий для кнопок
        Vse.setOnClickListener { textView.text = Vse.text }
        Outdoor.setOnClickListener { textView.text = Outdoor.text }
        Tennis.setOnClickListener { textView.text = Tennis.text }
        Running.setOnClickListener { textView.text = Running.text }

        // Устанавливаем текст для TextView
        textView.text = catalogName ?: "Каталог"

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.Recycler2)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        // Добавление отступов для элементов RecyclerView
        val itemOffsetDecoration = CardAdapter.ItemOffsetDecoration(resources.getDimensionPixelSize(R.dimen.item_offset))
        recyclerView.addItemDecoration(itemOffsetDecoration)

        // Создание списка карточек
        val cardList = listOf(
            Sneakers("3","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("4","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("5","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("6","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("7","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("8","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("9","Best Seller", "Nike Air Max", "₽752.00")
        )

        // Инициализация адаптера
        adapter = CardAdapter(cardList.toMutableList()) { card ->
            navigateToProductDetail(card)
        }
        recyclerView.adapter = adapter

        // Обработка нажатия на кнопку "Назад"
        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Метод для перехода к деталям продукта
    private fun navigateToProductDetail(card: Sneakers) {
        val intent = Intent(this, Details::class.java).apply {
            putExtra("PRODUCT_TITLE", card.seller)
            putExtra("PRODUCT_DESCRIPTION", card.name)
            putExtra("PRODUCT_PRICE", card.price)
        }
        startActivity(intent)
    }
}
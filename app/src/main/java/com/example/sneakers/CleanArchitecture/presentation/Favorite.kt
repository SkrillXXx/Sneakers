@file:Suppress("KotlinConstantConditions")

package com.example.sneakers.CleanArchitecture.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sneakers.CleanArchitecture.data.Sneakers
import com.example.sneakers.CleanArchitecture.domain.CardAdapter
import com.example.sneakers.R
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("UNUSED_EXPRESSION", "UNREACHABLE_CODE", "KotlinConstantConditions")
class Favorite : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cardAdapter: CardAdapter
    private lateinit var favoriteCards: MutableList<Sneakers>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite)

        recyclerView = findViewById(R.id.RecyclerFavorite)

        // Установите GridLayoutManager с 2 столбцами
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val itemOffsetDecoration = CardAdapter.ItemOffsetDecoration(resources.getDimensionPixelSize(R.dimen.item_offset))
        recyclerView.addItemDecoration(itemOffsetDecoration)

        // Получаем избранные карточки
        favoriteCards = getFavoriteCards(this)

        // Создаем адаптер и устанавливаем его в RecyclerView
        cardAdapter = CardAdapter(favoriteCards) { card ->
            // Обработка нажатия на карточку
            navigateToProductDetail(card)
        }
        recyclerView.adapter = cardAdapter

        // Настройка нижней навигации
        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Установка слушателя для обработки нажатий
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                    finish() // Закрываем текущую активность, если это необходимо
                    true
                }
                R.id.bottom_like -> {
                    // Обработка перехода на экран "Favorites"
                    true
                }
                R.id.bottom_lampa -> {
                    // Обработка перехода на экран "Lampa"
                    true
                }
                R.id.bottom_profile -> {
                    // Обработка перехода на экран "Profile"
                    true
                }
                else -> false
            }
        }

        // Установка выбранного элемента
        navigation.selectedItemId = R.id.bottom_like // Установите нужный ID
    }

    private fun getFavoriteCards(context: Context): MutableList<Sneakers> {
        val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val favoriteCards = mutableListOf<Sneakers>()

        val allProducts = listOf(
            Sneakers("1", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("2", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("3", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("4", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("5", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("6", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("7", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("8", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("9", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("10", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("11", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("12", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("13", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("14", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("15", "Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("16", "Best Seller", "Nike Air Max", "₽752.00"),

        )

        // Загружаем все карточки, которые были добавлены в избранное
        for (product in allProducts) {
            val isFavorite = sharedPreferences.getBoolean(product.id, false)
            if (isFavorite) {
                favoriteCards.add(product)
            }
        }
        return favoriteCards
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


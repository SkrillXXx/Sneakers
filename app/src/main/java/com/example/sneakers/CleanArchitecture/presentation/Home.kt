package com.example.sneakers.CleanArchitecture.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.example.sneakers.CleanArchitecture.data.Sneakers
import com.example.sneakers.CleanArchitecture.domain.CardAdapter
import com.example.sneakers.R
import com.google.android.material.bottomnavigation.BottomNavigationView



class Home : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter
    private val favoriteItems = mutableListOf<Sneakers>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val popular = findViewById<TextView>(R.id.popularVse)
        popular.setOnClickListener {
            val intent = Intent(this, PopularActivity::class.java)
            startActivity(intent)
        }
        // Инициализация Side-menu
        val openMenuButton: ImageView = findViewById(R.id.side_menu)
        openMenuButton.setOnClickListener {
            showMenu()
        }



        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.Recycler)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Создание списка карточек
        favoriteItems.addAll(listOf(
            Sneakers("1","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("2","Best Seller", "Nike Air Max", "₽752.00")
        ))

        // Инициализация адаптера с обработчиком нажатий
        adapter = CardAdapter(favoriteItems) { card -> navigateToProductDetail(card) }
        recyclerView.adapter = adapter

        // Переход на Catalog Категории
        val Button1: TextView = findViewById(R.id.Vse)
        val Button2: TextView = findViewById(R.id.Outdoor)
        val Button3: TextView = findViewById(R.id.Tennis)
        val Button4: TextView = findViewById(R.id.Running)

        Button1.setOnClickListener {
            navigateToSecondActivity("Все")
        }

        Button2.setOnClickListener {
            navigateToSecondActivity("Outdoor")
        }

        Button3.setOnClickListener {
            navigateToSecondActivity("Tennis")
        }

        Button4.setOnClickListener {
            navigateToSecondActivity("Running")
        }

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    // Обработка перехода на экран "Home"
                    true
                }
                R.id.bottom_like -> {
                    val intent = Intent(this, Favorite::class.java )
                    startActivity(intent)
                    true
                }
                R.id.bottom_lampa -> {
                    // Обработка перехода на экран "Profile"
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
        navigation.selectedItemId = R.id.bottom_home_selected // Установите нужный ID
    }
    private fun showMenu() {
        val menuBottomSheet = MenuBottomDialogFragment()
        menuBottomSheet.show(supportFragmentManager, menuBottomSheet.tag)
    }

    private fun navigateToSecondActivity(catalogName: String) {
        val intent = Intent(this, Catalog::class.java)
        intent.putExtra("CATALOG_NAME", catalogName)
        startActivity(intent)
    }

    private fun navigateToProductDetail(card: Sneakers) {
        val intent = Intent(this, Details::class.java).apply {
            putExtra("SELLER", card.seller)
            putExtra("NAME", card.name)
            putExtra("PRICE", card.price)
        }
        startActivity(intent)
    }

}



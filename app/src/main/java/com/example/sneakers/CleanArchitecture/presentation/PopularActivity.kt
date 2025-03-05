package com.example.sneakers.CleanArchitecture.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sneakers.CleanArchitecture.data.Sneakers
import com.example.sneakers.CleanArchitecture.domain.CardAdapter
import com.example.sneakers.CleanArchitecture.presentation.Home
import com.example.sneakers.R


class PopularActivity: AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter
    private val favoriteItems = mutableListOf<Sneakers>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popular)
        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener{
            val intent = Intent(this, Home::class.java )
            startActivity(intent)
            finish()
        }

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.Recycler1)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

      val itemOffsetDecoration = CardAdapter.ItemOffsetDecoration(resources.getDimensionPixelSize(R.dimen.item_offset))
      recyclerView.addItemDecoration(itemOffsetDecoration)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        // Создание списка карточек
        val cardList = listOf(
            Sneakers("10","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("11","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("12","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("13","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("14","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("15","Best Seller", "Nike Air Max", "₽752.00"),
            Sneakers("16","Best Seller", "Nike Air Max", "₽752.00"),
        )

        // Инициализация адаптера
        adapter = CardAdapter(cardList.toMutableList()) { card ->
            navigateToProductDetail(card)
        }
        recyclerView.adapter = adapter
    }
    private fun navigateToProductDetail(card: Sneakers) {
        val intent = Intent(this, Details::class.java).apply {
            putExtra("PRODUCT_TITLE", card.seller)
            putExtra("PRODUCT_DESCRIPTION", card.name)
            putExtra("PRODUCT_PRICE", card.price)
        }
        startActivity(intent)
    }

}
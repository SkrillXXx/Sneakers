package com.example.sneakers.CleanArchitecture.domain

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sneakers.CleanArchitecture.data.Sneakers
import com.example.sneakers.R


// Адаптер для отображения карточек в RecyclerView
class CardAdapter(
    private val cardList: MutableList<Sneakers>, // Список карточек
    private val onCardClick: (Sneakers) -> Unit // Обработчик нажатий на карточку
) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val seller: TextView = itemView.findViewById(R.id.Seller)
        val name: TextView = itemView.findViewById(R.id.Name)
        val price: TextView = itemView.findViewById(R.id.price)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favourite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cardList[position]
        holder.seller.text = card.seller
        holder.price.text = card.price
        holder.name.text = card.name

        // Загружаем состояние избранного
        val sharedPreferences = holder.itemView.context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        card.isFavorite = sharedPreferences.getBoolean(card.id, false) // Используем id как ключ
        holder.favoriteIcon.setImageResource(if (card.isFavorite) R.drawable.img_29 else R.drawable.img_27)

        // Устанавливаем обработчик нажатий на элемент карточки
        holder.itemView.setOnClickListener { onCardClick(card) }

        // Обработчик нажатия на иконку избранного
        holder.favoriteIcon.setOnClickListener {
            card.isFavorite = !card.isFavorite // Переключаем статус избранного

            // Сохраняем состояние в SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putBoolean(card.id, card.isFavorite) // Используем id как ключ
            editor.apply()

            // Обновляем иконку
            holder.favoriteIcon.setImageResource(if (card.isFavorite) R.drawable.img_29 else R.drawable.img_27)

            // Обновляем только этот элемент
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = cardList.size

    class ItemOffsetDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.set(offset, offset, offset, offset) // Устанавливаем отступы
        }
    }
}

package com.example.sneakers.CleanArchitecture.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sneakers.CleanArchitecture.data.Slider
import com.example.sneakers.R

class Slider_Adapter(private val Slider: List<Slider>): RecyclerView.Adapter<Slider_Adapter.SliderViewHolder>(){

    inner class SliderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val Text_screen = view.findViewById<TextView>(R.id.Text_screen)
        private val Description = view.findViewById<TextView>(R.id.Description)
        private val Image_screen = view.findViewById<ImageView>(R.id.Image_Screen)

        fun bind(Slider: Slider){
            Text_screen.text = Slider.Text_screen
            Description.text = Slider.Description
            Image_screen.setImageResource(Slider.Image_screen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_screen, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return Slider.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(Slider[position])
    }
}
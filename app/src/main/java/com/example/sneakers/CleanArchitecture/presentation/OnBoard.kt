package com.example.sneakers.CleanArchitecture.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.sneakers.CleanArchitecture.data.Slider
import com.example.sneakers.CleanArchitecture.domain.Slider_Adapter
import com.example.sneakers.CleanArchitecture.presentation.Home
import com.example.sneakers.R

class OnBoard : AppCompatActivity() {

    private val Slider_Adapter = Slider_Adapter(
        listOf(
            Slider(
                "",
                "",
                R.drawable.img
            ),
            Slider(
                "Начнем \n путешествие",
                "Умная, великолепная и модная \n коллекция Изучите сейчас",
                R.drawable.img_2
            ),
            Slider(
                "У Вас Есть Сила, \n Чтобы",
                "В вашей комнате много красивых и \n привлекательных растений",
                R.drawable.img_3
            )
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboard)
        val IntroSliderViewPager = findViewById<ViewPager2>(R.id.IntroSliderViewPager)
        IntroSliderViewPager.adapter = Slider_Adapter
        setupIndicators()
        setCurrentindicator(0)
        IntroSliderViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentindicator(position)
            }
        }
        )
        val buttonNext = findViewById<Button>(R.id.button3)
        val TextPrivet = findViewById<TextView>(R.id.textView8)
        buttonNext.setOnClickListener{
            buttonNext.text = "Далее"
            if(IntroSliderViewPager.currentItem + 1 < Slider_Adapter.itemCount){
                IntroSliderViewPager.currentItem += 1
                TextPrivet.text = ""
            }
            else{
                Intent(applicationContext, Home :: class.java).also{
                    startActivity(it)
                }
            }

        }
    }
    private fun setupIndicators(){
        val indacators = arrayOfNulls<ImageView>(Slider_Adapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indacators.indices) {
            indacators[i] = ImageView(applicationContext)
            indacators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable
                        (applicationContext,
                        R.drawable.indicator_inactive
                       )
               )
                this?.layoutParams = layoutParams
            }
            val IndicatorsContainer = findViewById<LinearLayout>(R.id.IndicatorsContainer)
            IndicatorsContainer.addView(indacators[i])
        }
    }
    private  fun setCurrentindicator(index: Int){
        val IndicatorsContainer = findViewById<LinearLayout>(R.id.IndicatorsContainer)
        val childCount = IndicatorsContainer.childCount
        for (i in 0 until childCount){
            val ImageView = IndicatorsContainer[i] as ImageView
            if (i == index){
                ImageView.setImageDrawable(
                    ContextCompat.getDrawable
                        (applicationContext,
                         R.drawable.indicator_active

                    )
                )
            } else{
                ImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )

            }
        }
    }
}
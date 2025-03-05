package com.example.sneakers.CleanArchitecture.presentation

import android.content.Intent
import android.graphics.Color
import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.sneakers.R


class MyDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Возвращаем макет диалога с CardView
        return inflater.inflate(R.layout.dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Находим корневой элемент и устанавливаем обработчик нажатия
        val rootLayout: LinearLayout = view.findViewById(R.id.dialog_window)
        rootLayout.setOnClickListener {
            // Запускаем новую активность
            val intent = Intent(requireContext(), OTPVerification::class.java) // Замените NewActivity на вашу целевую активность
            startActivity(intent)
            dismiss() // Закрываем диалог
        }
    }
}
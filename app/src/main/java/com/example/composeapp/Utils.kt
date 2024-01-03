package com.example.composeapp

import androidx.compose.ui.graphics.Color

object Utils {
    object Constant{
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    object CommonMethods{
        fun getColorByStatus(status: String?) =
            when(status?.lowercase()){
                "Alive".lowercase() -> Color.Green
                "Unknown".lowercase() -> Color.LightGray
                else -> Color.Red
            }
    }
}
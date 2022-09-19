package com.example.canvas.data

import androidx.annotation.DrawableRes
import com.example.canvas.R

enum class SHAPE(
    @DrawableRes
    val value: Int
) {
    TRIANGLE(R.drawable.triangle),
    SQUARE(R.drawable.square),
    CIRCLE(R.drawable.circle);

    companion object {
        private val map = values().associateBy(SHAPE::value)
        fun from(shape: Int) = map[shape] ?: TRIANGLE
    }
}
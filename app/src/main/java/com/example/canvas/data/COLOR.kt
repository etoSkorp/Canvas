package com.example.canvas.data

import androidx.annotation.ColorRes
import com.example.canvas.R

enum class COLOR(
    @ColorRes
    val value: Int
) {
    BLACK(R.color.black_100),
    RED(R.color.red_900),
    YELLOW(R.color.yellow_900),
    GREEN(R.color.green_500),
    BLUE(R.color.blue_900);

    companion object {
        private val map = values().associateBy(COLOR::value)
        fun from(color: Int) = map[color] ?: BLACK
    }
}
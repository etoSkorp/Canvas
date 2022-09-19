package com.example.canvas.data

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.canvas.base.Item

sealed class ToolItem : Item {
    data class ColorModel(@ColorRes val color: Int) : ToolItem()
    data class SizeModel(val size: Int) : ToolItem()
    data class ToolModel(
        val type: TOOLS,
        val isSelected: Boolean = false,
        val selectedSize: SIZE = SIZE.MEDIUM,
        val selectedColor: COLOR = COLOR.BLACK,
        val selectedShape: SHAPE = SHAPE.TRIANGLE
    ) : ToolItem()
    data class ShapeModel(val shape: Int) : ToolItem()
}
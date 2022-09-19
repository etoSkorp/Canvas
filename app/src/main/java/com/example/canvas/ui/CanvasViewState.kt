package com.example.canvas.ui

import com.example.canvas.data.COLOR
import com.example.canvas.data.SHAPE
import com.example.canvas.data.SIZE
import com.example.canvas.data.TOOLS

data class CanvasViewState(
    val tools: TOOLS,
    val size: SIZE,
    val color: COLOR,
    val shape: SHAPE
)






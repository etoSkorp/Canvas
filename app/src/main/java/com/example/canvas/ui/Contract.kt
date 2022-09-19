package com.example.canvas.ui

import com.example.canvas.base.Event
import com.example.canvas.data.COLOR
import com.example.canvas.data.SHAPE
import com.example.canvas.data.TOOLS
import com.example.canvas.data.ToolItem

data class ViewState(
    val canvasViewState: CanvasViewState,
    val toolsList: List<ToolItem.ToolModel>,
    val colorList: List<ToolItem.ColorModel>,
    val sizeList: List<ToolItem.SizeModel>,
    val shapeList: List<ToolItem.ShapeModel>,
    val isToolsVisible: Boolean,
    val isPaletteVisible: Boolean,
    val isBrushSizeChangerVisible: Boolean,
    val isShapeVisible: Boolean

)

sealed class UIEvent : Event {
    data class OnToolsClicked(val index: Int) : UIEvent()
    data class OnPaletteClicked(val index: Int) : UIEvent()
    data class OnSizeClicked(val index: Int) : UIEvent()
    data class OnShapeClicked(val index: Int) : UIEvent()
    object OnDrawViewClicked : UIEvent()
    object OnToolbarClicked : UIEvent()
}

sealed class DataEvent : Event {
    data class OnSetDefaultTools(val tool: TOOLS) : DataEvent()
}
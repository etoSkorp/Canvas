package com.example.canvas.ui

import com.example.canvas.base.BaseViewModel
import com.example.canvas.base.Event
import com.example.canvas.data.*

class CanvasViewModel : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState =
        ViewState(
            toolsList = enumValues<TOOLS>().map { ToolItem.ToolModel(it) },
            colorList = enumValues<COLOR>().map { ToolItem.ColorModel(it.value) },
            sizeList = enumValues<SIZE>().map { ToolItem.SizeModel(it.value) },
            shapeList = enumValues<SHAPE>().map { ToolItem.ShapeModel(it.value) },
            canvasViewState = CanvasViewState(
                color = COLOR.BLACK,
                size = SIZE.MEDIUM,
                tools = TOOLS.NORMAL,
                shape = SHAPE.TRIANGLE
            ),
            isToolsVisible = false,
            isPaletteVisible = false,
            isBrushSizeChangerVisible = false,
            isShapeVisible = false
        )

    init {
        processDataEvent(DataEvent.OnSetDefaultTools(tool = TOOLS.NORMAL))
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UIEvent.OnToolbarClicked -> {
                return previousState.copy(
                    isToolsVisible = !previousState.isToolsVisible,
                    isPaletteVisible = false,
                    isBrushSizeChangerVisible = false,
                    isShapeVisible = false
                )
            }

            is UIEvent.OnToolsClicked -> {
                when (event.index) {
                    TOOLS.PALETTE.ordinal -> {
                        return previousState.copy(
                            isPaletteVisible = !previousState.isPaletteVisible,
                            isBrushSizeChangerVisible = false,
                            isShapeVisible = false
                        )
                    }

                    TOOLS.SHAPE.ordinal -> {
                        return previousState.copy(
                            isShapeVisible = !previousState.isShapeVisible,
                            isPaletteVisible = false,
                            isBrushSizeChangerVisible = false
                        )
                    }

                    TOOLS.SIZE.ordinal -> {
                        return previousState.copy(
                            isBrushSizeChangerVisible = !previousState.isBrushSizeChangerVisible,
                            isPaletteVisible = false,
                            isShapeVisible = false
                        )
                    }

                    else -> {
                        val toolsList = previousState.toolsList.mapIndexed { index, toolModel ->
                            if (index == event.index) {
                                toolModel.copy(isSelected = true)
                            } else {
                                toolModel.copy(isSelected = false)
                            }
                        }
                        return previousState.copy(
                            toolsList = toolsList,
                            isPaletteVisible = false,
                            isBrushSizeChangerVisible = false,
                            isShapeVisible = false,
                            canvasViewState = previousState.canvasViewState.copy(tools = TOOLS.values()[event.index])
                        )
                    }
                }
            }

            is UIEvent.OnPaletteClicked -> {
                val selectedColor = COLOR.values()[event.index]
                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.PALETTE) {
                        it.copy(selectedColor = selectedColor)
                    } else {
                        it
                    }
                }
                return previousState.copy(
                    toolsList = toolsList,
                    isPaletteVisible = !previousState.isPaletteVisible,
                    canvasViewState = previousState.canvasViewState.copy(color = selectedColor)
                )
            }

            is UIEvent.OnShapeClicked -> {
                val selectedShape = SHAPE.values()[event.index]
                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.SHAPE) {
                        it.copy(selectedShape = selectedShape)
                    } else {
                        it
                    }
                }
                return previousState.copy(
                    toolsList = toolsList,
                    isShapeVisible = !previousState.isShapeVisible,
                    canvasViewState = previousState.canvasViewState.copy(shape = selectedShape)
                )
            }

            is UIEvent.OnSizeClicked -> {
                val selectedSize = SIZE.values()[event.index]
                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.SIZE) {
                        it.copy(selectedSize = selectedSize)
                    } else {
                        it
                    }
                }
                return previousState.copy(
                    toolsList = toolsList,
                    isBrushSizeChangerVisible = !previousState.isBrushSizeChangerVisible,
                    canvasViewState = previousState.canvasViewState.copy(size = selectedSize)
                )
            }

            is UIEvent.OnDrawViewClicked -> {
                return previousState.copy(
                    isBrushSizeChangerVisible = false,
                    isPaletteVisible = false,
                    isToolsVisible = false,
                    isShapeVisible = false
                )
            }

            is DataEvent.OnSetDefaultTools -> {
                val toolsList = previousState.toolsList.map { toolModel ->
                    if (toolModel.type == event.tool) {
                        toolModel.copy(isSelected = true)
                    } else {
                        toolModel.copy(isSelected = false)
                    }
                }
                return previousState.copy(toolsList = toolsList)
            }
            else -> return null
        }
    }
}
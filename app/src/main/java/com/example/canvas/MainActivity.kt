package com.example.canvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.isVisible
import com.example.canvas.ui.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PALETTE_VIEW = 0
        private const val TOOLS_VIEW = 1
        private const val SIZE_VIEW = 2
        private const val SHAPE_VIEW = 3
    }

    private val viewModel: CanvasViewModel by viewModel()

    private var toolsList: List<ToolsLayout> = listOf()

    private val paletteLayout: ToolsLayout by lazy { findViewById(R.id.paletteLayout) }
    private val toolsLayout: ToolsLayout by lazy { findViewById(R.id.toolsLayout) }
    private val brushSizeLayout: ToolsLayout by lazy { findViewById(R.id.brushSizeLayout) }
    private val shapeLayout: ToolsLayout by lazy { findViewById(R.id.shapeLayout) }
    private val ivToolbarBrush: ImageView by lazy { findViewById(R.id.ivToolbarBrush) }
    private val ivToolbarClear: ImageView by lazy { findViewById(R.id.ivToolbarClear) }
    private val drawView: DrawView by lazy { findViewById(R.id.drawView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolsList = listOf(paletteLayout, toolsLayout, brushSizeLayout, shapeLayout)
        viewModel.viewState.observe(this, ::render)

        ivToolbarBrush.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnToolbarClicked)
        }

        toolsLayout.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnToolsClicked(it))
        }

        paletteLayout.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnPaletteClicked(it))
        }

        brushSizeLayout.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnSizeClicked(it))
        }

        shapeLayout.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnShapeClicked(it))
        }

        ivToolbarClear.setOnClickListener {
            drawView.clear()
        }

        drawView.setOnClickField {
            viewModel.processUIEvent(UIEvent.OnDrawViewClicked)
        }

    }

    private fun render(viewState: ViewState) {
        with(toolsList[TOOLS_VIEW]) {
            render(viewState.toolsList)
            isVisible = viewState.isToolsVisible
        }

        with(toolsList[PALETTE_VIEW]) {
            render(viewState.colorList)
            isVisible = viewState.isPaletteVisible
        }

        with(toolsList[SIZE_VIEW]) {
            render(viewState.sizeList)
            isVisible = viewState.isBrushSizeChangerVisible
        }

        with(toolsList[SHAPE_VIEW]) {
            render(viewState.shapeList)
            isVisible = viewState.isShapeVisible
        }

        drawView.render(viewState.canvasViewState)
    }
}
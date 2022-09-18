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
    }

    private val viewModel: CanvasViewModel by viewModel()

    private var toolsList: List<ToolsLayout> = listOf()

    private val paletteLayout: ToolsLayout by lazy { findViewById(R.id.paletteLayout) }
    private val toolsLayout: ToolsLayout by lazy { findViewById(R.id.toolsLayout) }
    private val ivToolbarBrush: ImageView by lazy { findViewById(R.id.ivToolbarBrush) }
    private val drawView: DrawView by lazy { findViewById(R.id.drawView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolsList = listOf(paletteLayout, toolsLayout)
        viewModel.viewState.observe(this, ::render)

        ivToolbarBrush.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnToolbarClicked)
        }

        paletteLayout.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnPaletteClicked(it))
        }

        toolsLayout.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnToolsClicked(it))
        }
    }

    private fun render(viewState: ViewState) {
        with(toolsList[PALETTE_VIEW]) {
            render(viewState.colorList)
            isVisible = viewState.isPaletteVisible
        }

        with(toolsList[TOOLS_VIEW]) {
            render(viewState.toolsList)
            isVisible = viewState.isToolsVisible
        }

        drawView.render(viewState.canvasViewState)
    }
}
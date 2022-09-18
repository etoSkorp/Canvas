package com.example.canvas.ui

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canvas.R
import com.example.canvas.base.setAdapterAndCleanupOnDetachFromWindow
import com.example.canvas.base.setData
import com.example.canvas.data.ToolItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class ToolsLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :CardView(context, attrs, defStyleAttr) {

    private val toolsList: RecyclerView by lazy { findViewById(R.id.rvTools) }

    private var onClick: (Int) -> Unit = {}

    private val adapterDelegate = ListDelegationAdapter(
        colorAdapterDelegate {
            onClick(it)
        },
        sizeChangeAdapterDelegate {
            onClick(it)
        },
        toolsAdapterDelegate {
            onClick(it)
        }
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        toolsList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        toolsList.setAdapterAndCleanupOnDetachFromWindow(adapterDelegate)
    }

    fun render(list: List<ToolItem>) {
        adapterDelegate.setData(list)
    }

    fun setOnClickListener(onClick: (Int) -> Unit) {
        this.onClick = onClick
    }
}
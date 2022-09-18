package com.example.canvas.ui

import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.TextView
import com.example.canvas.R
import com.example.canvas.base.Item
import com.example.canvas.data.TOOLS
import com.example.canvas.data.ToolItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer

fun colorAdapterDelegate(
    onClick: (Int) -> Unit
): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<ToolItem.ColorModel, Item>(
        R.layout.item_palette
    ) {
        val color: ImageView = findViewById(R.id.color)
        itemView.setOnClickListener { onClick(adapterPosition) }
        bind { list ->
            color.setColorFilter(
                context.resources.getColor(item.color, null),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

fun sizeChangeAdapterDelegate(
    onSizeClick: (Int) -> Unit
): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<ToolItem.SizeModel, Item>(
        R.layout.item_size
    ) {
        val tvTextSize: TextView = findViewById(R.id.tvBrushSize)
        itemView.setOnClickListener { onSizeClick(adapterPosition) }
        bind { list ->
            tvTextSize.text = item.size.toString()
            itemView.setBackgroundResource(R.drawable.tool_size_item)
            when (item.size) {
                4 -> {
                    tvTextSize.textSize = 12.toFloat()
                }
                16 -> {
                    tvTextSize.textSize = 14.toFloat()
                }
                32 -> {
                    tvTextSize.textSize = 17.toFloat()
                }
            }
        }
    }

fun toolsAdapterDelegate(
    onToolsClick: (Int) -> Unit
): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<ToolItem.ToolModel, Item>(
        R.layout.item_tools
    ) {
        val ivTool: ImageView by lazy { findViewById(R.id.ivTool) }
//        val tvToolsText: TextView = findViewById(R.id.tvToolsText)
        bind { list ->
            ivTool.setImageResource(item.type.value)

            when (item.type) {
                TOOLS.PALETTE -> {
                    ivTool.setColorFilter(
                        context.resources.getColor(item.selectedColor.value, null),
                        PorterDuff.Mode.SRC_IN
                    )
                }

                else -> {
                    if (item.isSelected) {
                        ivTool.setBackgroundResource(R.drawable.bg_selected)
                    } else {
                        ivTool.setBackgroundResource(R.color.transparent)
                    }
                }
            }

            itemView.setOnClickListener {
                onToolsClick(adapterPosition)
            }
        }
    }
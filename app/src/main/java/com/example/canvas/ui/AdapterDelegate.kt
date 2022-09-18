package com.example.canvas.ui

import android.graphics.PorterDuff
import android.widget.ImageView
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

//fun sizeChangeAdapterDelegate(
//    onSizeClick: (Int) -> Unit
//): AdapterDelegate<List<Item>> =
//    adapterDelegateLayoutContainer<ToolItem.SizeModel, Item>(
//        R.layout.item_size
//    ) {
//        val tvToolsSize: TextView = findViewById(R.id.tvToolsSize)
//        itemView.setOnClickListener { onSizeClick(adapterPosition) }
//        bind { list ->
//            tvToolsSize.text = item.size.toString()
//        }
//    }

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

//            if (tvToolsText.isVisible) {
//                tvToolsText.isVisible = false
//            }

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

//                TOOLS.SIZE -> {
//                    tvToolsText.isVisible = true
//                    tvToolsText.text = item.selectedSize.value.toString()
//                }
            }

            itemView.setOnClickListener {
                onToolsClick(adapterPosition)
            }
        }
    }
package com.example.canvas.data

import androidx.annotation.DrawableRes
import com.example.canvas.R

enum class TOOLS(
    @DrawableRes
    val value: Int
) {
    NORMAL(R.drawable.ic_baseline_horizontal_rule_24),
    DASH(R.drawable.ic_baseline_more_horiz_24),
    SIZE(R.drawable.ic_baseline_scatter_plot_24),
    PALETTE(R.drawable.ic_baseline_brightness_1_24),
    SHAPE(R.drawable.triangle);
}
package com.example.canvas.koin

import com.example.canvas.ui.CanvasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CanvasViewModel()
    }
}
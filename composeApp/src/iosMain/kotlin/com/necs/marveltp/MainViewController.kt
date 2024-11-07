package com.necs.marveltp

import androidx.compose.ui.window.ComposeUIViewController
import com.necs.marveltp.ui.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }
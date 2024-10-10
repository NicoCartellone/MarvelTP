package com.necs.marveltp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.necs.marveltp.ui.navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigation()
    }
}
package com.necs.marveltp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.necs.marveltp.data.local.CharactersDBRepositoryImpl

class MainActivity : ComponentActivity() {
    private val characterDBRepository = CharactersDBRepositoryImpl(DatabaseDriverFactory(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EnableTransparentStatusBar()

            App(characterDBRepository)
        }
    }
}

@Composable
private fun EnableTransparentStatusBar(){
    val view = LocalView.current
    val darkMode = isSystemInDarkTheme()
    if(!view.isInEditMode){
        val window = (view.context as Activity).window
        window.statusBarColor = Color.Transparent.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkMode
    }
}
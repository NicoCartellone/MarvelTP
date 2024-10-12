package com.necs.marveltp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.necs.marveltp.ui.screens.characterDetail.CharacterDetailScreen
import com.necs.marveltp.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable("HomeScreen") {
            HomeScreen(onClick = { navController.navigate("CharacterDetail") })
        }
        composable("CharacterDetail") {
            CharacterDetailScreen(onClick = { navController.navigate("HomeScreen") })
        }
    }
}
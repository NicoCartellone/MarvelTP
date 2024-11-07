package com.necs.marveltp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.necs.marveltp.ui.screens.characterDetail.CharacterDetailScreen
import com.necs.marveltp.ui.screens.home.HomeScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable("HomeScreen") {
            HomeScreen(
                onCharacterClick = { character ->
                    navController.navigate("CharacterDetail/${character.id}")
                }
            )
        }
        composable(
            route = "CharacterDetail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backstackEntry ->
            val characterId = checkNotNull(backstackEntry.arguments?.getInt("characterId"))

            CharacterDetailScreen(
                vm = koinViewModel(parameters = { parametersOf(characterId) }),
                onBack = { navController.popBackStack() }
            )

        }
    }
}
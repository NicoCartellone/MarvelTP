package com.necs.marveltp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.necs.marveltp.data.network.CharactersService
import com.necs.marveltp.data.repository.RemoteCharactersRepository
import com.necs.marveltp.ui.screens.characterDetail.CharacterDetailScreen
import com.necs.marveltp.ui.screens.characterDetail.CharacterDetailViewModel
import com.necs.marveltp.ui.screens.home.HomeScreen
import com.necs.marveltp.ui.screens.home.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val charactersRepository = RemoteCharactersRepository()

    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable("HomeScreen") {
            HomeScreen(
                onCharacterClick = { character ->
                    navController.navigate("CharacterDetail/${character.id}")
                },
                vm = viewModel {
                    HomeViewModel(CharactersService(charactersRepository))
                }
            )
        }
        composable(
            route = "CharacterDetail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backstackEntry ->
            val characterId = checkNotNull(backstackEntry.arguments?.getInt("characterId"))
            val characterService = CharactersService(charactersRepository)
            CharacterDetailScreen(
                vm = viewModel { CharacterDetailViewModel(characterId, characterService) },
                onBack = { navController.popBackStack() }
            )

        }
    }
}
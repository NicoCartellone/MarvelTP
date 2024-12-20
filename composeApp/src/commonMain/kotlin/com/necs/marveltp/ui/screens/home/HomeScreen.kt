package com.necs.marveltp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.necs.marveltp.data.models.Character
import com.necs.marveltp.ui.components.CharacterList
import com.necs.marveltp.ui.components.CharacterSearchBar
import com.necs.marveltp.ui.screens.Screen
import marveltp.composeapp.generated.resources.Res
import marveltp.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    onCharacterClick: (Character) -> Unit,
    vm: HomeViewModel = koinViewModel(),
) {
    val uiState: HomeUIState by vm.uiState.collectAsState()

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        var searchQuery by remember { mutableStateOf(vm.searchQuery) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(Res.string.app_name)) },
                    scrollBehavior = scrollBehavior,
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                CharacterSearchBar(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    searchQuery = searchQuery,
                    onSearchQueryChange = { newQuery ->
                        searchQuery = newQuery
                        if (newQuery.isEmpty()) vm.fetchCharacters()
                        else
                            vm.fetchCharactersByName(newQuery)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                when (val charactersState = uiState.charactersUIState) {
                    is CharactersUIState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is CharactersUIState.Success -> {
                        CharacterList(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            onClick = onCharacterClick,
                            characters = charactersState.characters,
                        )
                    }

                    is CharactersUIState.Error -> {
                        Text(
                            charactersState.message,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}
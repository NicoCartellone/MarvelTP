package com.necs.marveltp.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necs.marveltp.data.local.CharactersDBRepository
import com.necs.marveltp.data.models.Character
import com.necs.marveltp.data.network.CharactersService
import kotlinx.coroutines.launch

class HomeViewModel(
    private val charactersService: CharactersService,
    private val charactersDBRepository: CharactersDBRepository,
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    var searchQuery by mutableStateOf("")

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            state = UiState(loading = true)
            try {
                charactersService.getCharacters().collect { characters ->
                    charactersDBRepository.insertCharacters(characters)
                    state = UiState(loading = false, characters = characters)
                }
            } catch (e: Exception) {
                val cachedCharacters = charactersDBRepository.getAllCharacters()
                state = if(cachedCharacters.isNotEmpty()){
                    UiState(loading = false, characters = cachedCharacters)
                } else {
                    UiState(loading = false, characters = emptyList(), error = "No hay personajes disponibles.")
                }
            }
        }
    }

    fun fetchCharactersByName(name: String) {
        searchQuery = name
        viewModelScope.launch {
            state = UiState(loading = true)
            try {
                charactersService.searchCharacterByName(name).collect { characters ->
                    state = if (characters.isEmpty()){
                        UiState(loading = false, characters = emptyList(), error = "No hay personajes disponibles.")
                    } else {
                        UiState(loading = false, characters = characters)
                    }
                }
            } catch (e: Exception) {
                state = UiState(loading = false, characters = emptyList(), error = "No hay personajes disponibles.")
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val characters: List<Character> = emptyList(),
        val error: String? = null
    )
}
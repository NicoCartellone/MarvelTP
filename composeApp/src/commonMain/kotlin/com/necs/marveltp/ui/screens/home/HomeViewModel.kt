package com.necs.marveltp.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necs.marveltp.data.models.Character
import com.necs.marveltp.data.network.CharactersService
import kotlinx.coroutines.launch

class HomeViewModel(
    private val charactersService: CharactersService,
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            state = UiState(loading = true)
            try {
                val characters = charactersService.getCharacters()
                println(characters)
                state = UiState(loading = false, characters = characters)
            } catch (e: Exception) {
                state = UiState(loading = false)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val characters: List<Character> = emptyList(),
    )
}
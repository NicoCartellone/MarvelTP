package com.necs.marveltp.ui.screens.characterDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necs.marveltp.data.models.Character
import com.necs.marveltp.data.network.CharactersService
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val id: Int,
    private val charactersService: CharactersService
): ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            try {
                charactersService.getCharacterById(id).collect {
                    state = UiState(loading = false, character = it)
                }
            }catch (e: Exception){
                state = UiState(loading = false)
            }
        }
    }

}

data class UiState(
    val loading: Boolean = false,
    val character:Character? = null
)
package com.necs.marveltp.ui.screens.home

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necs.marveltp.data.local.CharactersDBRepository
import com.necs.marveltp.data.models.Character
import com.necs.marveltp.data.network.CharactersService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Immutable
sealed interface CharactersUIState {
    data object Loading : CharactersUIState

    data class Success(
        val characters: List<Character>,
    ) : CharactersUIState

    data class Error(
        val message: String,
    ) : CharactersUIState
}

data class HomeUIState(
    val charactersUIState: CharactersUIState,
)

class HomeViewModel(
    private val charactersService: CharactersService,
    private val charactersDBRepository: CharactersDBRepository,
) : ViewModel() {
    private val charactersUIState = MutableStateFlow(CharactersUIState.Loading)

    private val _uiState = MutableStateFlow(
        HomeUIState(charactersUIState.value)
    )

    val uiState = _uiState.asStateFlow()

    var searchQuery by mutableStateOf("")

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(charactersUIState = CharactersUIState.Loading)
            try {
                charactersService.getCharacters().collect { characters ->
                    charactersDBRepository.insertCharacters(characters)
                    _uiState.value =
                        _uiState.value.copy(charactersUIState = CharactersUIState.Success(characters))
                }
            } catch (e: Exception) {
                val cachedCharacters = charactersDBRepository.getAllCharacters()
                _uiState.value = if (cachedCharacters.isNotEmpty()) {
                    _uiState.value.copy(
                        charactersUIState = CharactersUIState.Success(
                            cachedCharacters
                        )
                    )
                } else {
                    _uiState.value.copy(charactersUIState = CharactersUIState.Error("No available characters."))
                }
            }
        }
    }

    fun fetchCharactersByName(name: String) {
        searchQuery = name
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(charactersUIState = CharactersUIState.Loading)
            try {
                charactersService.searchCharacterByName(name).collect { characters ->
                    if (characters.isEmpty()) {
                        _uiState.value = _uiState.value.copy(
                            charactersUIState = CharactersUIState.Error("No available characters.")
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            charactersUIState = CharactersUIState.Success(characters)
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.value =
                    _uiState.value.copy(charactersUIState = CharactersUIState.Error("No available characters."))
            }
        }
    }
}
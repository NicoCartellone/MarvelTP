package com.necs.marveltp.data.network

import com.necs.marveltp.data.models.Character
import com.necs.marveltp.data.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersService(private val charactersRepository: CharactersRepository) {

    fun getCharacters(): Flow<List<Character>> = flow  {
        val characters = charactersRepository.getCharacters()
        emit(sort(characters))
    }

    fun getCharacterById(id: Int): Flow<Character> = flow {
        emit(charactersRepository.getCharacterById(id))
    }

    fun searchCharacterByName(name: String): Flow<List<Character>> = flow {
        val characters = charactersRepository.searchCharacterByName(name)
        emit(sort(characters))
    }

    private fun sort(characters: List<Character>): List<Character> {
        return characters.sortedWith(CharacterComparator())
    }

    private class CharacterComparator : Comparator<Character> {
        override fun compare(c1: Character, c2: Character): Int {
            if (c1.description.isEmpty() && c2.description.isEmpty()) {
                return c2.id.compareTo(c1.id)
            }
            if (c1.description.isNotEmpty() && c2.description.isNotEmpty()) {
                return c1.id.compareTo(c2.id)
            }
            if (c1.description.isNotEmpty() && c2.description.isEmpty()) {
                return -1
            }
            return 1
        }

    }
}
package com.necs.marveltp.data.network

import com.necs.marveltp.data.models.Character
import com.necs.marveltp.data.repository.CharactersRepository

class CharactersService(private val charactersRepository: CharactersRepository) {

    suspend fun getCharacters(): List<Character> {
        val characters = charactersRepository.getCharacters()
        return sort(characters)
    }

    suspend fun getCharacterById(id: Int): Character {
        return charactersRepository.getCharacterById(id)
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
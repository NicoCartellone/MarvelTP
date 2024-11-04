package com.necs.marveltp.data.repository

import com.necs.marveltp.data.models.Character

interface CharactersRepository {
    suspend fun getCharacters(): List<Character>

    suspend fun getCharacterById(id: Int): Character

    suspend fun searchCharacterByName(name: String): List<Character>
}
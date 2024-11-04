package com.necs.marveltp.data.local

import com.necs.marveltp.data.models.Character

interface CharactersDBRepository {
    suspend fun insertCharacters(characters: List<Character>): Unit
    suspend fun deleteAllCharacters(): Unit
    suspend fun deleteCharacter(id: Long): Unit
    suspend fun getAllCharacters(): List<Character>
    suspend fun getCharacter(id: Long): Character
}
package com.necs.marveltp.data.local

import com.necs.marvelapp.MarvelDatabase
import com.necs.marvelapp.MarvelDbQueries
import com.necs.marveltp.DatabaseDriverFactory
import com.necs.marveltp.data.models.Character

class CharactersDBRepositoryImpl(driverFactory: DatabaseDriverFactory) : CharactersDBRepository {
    private val charactersDB = MarvelDatabase(driverFactory.createDriver())
    private val query: MarvelDbQueries = charactersDB.marvelDbQueries

    override suspend fun getAllCharacters(): List<Character> {
        return query.getAllMarvelCharacters(::mapToCharacter).executeAsList()
    }

    override suspend fun getCharacter(id: Long): Character {
        return query.getMarvelCharacter(id, ::mapToCharacter).executeAsOne()
    }

    override suspend fun insertCharacters(characters: List<Character>) {
        charactersDB.transaction {
            for (character in characters) {
                query.insertMarvelCharacter(
                    character.id,
                    character.name,
                    character.description,
                    character.thumbnailUrl
                )
            }
        }
    }

    override suspend fun deleteAllCharacters() {
        query.deleteAllMarvelCharacters()
    }

    override suspend fun deleteCharacter(id: Long) {
        query.deleteMarvelCharacter(id)
    }

    private fun mapToCharacter(
        id: Long,
        name: String,
        description: String,
        thumbnailUrl: String
    ): Character {
        return Character(id, name, description, thumbnailUrl)
    }

}
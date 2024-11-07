package com.necs.marveltp.data.repository

import com.necs.marveltp.data.models.Character
import com.necs.marveltp.data.network.CharacterResult
import com.necs.marveltp.data.network.CharactersResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class CharactersRepositoryImpl(private val client: HttpClient) : CharactersRepository {
    override suspend fun getCharacters(): List<Character> {
        val response: CharactersResponse = client
            .get("v1/public/characters")
            .body<CharactersResponse>()
        return response.characters.list.map { it.toCharacter()}
    }

    override suspend fun getCharacterById(id: Int): Character {
        val response: CharactersResponse = client
            .get("v1/public/characters/$id")
            .body<CharactersResponse>()
        return response.characters.list.first().toCharacter()
    }

    override suspend fun searchCharacterByName(name: String): List<Character> {
        val response: CharactersResponse = client
            .get("v1/public/characters") {
                parameter("nameStartsWith", name)
            }
            .body<CharactersResponse>()
        return response.characters.list.map { it.toCharacter()}
    }

    private fun CharacterResult.toCharacter(): Character {
        return Character(id, name, description, thumbnail.toUrl())
    }
}
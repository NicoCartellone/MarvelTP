package com.necs.marveltp.data.network

import com.necs.marveltp.core.PRIVATE_KEY
import com.necs.marveltp.core.PUBLIC_KEY
import com.necs.marveltp.currentTimeMillis
import com.necs.marveltp.md5
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object NetworkUtils {
    val timestamp = currentTimeMillis()
    val md5 = md5(timestamp.toString() + PRIVATE_KEY + PUBLIC_KEY)
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            }, contentType = ContentType.Any)
        }
        install(DefaultRequest){
            headers {
                append("Accept", "application/json")
            }
            url {
                protocol = URLProtocol.HTTPS
                host = "gateway.marvel.com/"
                parameters.append("ts", timestamp.toString())
                parameters.append("hash", md5)
                parameters.append("apikey", PUBLIC_KEY)
            }
        }
    }
}

@Serializable
data class CharactersResponse(
    @SerialName("data") val characters: CharacterData
)

@Serializable
data class CharacterData(
    @SerialName("results")
    val list: List<CharacterResult>
)

@Serializable
data class CharacterResult(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("thumbnail") val thumbnail: Thumbnail
)

@Serializable
data class Thumbnail(
    @SerialName("path") val path: String,
    @SerialName("extension") val extension: String
) {
    fun toUrl() : String {
        return "$path.$extension"
    }
}

package ru.mav26.vkrapp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.mav26.vkrapp.data.remote.mappers.fromApi
import ru.mav26.vkrapp.data.remote.mappers.toApi
import ru.mav26.vkrapp.data.remote.models.character.CharacterResponse
import ru.mav26.vkrapp.data.remote.models.character.CharacterTypesResponse
import ru.mav26.vkrapp.domain.model.character.Character
import ru.mav26.vkrapp.domain.model.character.CharacterCreate
import ru.mav26.vkrapp.domain.model.character.CharacterItems
import ru.mav26.vkrapp.domain.model.character.CharacterStats
import ru.mav26.vkrapp.domain.model.character.CharacterType
import ru.mav26.vkrapp.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val client: HttpClient
) : CharacterRepository {
    override suspend fun getCharacter(): Character {
        val response: CharacterResponse =
            client.get("/get-character").body()
        return response.fromApi()
    }

    override suspend fun getAllCharacters(): List<Character> {
        val response: List<CharacterResponse> =
            client.get("/get-all-characters").body()
        return response.map { it.fromApi() }
    }

    override suspend fun getCharacterTypes(): List<CharacterType> {
        val response: List<CharacterTypesResponse> =
            client.get("/get-character-types").body()
        return response.map { it.fromApi() }
    }

    override suspend fun getRandomDialog(mood: Int): String {
        val response: String = client.get("/get-random-dialog/${mood}").body()
        return response
    }

    override suspend fun createCharacter(newCharacter: CharacterCreate) {
        val response = client.post("/create-character") {
            contentType(ContentType.Application.Json)
            setBody(newCharacter.toApi())
        }
    }

    override suspend fun editCharacterItems(newItems: CharacterItems, characterId: String) {
        val response = client.patch("/edit-character-items/${characterId}") {
            contentType(ContentType.Application.Json)
            setBody(newItems.toApi())
        }
    }

    override suspend fun editCharacterStats(newStats: CharacterStats, characterId: String) {
        val response = client.patch("/edit-character-stats/${characterId}") {
            contentType(ContentType.Application.Json)
            setBody(newStats.toApi())
        }
    }

}
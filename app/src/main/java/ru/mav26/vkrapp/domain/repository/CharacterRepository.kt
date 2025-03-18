package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.character.Character
import ru.mav26.vkrapp.domain.model.character.CharacterCreate
import ru.mav26.vkrapp.domain.model.character.CharacterItems
import ru.mav26.vkrapp.domain.model.character.CharacterStats
import ru.mav26.vkrapp.domain.model.character.CharacterType

interface CharacterRepository {
    suspend fun getCharacter(userLogin: String) : Character

    suspend fun getAllCharacters(userLogin: String) : List<Character>

    suspend fun getCharacterTypes() : List<CharacterType>

    suspend fun getRandomDialog(mood: Int) : String

    suspend fun createCharacter(newCharacter: CharacterCreate, userLogin: String)

    suspend fun editCharacterItems(newItems: CharacterItems, characterId: String)

    suspend fun editCharacterStats(newStats: CharacterStats, characterId: String)
}
package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.character.Character
import ru.mav26.vkrapp.domain.model.character.CharacterCreate
import ru.mav26.vkrapp.domain.model.character.CharacterItems
import ru.mav26.vkrapp.domain.model.character.CharacterStats
import ru.mav26.vkrapp.domain.model.character.CharacterType

interface CharacterRepository {
    fun getCharacter(userLogin: String) : Character

    fun getAllCharacters(userLogin: String) : List<Character>

    fun getCharacterTypes() : List<CharacterType>

    fun getRandomDialog(mood: Int) : String

    fun createCharacter(newCharacter: CharacterCreate, userLogin: String)

    fun editCharacterItems(newItems: CharacterItems, characterId: String)

    fun editCharacterStats(newStats: CharacterStats, characterId: String)
}
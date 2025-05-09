package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.character.Character
import ru.mav26.vkrapp.domain.model.character.CharacterCreate
import ru.mav26.vkrapp.domain.model.character.CharacterItems
import ru.mav26.vkrapp.domain.model.character.CharacterStats
import ru.mav26.vkrapp.domain.model.character.CharacterType
import ru.mav26.vkrapp.domain.repository.CharacterRepository

class CharacterUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend fun getCharacter() : Character {
        return characterRepository.getCharacter()
    }

    suspend fun getAllCharacters() : List<Character> {
        return characterRepository.getAllCharacters()
    }

    suspend fun getCharacterTypes(): List<CharacterType> {
        return characterRepository.getCharacterTypes()
    }

    suspend fun getRandomDialog(mood: Int) : String {
        return characterRepository.getRandomDialog(mood)
    }

    suspend fun createCharacter(newCharacter: CharacterCreate) {
        return characterRepository.createCharacter(newCharacter)
    }

    suspend fun editItems(newItems: CharacterItems, characterId: String) {
        return characterRepository.editCharacterItems(newItems, characterId)
    }

    suspend fun editStats(newStats: CharacterStats, characterId: String) {
        return characterRepository.editCharacterStats(newStats, characterId)
    }
}
package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.character.Character
import ru.mav26.vkrapp.domain.model.character.CharacterCreate
import ru.mav26.vkrapp.domain.model.character.CharacterItems
import ru.mav26.vkrapp.domain.model.character.CharacterStats
import ru.mav26.vkrapp.domain.repository.CharacterRepository

class CharacterUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend fun getCharacter(userLogin: String) : Character {
        return characterRepository.getCharacter(userLogin)
    }

    suspend fun getAllCharacters(userLogin: String) : List<Character> {
        return characterRepository.getAllCharacters(userLogin)
    }

    suspend fun getRandomDialog(mood: Int) : String {
        return characterRepository.getRandomDialog(mood)
    }

    suspend fun createCharacter(newCharacter: CharacterCreate, userLogin: String) {
        return characterRepository.createCharacter(newCharacter, userLogin)
    }

    suspend fun editItems(newItems: CharacterItems, characterId: String) {
        return characterRepository.editCharacterItems(newItems, characterId)
    }

    suspend fun editStats(newStats: CharacterStats, characterId: String) {
        return characterRepository.editCharacterStats(newStats, characterId)
    }
}
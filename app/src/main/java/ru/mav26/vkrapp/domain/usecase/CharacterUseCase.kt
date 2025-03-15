package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.character.Character
import ru.mav26.vkrapp.domain.model.character.CharacterCreate
import ru.mav26.vkrapp.domain.model.character.CharacterItems
import ru.mav26.vkrapp.domain.model.character.CharacterStats
import ru.mav26.vkrapp.domain.repository.CharacterRepository

class CharacterUseCase(
    private val characterRepository: CharacterRepository
) {
    fun getCharacter(userLogin: String) : Character {
        return characterRepository.getCharacter(userLogin)
    }

    fun getAllCharacters(userLogin: String) : List<Character> {
        return characterRepository.getAllCharacters(userLogin)
    }

    fun getRandomDialog(mood: Int) : String {
        return characterRepository.getRandomDialog(mood)
    }

    fun createCharacter(newCharacter: CharacterCreate, userLogin: String) {
        return characterRepository.createCharacter(newCharacter, userLogin)
    }

    fun editItems(newItems: CharacterItems, characterId: String) {
        return characterRepository.editCharacterItems(newItems, characterId)
    }

    fun editStats(newStats: CharacterStats, characterId: String) {
        return characterRepository.editCharacterStats(newStats, characterId)
    }
}
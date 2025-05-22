package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.states

import ru.mav26.vkrapp.domain.model.character.Character

data class CharacterState(
    val character: Character? = null,
    val allCharacters: List<Character> = emptyList(),

    val isLoading: Boolean = false
)

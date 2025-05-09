package ru.mav26.vkrapp.presentation.feature.createCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mav26.vkrapp.domain.model.character.CharacterCreate
import ru.mav26.vkrapp.domain.usecase.CharacterUseCase

class CreateCharacterViewModel(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CreateCharacterState())
    val state: StateFlow<CreateCharacterState> = _state

    fun getTypes() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = characterUseCase.getCharacterTypes()
            _state.update { it.copy(types = list) }
        }
    }

    fun createCharacter() {
        val character = CharacterCreate(
            characterName = _state.value.characterName,
            gender = _state.value.characterGender,
            hairId = "", /*TODO: default val string*/
            chestplateId = "", /*TODO: default val string*/
            footsId = "", /*TODO: default val string*/
            legsId = "", /*TODO: default val string*/
            backgroundId = "", /*TODO: default val string*/
            characterType = _state.value.characterType
        )

        viewModelScope.launch(Dispatchers.IO) {
            characterUseCase.createCharacter(character)
        }
    }

    fun changeName(name: String) {
        _state.update { it.copy(characterName = name) }
    }

    fun changeGender(gender: Boolean) {
        _state.update { it.copy(characterGender = gender) }
    }

    fun changeType(type: String) {
        _state.update { it.copy(characterType = type) }
    }
}
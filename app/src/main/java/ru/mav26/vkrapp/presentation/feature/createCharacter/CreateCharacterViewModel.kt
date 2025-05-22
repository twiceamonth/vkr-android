package ru.mav26.vkrapp.presentation.feature.createCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mav26.vkrapp.domain.model.character.CharacterCreate
import ru.mav26.vkrapp.domain.usecase.CharacterUseCase

class CreateCharacterViewModel(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CreateCharacterState())
    val state: StateFlow<CreateCharacterState> = _state

    fun getTypes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val list = withContext(Dispatchers.IO) {
                characterUseCase.getCharacterTypes()
            }
            _state.update { it.copy(types = list, isLoading = false) }
        }
    }

    fun createCharacter() {
        val character = CharacterCreate(
            characterName = _state.value.characterName,
            gender = _state.value.characterGender,
            hairId = "d27d4678-5a0e-41b8-b791-102c6eb28f75", /*TODO: default val string*/
            chestplateId = "d27d4678-5a0e-41b8-b791-102c6eb28f75", /*TODO: default val string*/
            footsId = "d27d4678-5a0e-41b8-b791-102c6eb28f75", /*TODO: default val string*/
            legsId = "d27d4678-5a0e-41b8-b791-102c6eb28f75", /*TODO: default val string*/
            backgroundId = "d27d4678-5a0e-41b8-b791-102c6eb28f75", /*TODO: default val string*/
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
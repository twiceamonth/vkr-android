package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mav26.vkrapp.domain.model.character.CharacterItems
import ru.mav26.vkrapp.domain.model.character.CharacterStats
import ru.mav26.vkrapp.domain.usecase.CharacterUseCase
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.states.CharacterState

class CharacterViewModel(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CharacterState())
    val state: StateFlow<CharacterState> = _state

    /*todo: update character in state*/

    fun getCharacter(userLogin: String) {
        /*todo: userlogin env*/
        viewModelScope.launch(Dispatchers.IO) {
            val char = characterUseCase.getCharacter(userLogin)
            _state.update { it.copy(character = char) }
        }
    }

    fun getAllCharacters(userLogin: String) {
        /*todo: userlogin env*/
        viewModelScope.launch(Dispatchers.IO) {
            val charList = characterUseCase.getAllCharacters(userLogin)
            _state.update { it.copy(allCharacters = charList) }
        }
    }

    fun getDialog() {
        viewModelScope.launch {
            val text = _state.value.character?.mood?.let { characterUseCase.getRandomDialog(it) }
        }
    }

    fun editItems(newItems: CharacterItems) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value.character?.let {
                characterUseCase.editItems(newItems, it.characterId)
            }
        }
    }

    fun editStats(newStats: CharacterStats) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value.character?.let { characterUseCase.editStats(newStats, it.characterId) }
        }
    }
}
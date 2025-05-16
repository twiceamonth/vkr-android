package ru.mav26.vkrapp.presentation.feature.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mav26.vkrapp.domain.usecase.StoreUseCase

class InventoryViewModel(
    private val storeUseCase: StoreUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(InventoryState())
    val state: StateFlow<InventoryState> = _state

    /* TODO: состояние персонажа */

    fun heal(characterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            storeUseCase.heal(characterId)
        }
    }

    fun getInventory() {
        viewModelScope.launch(Dispatchers.IO) {
            val inventory = storeUseCase.getInventory()
            _state.update { it.copy(inventoryItems = inventory) }
        }
    }
}
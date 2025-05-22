package ru.mav26.vkrapp.presentation.feature.inventory

sealed class InventoryUiState {
    object ItemList : InventoryUiState()
    data class ItemType(val type: String) : InventoryUiState()
}

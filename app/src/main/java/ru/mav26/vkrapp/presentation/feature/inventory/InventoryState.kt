package ru.mav26.vkrapp.presentation.feature.inventory

import ru.mav26.vkrapp.domain.model.store.Inventory

data class InventoryState(
    val inventoryItems: List<Inventory> = emptyList()
)

package ru.mav26.vkrapp.presentation.feature.store

import ru.mav26.vkrapp.domain.model.store.StoreItem

data class StoreState(
    val items: List<StoreItem> = emptyList()
)

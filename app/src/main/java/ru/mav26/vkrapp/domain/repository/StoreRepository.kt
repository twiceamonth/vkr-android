package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.store.Inventory
import ru.mav26.vkrapp.domain.model.store.StoreItem

interface StoreRepository {
    suspend fun getItemsList(type: String, userLogin: String) : List<StoreItem>

    suspend fun getInventory() : List<Inventory>

    suspend fun heal(characterId: String)

    suspend fun buyItem(storeId: String, userLogin: String)
}
package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.store.Inventory
import ru.mav26.vkrapp.domain.model.store.StoreItem

interface StoreRepository {
    fun getItemsList(type: String, userLogin: String) : List<StoreItem>

    fun getInventory() : List<Inventory>

    fun heal(characterId: String)

    fun buyItem(storeId: String, userLogin: String)
}
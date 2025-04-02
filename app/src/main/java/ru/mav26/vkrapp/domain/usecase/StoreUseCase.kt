package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.store.Inventory
import ru.mav26.vkrapp.domain.model.store.StoreItem
import ru.mav26.vkrapp.domain.repository.StoreRepository

class StoreUseCase(
    private val storeRepository: StoreRepository
) {
    suspend fun getItems(type: String, userLogin: String) : List<StoreItem> {
        return storeRepository.getItemsList(type, userLogin)
    }

    suspend fun getInventory(userLogin: String) : List<Inventory> {
        return storeRepository.getInventory(userLogin)
    }

    suspend fun heal(characterId: String) {
        return storeRepository.heal(characterId)
    }

    suspend fun buyItem(storeId: String, userLogin: String) {
        return storeRepository.buyItem(storeId, userLogin)
    }
}
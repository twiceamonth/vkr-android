package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.store.Inventory
import ru.mav26.vkrapp.domain.model.store.StoreItem
import ru.mav26.vkrapp.domain.repository.StoreRepository

class StoreUseCase(
    private val storeRepository: StoreRepository
) {
    fun getItems(type: String, userLogin: String) : List<StoreItem> {
        return storeRepository.getItemsList(type, userLogin)
    }

    fun getInventory() : List<Inventory> {
        return storeRepository.getInventory()
    }

    fun heal(characterId: String) {
        return storeRepository.heal(characterId)
    }

    fun buyItem(storeId: String, userLogin: String) {
        return storeRepository.buyItem(storeId, userLogin)
    }
}
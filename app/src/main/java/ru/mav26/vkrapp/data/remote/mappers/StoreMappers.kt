package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.store.InventoryResponse
import ru.mav26.vkrapp.data.remote.models.store.StoreItemsResponse
import ru.mav26.vkrapp.domain.model.store.Inventory
import ru.mav26.vkrapp.domain.model.store.StoreItem

fun InventoryResponse.fromApi() : Inventory {
    return Inventory(
        type = this.type,
        imagePath = this.imagePath
    )
}

fun StoreItemsResponse.fromApi() : StoreItem {
    return StoreItem(
        storeId = this.storeId,
        title = this.title,
        imagePath = this.imagePath,
        description = this.description,
        cost = this.cost,
        lvlToBuy = this.lvlToBuy,
        isOwned = this.isOwned,
        isApplied = this.isApplied,
        itemId = this.itemId
    )
}
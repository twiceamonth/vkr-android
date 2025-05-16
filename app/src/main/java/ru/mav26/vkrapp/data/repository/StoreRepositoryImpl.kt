package ru.mav26.vkrapp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import ru.mav26.vkrapp.data.remote.mappers.fromApi
import ru.mav26.vkrapp.data.remote.models.store.InventoryResponse
import ru.mav26.vkrapp.data.remote.models.store.StoreItemsResponse
import ru.mav26.vkrapp.domain.model.store.Inventory
import ru.mav26.vkrapp.domain.model.store.StoreItem
import ru.mav26.vkrapp.domain.repository.StoreRepository

class StoreRepositoryImpl(
    private val client: HttpClient
) : StoreRepository {
    override suspend fun getItemsList(type: String): List<StoreItem> {
        val response: List<StoreItemsResponse> =
            client.get("/store-items-list/${type}").body()
        return response.map { it.fromApi() }
    }

    override suspend fun getInventory(): List<Inventory> {
        val response: List<InventoryResponse> =
            client.get("/inventory").body()
        return response.map { it.fromApi() }
    }

    override suspend fun heal(characterId: String) {
        val response = client.post("/heal-button/${characterId}")
    }

    override suspend fun buyItem(storeId: String) {
        val response = client.post("/buy-item/${storeId}")
    }

}
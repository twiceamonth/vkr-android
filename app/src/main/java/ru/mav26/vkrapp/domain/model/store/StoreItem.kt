package ru.mav26.vkrapp.domain.model.store

data class StoreItem(
    val storeId: String,
    val title: String,
    val imagePath: String,
    val description: String,
    val cost: Int,
    val lvlToBuy: Int,
    val isOwned: Boolean,
    val isApplied: Boolean,
    val itemId: String
)

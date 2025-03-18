package ru.mav26.vkrapp.data.remote.models.bosses

import kotlinx.serialization.Serializable

@Serializable
data class ActiveBossResponse(
    val bossName: String,
    val criteriaType: String,
    val criteriaValue: Int,
    val maxHp: Int,
    val resistType: String,
    val resistValue: Int,
    val bonusType: String,
    val bonusValue: Int,
    val baseDamage: Int,
    val imagePath: String,
    val currentHp: Int
)

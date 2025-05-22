package ru.mav26.vkrapp.domain.model.character

import java.time.OffsetDateTime

data class Character(
    val characterId: String,
    val characterName: String,
    val gender: Boolean,
    val hair: String,
    val chestplate: String,
    val foots: String,
    val legs: String,
    val background: String,
    val characterType: String,
    val level: Int,
    val maxHp: Int,
    val currentHp: Int,
    val exp: Int,
    val expToNextLvl: Int,
    val coins: Int,
    val mood: Int,
    val stressCoef: Int,
    val createdAt: OffsetDateTime,
    val deadAt: OffsetDateTime? = null
)

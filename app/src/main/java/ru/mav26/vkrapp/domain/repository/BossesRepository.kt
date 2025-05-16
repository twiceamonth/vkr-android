package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.bosses.ActiveBoss

interface BossesRepository {
    suspend fun getActiveBoss() : ActiveBoss?

    suspend fun makeDamage(taskDiff: String)
}
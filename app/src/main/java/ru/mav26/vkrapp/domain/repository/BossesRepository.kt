package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.bosses.ActiveBoss

interface BossesRepository {
    suspend fun getActiveBoss(userLogin: String) : ActiveBoss?

    suspend fun makeDamage(userLogin: String, taskDiff: String)
}
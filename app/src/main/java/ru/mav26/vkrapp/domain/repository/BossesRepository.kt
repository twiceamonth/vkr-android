package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.bosses.ActiveBoss

interface BossesRepository {
    fun getActiveBoss(userLogin: String) : ActiveBoss?

    fun makeDamage(userLogin: String, taskDiff: String)
}
package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.bosses.ActiveBoss
import ru.mav26.vkrapp.domain.repository.BossesRepository

class BossesUseCase(
    private val bossesRepository: BossesRepository
) {
    suspend fun getActiveBoss() : ActiveBoss? {
        return bossesRepository.getActiveBoss()
    }

    suspend fun makeDamage(taskDiff: String) {
        return bossesRepository.makeDamage(taskDiff)
    }
}
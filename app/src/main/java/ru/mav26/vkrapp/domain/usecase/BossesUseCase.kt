package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.bosses.ActiveBoss
import ru.mav26.vkrapp.domain.repository.BossesRepository

class BossesUseCase(
    private val bossesRepository: BossesRepository
) {
    fun getActiveBoss(userLogin: String) : ActiveBoss? {
        return bossesRepository.getActiveBoss(userLogin)
    }

    fun makeDamage(userLogin: String, taskDiff: String) {
        return bossesRepository.makeDamage(userLogin, taskDiff)
    }
}
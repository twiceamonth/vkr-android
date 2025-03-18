package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.bosses.ActiveBossResponse
import ru.mav26.vkrapp.domain.model.bosses.ActiveBoss

fun ActiveBossResponse.fromApi() : ActiveBoss {
    return ActiveBoss(
        bossName = this.bossName,
        criteriaType = this.criteriaType,
        criteriaValue = this.criteriaValue,
        maxHp = this.maxHp,
        resistType = this.resistType,
        resistValue = this.resistValue,
        bonusType = this.bonusType,
        bonusValue = this.bonusValue,
        baseDamage = this.baseDamage,
        imagePath = this.imagePath,
        currentHp = this.currentHp
    )
}
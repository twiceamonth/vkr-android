package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.character.CharacterResponse
import ru.mav26.vkrapp.data.remote.models.character.CharacterTypesResponse
import ru.mav26.vkrapp.data.remote.models.character.CreateCharacter
import ru.mav26.vkrapp.domain.model.character.Character
import ru.mav26.vkrapp.domain.model.character.CharacterCreate
import ru.mav26.vkrapp.domain.model.character.CharacterItems
import ru.mav26.vkrapp.domain.model.character.CharacterStats
import ru.mav26.vkrapp.domain.model.character.CharacterType
import java.time.OffsetTime

fun CharacterResponse.fromApi() : Character {
    return Character(
        characterId = this.characterId,
        characterName = this.characterName,
        gender = this.gender,
        hair = this.hair,
        chestplate = this.chestplate,
        foots = this.foots,
        legs = this.legs,
        background = this.background,
        characterType = this.characterType,
        level = this.level,
        maxHp = this.maxHp,
        currentHp = this.currentHp,
        exp = this.exp,
        coins = this.coins,
        stressCoef = this.stressCoef,
        createdAt = OffsetTime.parse(this.createdAt),
        deadAt = OffsetTime.parse(this.deadAt)
    )
}

fun CharacterTypesResponse.fromApi() : CharacterType {
    return CharacterType(
        characterType = this.characterType,
        imagePath = this.imagePath,
        bonusType = this.bonusType,
        description = this.description,
        effect = this.effect,
        multiplier = this.multiplier
    )
}

fun CharacterItems.toApi() : ru.mav26.vkrapp.data.remote.models.character.CharacterItems {
    return ru.mav26.vkrapp.data.remote.models.character.CharacterItems(
        hairId = this.hairId,
        chestplateId = this.chestplateId,
        footsId = this.hairId,
        legsId = this.hairId,
        backgroundId = this.backgroundId
    )
}

fun CharacterStats.toApi() : ru.mav26.vkrapp.data.remote.models.character.CharacterStats {
    return ru.mav26.vkrapp.data.remote.models.character.CharacterStats(
        level = this.level,
        maxHp = this.maxHp,
        currentHp = this.currentHp,
        exp = this.exp,
        coins = this.coins,
        stressCoef = this.stressCoef,
        isDead = this.isDead,
        baseDamage = this.baseDamage,
        deadAt = this.deadAt.toString(),
        moodLevel = this.moodLevel
    )
}

fun CharacterCreate.toApi() : CreateCharacter {
    return CreateCharacter(
        characterName = this.characterName,
        gender = this.gender,
        hairId = this.hairId,
        chestplateId = this.chestplateId,
        footsId = this.footsId,
        legsId = this.legsId,
        backgroundId = this.footsId,
        characterType = this.characterType
    )
}
package ru.mav26.vkrapp.domain

import org.koin.dsl.module
import ru.mav26.vkrapp.domain.usecase.AchievementsUseCase
import ru.mav26.vkrapp.domain.usecase.AuthUseCase
import ru.mav26.vkrapp.domain.usecase.BossesUseCase
import ru.mav26.vkrapp.domain.usecase.CharacterUseCase
import ru.mav26.vkrapp.domain.usecase.EffectsUseCase
import ru.mav26.vkrapp.domain.usecase.EventsUseCase
import ru.mav26.vkrapp.domain.usecase.StoreUseCase
import ru.mav26.vkrapp.domain.usecase.TaskUseCase

val domainModule = module {
    factory { TaskUseCase(get()) }
    factory { StoreUseCase(get()) }
    factory { EventsUseCase(get()) }
    factory { EffectsUseCase(get()) }
    factory { CharacterUseCase(get()) }
    factory { BossesUseCase(get()) }
    factory { AuthUseCase(get()) }
    factory { AchievementsUseCase(get()) }
}
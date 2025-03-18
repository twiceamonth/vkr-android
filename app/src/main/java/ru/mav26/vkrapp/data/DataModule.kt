package ru.mav26.vkrapp.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module
import ru.mav26.vkrapp.data.repository.AchievementsRepositoryImpl
import ru.mav26.vkrapp.data.repository.AuthRepositoryImpl
import ru.mav26.vkrapp.data.repository.BossesRepositoryImpl
import ru.mav26.vkrapp.data.repository.CharacterRepositoryImpl
import ru.mav26.vkrapp.data.repository.EffectsRepositoryImpl
import ru.mav26.vkrapp.data.repository.EventsRepositoryImpl
import ru.mav26.vkrapp.data.repository.StoreRepositoryImpl
import ru.mav26.vkrapp.data.repository.TaskRepositoryImpl
import ru.mav26.vkrapp.domain.repository.AchievementsRepository
import ru.mav26.vkrapp.domain.repository.AuthRepository
import ru.mav26.vkrapp.domain.repository.BossesRepository
import ru.mav26.vkrapp.domain.repository.CharacterRepository
import ru.mav26.vkrapp.domain.repository.EffectsRepository
import ru.mav26.vkrapp.domain.repository.EventsRepository
import ru.mav26.vkrapp.domain.repository.StoreRepository
import ru.mav26.vkrapp.domain.repository.TaskRepository

val dataModule = module {
    single { HttpClient(CIO) }

    single<AchievementsRepository> { AchievementsRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<BossesRepository> { BossesRepositoryImpl(get()) }
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    single<EffectsRepository> { EffectsRepositoryImpl(get()) }
    single< EventsRepository> { EventsRepositoryImpl(get()) }
    single< StoreRepository> { StoreRepositoryImpl(get()) }
    single<TaskRepository> { TaskRepositoryImpl(get()) }
}
package ru.mav26.vkrapp.presentation

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.mav26.vkrapp.presentation.feature.achievements.AchievementsViewModel
import ru.mav26.vkrapp.presentation.feature.auth.AuthViewModel
import ru.mav26.vkrapp.presentation.feature.createCharacter.CreateCharacterViewModel
import ru.mav26.vkrapp.presentation.feature.inventory.InventoryViewModel
import ru.mav26.vkrapp.presentation.feature.store.StoreViewModel
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.ActivitiesViewModel
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.CharacterViewModel
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.TaskViewModel

val presentationModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { StoreViewModel(get()) }
    viewModel { InventoryViewModel(get()) }
    viewModel { CreateCharacterViewModel(get()) }
    viewModel { CharacterViewModel(get()) }
    viewModel { AchievementsViewModel(get()) }
    viewModel { ActivitiesViewModel(get(), get(), get()) }
    viewModel { TaskViewModel(get()) }
}
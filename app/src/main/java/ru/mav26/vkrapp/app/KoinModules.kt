package ru.mav26.vkrapp.app

import ru.mav26.vkrapp.data.dataModule
import ru.mav26.vkrapp.domain.domainModule
import ru.mav26.vkrapp.presentation.presentationModule

val appModules = listOf(
    domainModule,
    dataModule,
    presentationModule
)
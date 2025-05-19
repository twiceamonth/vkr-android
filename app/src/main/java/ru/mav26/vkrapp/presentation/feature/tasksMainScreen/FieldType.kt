package ru.mav26.vkrapp.presentation.feature.tasksMainScreen

sealed class FieldType {
    data object Text : FieldType()
    data object Date : FieldType()
    data object Time : FieldType()
}
package ru.mav26.vkrapp.domain.model.task

data class SubtaskEdit(
    val title: String ?= null,
    val status: Boolean ?= null
)
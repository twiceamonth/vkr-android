package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.events.ActiveEvent

interface EventsRepository {
    suspend fun getActiveEvent() : ActiveEvent?

    suspend fun updateEventProgress(activeEventId: String, type: String)
}
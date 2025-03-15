package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.events.ActiveEvent

interface EventsRepository {
    fun getActiveEvent(userLogin: String) : ActiveEvent?

    fun updateEventProgress(activeEventId: String, userLogin: String, type: String)
}
package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.events.ActiveEvent
import ru.mav26.vkrapp.domain.repository.EventsRepository

class EventsUseCase(
    private val eventsRepository: EventsRepository
) {
    fun getActiveEvent(userLogin: String) : ActiveEvent? {
        return eventsRepository.getActiveEvent(userLogin)
    }

    fun updateProgress(activeEventId: String, userLogin: String, type: String) {
        return eventsRepository.updateEventProgress(activeEventId, userLogin, type)
    }
}
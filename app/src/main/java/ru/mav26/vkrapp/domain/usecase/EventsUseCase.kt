package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.events.ActiveEvent
import ru.mav26.vkrapp.domain.repository.EventsRepository

class EventsUseCase(
    private val eventsRepository: EventsRepository
) {
    suspend fun getActiveEvent() : ActiveEvent? {
        return eventsRepository.getActiveEvent()
    }

    suspend fun updateProgress(activeEventId: String, type: String) {
        return eventsRepository.updateEventProgress(activeEventId, type)
    }
}
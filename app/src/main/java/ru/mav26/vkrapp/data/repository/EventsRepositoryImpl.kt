package ru.mav26.vkrapp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import ru.mav26.vkrapp.data.remote.mappers.fromApi
import ru.mav26.vkrapp.data.remote.models.events.ActiveEventResponse
import ru.mav26.vkrapp.domain.model.events.ActiveEvent
import ru.mav26.vkrapp.domain.repository.EventsRepository

class EventsRepositoryImpl(
    private val client: HttpClient,
) : EventsRepository {
    override suspend fun getActiveEvent(): ActiveEvent? {
        val response: ActiveEventResponse =
            client.get("/get-active-event").body()
        return response.fromApi()
    }

    override suspend fun updateEventProgress(
        activeEventId: String,
        type: String,
    ) {
        val response =
            client.patch("/update-event-progress/${activeEventId}/${type}")
    }

}
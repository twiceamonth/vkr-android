package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun CharacterPreview(
    background: String,
    body: String,
    head: String,
    legs: String,
    shoes: String,
    chest: String,
    modifier: Modifier = Modifier
) {
    val elementSize = with(LocalDensity.current) { 128.toDp() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(110.dp)
            .height(140.dp)
    ) {
        // Задний фон
        AsyncImage(
            model = background,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Основной контейнер для частей тела
        Box(
            modifier = Modifier
                .size(elementSize)
                .align(Alignment.Center)
        ) {
            // Тело персонажа (базовый слой)
            AsyncImage(
                model = body,
                contentDescription = "Тело",
                contentScale = ContentScale.None,
                modifier = Modifier.size(elementSize)
            )

            // Ноги
            AsyncImage(
                model = legs,
                contentDescription = "Ноги",
                contentScale = ContentScale.None,
                modifier = Modifier.size(elementSize)
            )

            // Одежда
            AsyncImage(
                model = chest,
                contentDescription = "Одежда",
                contentScale = ContentScale.None,
                modifier = Modifier.size(elementSize)
            )

            // Голова
            AsyncImage(
                model = head,
                contentDescription = "Голова",
                contentScale = ContentScale.None,
                modifier = Modifier.size(elementSize)
            )

            // Обувь
            AsyncImage(
                model = shoes,
                contentDescription = "Обувь",
                contentScale = ContentScale.None,
                modifier = Modifier.size(elementSize)
            )
        }
    }
}

@Preview
@Composable
private fun prevvv() {
    CharacterPreview(
        background = "TODO()",
        body = "TODO()",
        head = "TODO()",
        legs = "TODO()",
        shoes = "TODO()",
        chest = "TODO()"
    )

}
package ru.mav26.vkrapp.presentation.feature.inventory.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mav26.vkrapp.app.Constants
import ru.mav26.vkrapp.domain.model.character.Character

@Composable
fun InventoryItems(
    character: Character,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            ItemCard(
                itemImage = character.hair,
                itemTitle = "Голова",
                itemType = Constants.TYPE_HEAD,
                onClick = { onItemClick(Constants.TYPE_HEAD) }
            )

            ItemCard(
                itemImage = character.chestplate,
                itemTitle = "Торс",
                itemType = Constants.TYPE_BODY,
                onClick = { onItemClick(Constants.TYPE_BODY) }
            )

            ItemCard(
                itemImage = character.legs,
                itemTitle = "Ноги",
                itemType = Constants.TYPE_LEGS,
                onClick = { onItemClick(Constants.TYPE_LEGS) }
            )
        }

        Spacer(Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            ItemCard(
                itemImage = character.foots,
                itemTitle = "Обувь",
                itemType = Constants.TYPE_FOOTS,
                onClick = { onItemClick(Constants.TYPE_FOOTS) }
            )

            Spacer(Modifier.width(44.dp))

            ItemCard(
                itemImage = character.background,
                itemTitle = "Фон",
                itemType = Constants.TYPE_BCG,
                onClick = { onItemClick(Constants.TYPE_BCG) }
            )
        }
    }
}
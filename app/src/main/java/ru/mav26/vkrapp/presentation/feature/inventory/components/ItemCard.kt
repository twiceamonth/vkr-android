package ru.mav26.vkrapp.presentation.feature.inventory.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.mav26.vkrapp.presentation.theme.mainColor

@Composable
fun ItemCard(
    itemImage: String,
    itemTitle: String,
    itemType: String,
    onClick: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(80.dp)
                .border(
                    width = 1.dp,
                    color = mainColor,
                    shape = RoundedCornerShape(5.dp)
                )
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { onClick(itemType) }
                )
        ) {
            AsyncImage(
                model = itemImage,
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = itemTitle,
            color = mainColor,
            fontSize = 12.sp
        )
    }

}
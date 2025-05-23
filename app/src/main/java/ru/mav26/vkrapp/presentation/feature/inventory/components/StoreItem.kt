package ru.mav26.vkrapp.presentation.feature.inventory.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.app.Constants
import ru.mav26.vkrapp.domain.model.store.StoreItem
import ru.mav26.vkrapp.presentation.theme.mainColor

@Composable
fun StoreItem(
    item: StoreItem,
    itemType: String,
    onClick: (StoreItem) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(80.dp)
                .then(
                    if(item.isApplied) Modifier.border(
                        width = 1.dp,
                        color = mainColor,
                        shape = RoundedCornerShape(5.dp)
                    ) else Modifier
                )
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { onClick(item) }
                )
        ) {
            AsyncImage(
                model = Constants.BASE_URL + item.imagePath,
                contentDescription = null,
                contentScale = if(itemType == Constants.TYPE_BCG) ContentScale.Crop else ContentScale.Fit,
                modifier = Modifier
                    .then(
                        if (itemType == Constants.TYPE_BCG) Modifier.fillMaxSize() else Modifier.size(60.dp)
                    )
                    .clip(RoundedCornerShape(5.dp))
            )
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = item.title,
            color = mainColor,
            fontSize = 12.sp
        )

        if(!item.isOwned) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.coins),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = item.cost.toString(),
                    color = mainColor,
                    fontSize = 12.sp
                )
            }
        }
    }
}
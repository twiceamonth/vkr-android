package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.presentation.theme.mainColor

@Composable
fun PrioritySelect(
    text: String,
    icon: String,
    option: String,
    isSelected: Boolean,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val md = if(isSelected) modifier.border(
        width = 1.dp ,
        shape = RoundedCornerShape(5.dp),
        color = mainColor
    ) else modifier

    Box(
        modifier = md
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onSelect(option) })
            .padding(8.dp)
    ) {
        Row {
            Text(
                text = icon,
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 4.dp)
            )

            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
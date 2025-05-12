package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.presentation.theme.accentColor
import ru.mav26.vkrapp.presentation.theme.backgroundColor
import ru.mav26.vkrapp.presentation.theme.mainColor

@Composable
fun AddButton(onCLick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(90.dp)
            .background(
                color = mainColor,
                shape = RoundedCornerShape(25.dp)
            )
            .border(
                width = 6.dp,
                color = backgroundColor,
                shape = RoundedCornerShape(25.dp)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onCLick
            )
    ) {
        Icon(
            painter = painterResource(R.drawable.add),
            tint = accentColor,
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
    }
}
package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.app.Constants
import ru.mav26.vkrapp.app.Constants.Tabs
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.NavTab
import ru.mav26.vkrapp.presentation.theme.backgroundColor
import ru.mav26.vkrapp.presentation.theme.mainColor

@Composable
fun NavTabItem(
    tab: NavTab,
    selected: Boolean,
    onClick: (NavTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    val cornerShape = when (tab.id) {
        Constants.Tabs.ASSIGNMENTS -> RoundedCornerShape(bottomStart = 15.dp)

        Constants.Tabs.INVENTORY -> RoundedCornerShape(bottomEnd = 15.dp)

        else -> RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
    }

    val arrangment =
        if (tab.id in listOf(Constants.Tabs.TASKS, Constants.Tabs.ASSIGNMENTS, Constants.Tabs.ACHIEVEMENTS)) Arrangement.Start
        else Arrangement.End

    val padding =
        if (tab.id in listOf(Constants.Tabs.TASKS,Constants.Tabs.ASSIGNMENTS, Constants.Tabs.ACHIEVEMENTS)) PaddingValues(start = 12.dp)
        else PaddingValues(end = 12.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (selected) mainColor else backgroundColor,
                shape = cornerShape
            )
            .border(
                width = 1.dp,
                color = mainColor,
                shape = cornerShape
            )
            .height(43.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick(tab) }
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = arrangment,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(padding)
        ) {
            Icon(
                painter = painterResource(tab.icon),
                tint = if (selected) backgroundColor else mainColor,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Spacer(Modifier.width(4.dp))

            Text(
                text = tab.label,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = if (selected) backgroundColor else mainColor
            )
        }
    }
}

package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mav26.vkrapp.app.Constants
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.NavTab
import ru.mav26.vkrapp.presentation.theme.backgroundColor

@Composable
fun BottomNavBar(
    topTabs: List<NavTab>,
    bottomTabs: List<NavTab>,
    selectedTab: NavTab,
    onTabSelected: (NavTab) -> Unit,
    onCenterClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(90.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(15.dp))
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(0.5f)
            ) {
                if (selectedTab.id != Constants.Tabs.INVENTORY){
                    topTabs.forEach { tab ->
                        NavTabItem(
                            tab = tab,
                            selected = tab.id == selectedTab.id,
                            onClick = { onTabSelected(it) },
                            modifier = Modifier.weight(0.5f)
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(0.5f)
            ) {
                bottomTabs.forEach { tab ->
                    NavTabItem(
                        tab = tab,
                        selected = tab.id == selectedTab.id,
                        onClick = { onTabSelected(it) },
                        modifier = Modifier.weight(0.5f)
                    )
                }
            }
        }

        AddButton(onCLick = onCenterClick)
    }
}
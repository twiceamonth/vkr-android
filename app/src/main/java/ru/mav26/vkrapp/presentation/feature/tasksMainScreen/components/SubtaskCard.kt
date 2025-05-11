package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.domain.model.task.Subtask
import ru.mav26.vkrapp.presentation.theme.defaultCard

@Composable
fun SubtaskCard(
    subtask: Subtask,
    cardColor: Color,
    roundedCorner: Dp,
    isLast: Boolean,
    modifier: Modifier = Modifier,
    onStatusChange: (Boolean, String) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(
                color = cardColor.copy(alpha = 0.5f),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = if (isLast) roundedCorner else 10.dp,
                    bottomStart = if (isLast) roundedCorner else 10.dp
                )
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomCheck(
                color = defaultCard,
                checked = subtask.status,
                isTimer = false,
                isLast = isLast,
                roundedCorner = if (isLast) roundedCorner else 10.dp,
                onCheck = { onStatusChange(it, subtask.subtaskId) },
                onTimerStart = { null }
            )

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = 11.dp, vertical = 8.dp)
            ) {
                Text(
                    text = subtask.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}
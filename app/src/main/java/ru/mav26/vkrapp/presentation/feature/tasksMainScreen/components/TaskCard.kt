package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.app.Constants
import ru.mav26.vkrapp.domain.model.task.Task
import ru.mav26.vkrapp.presentation.theme.defaultCard
import ru.mav26.vkrapp.presentation.theme.easyCard
import ru.mav26.vkrapp.presentation.theme.hardCard
import ru.mav26.vkrapp.presentation.theme.mainColor
import ru.mav26.vkrapp.presentation.theme.mediumCard

@Composable
fun TaskCard(
    task: Task,
    onStatusChange: (Boolean) -> Unit,
    onSubStatusChange: (Boolean, String) -> Unit,
    onTimerStart: () -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    val degree by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = tween(durationMillis = 200)
    )

    val roundedCorner by animateDpAsState(
        targetValue = if (expanded) 0.dp else 10.dp,
        animationSpec = tween(durationMillis = 200)
    )

    val cardColor = when (task.difficulty) {
        Constants.DIFFICULTY_EASY -> easyCard
        Constants.DIFFICULTY_MEDIUM -> mediumCard
        Constants.DIFFICULTY_HARD -> hardCard
        else -> defaultCard
    }

    val cardEmote = when (task.priority) {
        Constants.PRIORITY_LOW -> stringResource(R.string.low)
        Constants.PRIORITY_MEDIUM -> stringResource(R.string.medium)
        Constants.PRIORITY_HIGH -> stringResource(R.string.high)
        else -> ""
    }

    Column(
        modifier = Modifier.animateContentSize()
    ) {
        Box(
            modifier = modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onCardClick() }
                .fillMaxWidth()
                .height(70.dp)
                .background(
                    color = cardColor.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = roundedCorner,
                        bottomStart = roundedCorner
                    )
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomCheck(
                    color = cardColor,
                    checked = task.status,
                    isTimer = (task.timerInterval != null),
                    roundedCorner = roundedCorner,
                    onCheck = { onStatusChange(it) },
                    onTimerStart = { if (task.timerInterval != null) onTimerStart() }
                )

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(
                            top = 4.dp,
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 2.dp
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        Text(
                            text = task.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = task.description,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (task.endTime != null) {
                            val day =
                                if (task.endTime.dayOfMonth < 10) "0${task.endTime.dayOfMonth}"
                                else "${task.endTime.dayOfMonth}"

                            val month =
                                if (task.endTime.monthValue < 10) "0${task.endTime.monthValue}"
                                else "${task.endTime.monthValue}"

                            val formatedDate = "${day}.${month}"

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.calendar),
                                    contentDescription = null,
                                    tint = mainColor.copy(alpha = 0.5f),
                                    modifier = Modifier.size(16.dp)
                                )

                                Spacer(Modifier.width(4.dp))

                                Text(
                                    text = formatedDate,
                                    fontSize = 12.sp,
                                    color = mainColor.copy(alpha = 0.5f)
                                )
                            }
                        }

                        if (task.timerInterval != null) {
                            val tHour = if (task.timerInterval.hour > 0) {
                                if (task.timerInterval.hour < 10) "0${task.timerInterval.hour}:"
                                else "${task.timerInterval.hour}:"
                            } else ""

                            val tHMin =
                                if (task.timerInterval.minute < 10) "0${task.timerInterval.minute}:"
                                else "${task.timerInterval.minute}:"

                            val tSec = if (task.timerInterval.second > 0) {
                                if (task.timerInterval.second < 10) "0${task.timerInterval.second}"
                                else "${task.timerInterval.second}"
                            } else "00"


                            val timerValue = "${tHour}${tHMin}${tSec}"

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.timer),
                                    contentDescription = null,
                                    tint = mainColor.copy(alpha = 0.5f),
                                    modifier = Modifier.size(16.dp)
                                )

                                Spacer(Modifier.width(4.dp))

                                Text(
                                    text = timerValue,
                                    fontSize = 12.sp,
                                    color = mainColor.copy(alpha = 0.5f)
                                )
                            }
                        }
                    }
                }
                if (task.subtasks.isNotEmpty()) {
                    IconButton(
                        onClick = { expanded = !expanded },
                        modifier = Modifier
                            .fillMaxHeight()
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.subtask_expand),
                            contentDescription = null,
                            modifier = Modifier.rotate(degree)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 20.dp, y = (-18).dp)
                    .graphicsLayer {
                        clip = false
                    }
                    .rotate(20f)
                    .padding(4.dp)
            ) {
                Text(
                    text = cardEmote,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }

    if (task.subtasks.isNotEmpty() and expanded) {
        task.subtasks.forEachIndexed { index, subtask ->
            Box(
                modifier = Modifier
                    .alpha(animateFloatAsState(if (expanded) 1f else 0f).value)
                    .fillMaxWidth()
            ) {
                val rounded = if (task.subtasks.count() - 1 != index) roundedCorner else 10.dp
                val isLast = task.subtasks.count() != index
                SubtaskCard(subtask, cardColor, rounded, isLast) { b, i -> onSubStatusChange(b, i) }
            }
        }
    }

}
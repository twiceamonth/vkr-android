package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.app.Constants
import ru.mav26.vkrapp.domain.model.task.Habit
import ru.mav26.vkrapp.presentation.theme.defaultCard
import ru.mav26.vkrapp.presentation.theme.easyCard
import ru.mav26.vkrapp.presentation.theme.hardCard
import ru.mav26.vkrapp.presentation.theme.mainColor
import ru.mav26.vkrapp.presentation.theme.mediumCard

@Composable
fun HabitCard(
    habit: Habit,
    done: Boolean,
    onStatusChange: () -> Unit,
    onTimerStart: () -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardColor = when (habit.difficulty) {
        Constants.DIFFICULTY_EASY -> easyCard
        Constants.DIFFICULTY_MEDIUM -> mediumCard
        Constants.DIFFICULTY_HARD -> hardCard
        else -> defaultCard
    }

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
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomAdd(
                color = cardColor,
                checked = done,
                isTimer = (habit.timerInterval != null),
                onCheck = { onStatusChange() },
                onTimerStart = { onTimerStart() }
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(
                        top = 10.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 4.dp
                    )
            ) {
                Column {
                    Text(
                        text = habit.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = habit.description,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (habit.frequency != "") {
                        val title = when (habit.frequency) {
                            Constants.FREQUENCY_DAILY -> stringResource(R.string.daily)
                            Constants.FREQUENCY_WEEKLY -> stringResource(R.string.weekly)
                            Constants.FREQUENCY_MONTHLY -> stringResource(R.string.monthly)
                            else -> ""
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.replays),
                                contentDescription = null,
                                tint = mainColor.copy(alpha = 0.5f),
                                modifier = Modifier.size(14.dp)
                            )

                            Spacer(Modifier.width(4.dp))

                            Text(
                                text = title,
                                fontSize = 12.sp,
                                lineHeight = 14.sp,
                                color = mainColor.copy(alpha = 0.5f)
                            )
                        }
                    }



                    if (habit.timerInterval != null) {
                        val tHour = if (habit.timerInterval.hour > 0) {
                            if (habit.timerInterval.hour < 10) "0${habit.timerInterval.hour}:"
                            else "${habit.timerInterval.hour}:"
                        } else ""

                        val tHMin =
                            if (habit.timerInterval.minute < 10) "0${habit.timerInterval.minute}:"
                            else "${habit.timerInterval.minute}:"

                        val tSec = if (habit.timerInterval.second > 0) {
                            if (habit.timerInterval.second < 10) "0${habit.timerInterval.second}"
                            else "${habit.timerInterval.second}"
                        } else ""


                        val timerValue = "${tHour}${tHMin}${tSec}"

                        Row {
                            Icon(
                                painter = painterResource(R.drawable.timer),
                                contentDescription = null,
                                tint = mainColor.copy(alpha = 0.5f),
                                modifier = Modifier.size(14.dp)
                            )

                            Spacer(Modifier.width(4.dp))

                            Text(
                                text = timerValue,
                                fontSize = 12.sp,
                                lineHeight = 14.sp,
                                color = mainColor.copy(alpha = 0.5f)
                            )
                        }
                    }

                    Row {
                        Icon(
                            painter = painterResource(R.drawable.done_times),
                            contentDescription = null,
                            tint = mainColor.copy(alpha = 0.5f),
                            modifier = Modifier.size(14.dp)
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            text = habit.streakCount.toString(),
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            color = mainColor.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun hcprev() {
    HabitCard(
        habit = Habit(
            habitId = "TODO()",
            title = "TODO()",
            difficulty = "hard",
            frequency = "week",
            timerInterval = null,
            description = "TODO()",
            streakCount = 1,
            lastPerformed = ""
        ),
        onStatusChange = {},
        onCardClick = {},
        done = false,
        onTimerStart = {}
    )
}
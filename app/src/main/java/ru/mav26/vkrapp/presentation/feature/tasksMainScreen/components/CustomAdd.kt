package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.presentation.theme.backgroundColor
import ru.mav26.vkrapp.presentation.theme.mainColor

@Composable
fun CustomAdd(
    color: Color,
    checked: Boolean,
    isTimer: Boolean,
    onCheck: () -> Unit,
    onTimerStart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                if (isTimer) {
                    onTimerStart()
                } else onCheck()
            }
            .height(70.dp)
            .width(50.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        if (isTimer) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = backgroundColor,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .size(26.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = color.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(5.dp)
                            )
                            .size(26.dp)
                    ) {
                        Icon(
                            painter = if (!checked) painterResource(R.drawable.start_timer)
                            else painterResource(R.drawable.add),
                            contentDescription = null,
                            tint = mainColor
                        )
                    }
                }

                if (!checked){
                    Text(
                        text = stringResource(R.string.start),
                        fontSize = 9.sp,
                        color = mainColor
                    )
                }
            }
        }
        else {
            Box(
                modifier = Modifier
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .size(26.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = color.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .size(26.dp)
                ) {
                    Icon(
                        painter = if (checked) painterResource(R.drawable.check)
                        else painterResource(R.drawable.add),
                        contentDescription = null,
                        tint = mainColor
                    )

                }
            }
        }
    }
}
package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.presentation.theme.backgroundColor
import ru.mav26.vkrapp.presentation.theme.mainColor

@Composable
fun CharacterStatBar(
    icon: Int,
    label: Int,
    color: Color,
    maxVal: Int,
    curVal: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(icon),
            tint = color,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(Modifier.width(2.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(80.dp)
                    )
            ) {
                val progress = if (maxVal > 0) {
                    curVal.toFloat() / maxVal.toFloat()
                } else {
                    0f
                }

                Box(
                    modifier = Modifier
                        .height(6.dp)
                        .fillMaxWidth(progress)
                        .background(
                            color = color,
                            shape = RoundedCornerShape(80.dp)
                        )
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${curVal}/${maxVal}",
                    color = mainColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
                Text(
                    text = stringResource(label),
                    color = mainColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun pppp() {
    CharacterStatBar(
        R.drawable.health,
        R.string.health,
        Color.Red,
        50,
        34
    )
}
package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.domain.model.bosses.ActiveBoss
import ru.mav26.vkrapp.presentation.theme.backgroundColor
import ru.mav26.vkrapp.presentation.theme.bossCard
import ru.mav26.vkrapp.presentation.theme.mainColor

@Composable
fun BossCard(
    boss: ActiveBoss
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                color = bossCard,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(10.dp)
    ) {
        Row {
            AsyncImage(
                model = boss.imagePath,
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )

            Spacer(Modifier.width(4.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = boss.bossName,
                    color = backgroundColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = "${boss.resistType} | ${boss.criteriaType}",
                    color = backgroundColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )

                Spacer(Modifier.fillMaxHeight().weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.health),
                        tint = backgroundColor,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(Modifier.width(2.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .height(10.dp)
                            .background(
                                color = backgroundColor,
                                shape = RoundedCornerShape(80.dp)
                            )
                    ) {
                        val progress = if (boss.maxHp > 0) {
                            boss.currentHp.toFloat() / boss.maxHp.toFloat()
                        } else {
                            0f
                        }

                        Box(
                            modifier = Modifier
                                .height(10.dp)
                                .fillMaxWidth(progress)
                                .background(
                                    color = mainColor,
                                    shape = RoundedCornerShape(80.dp)
                                )
                        )
                    }

                    Spacer(Modifier.width(2.dp))

                    Text(
                        text = "${boss.currentHp}/${boss.maxHp}",
                        color = backgroundColor,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ecprev() {
    BossCard(
        ActiveBoss(
            bossName = "TODO()",
            criteriaType = "TODO()",
            criteriaValue = 12,
            maxHp = 100,
            resistType = "TODO()",
            resistValue = 12,
            bonusType = "TODO()",
            bonusValue = 10,
            baseDamage = 10,
            imagePath = "TODO()",
            currentHp = 67
        )
    )
}
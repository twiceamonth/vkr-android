package ru.mav26.vkrapp.presentation.feature.inventory.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.domain.model.character.Character
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.CharacterStatBar
import ru.mav26.vkrapp.presentation.theme.buttonColor
import ru.mav26.vkrapp.presentation.theme.mainColor

@Composable
fun CharacterBigCard(
    character: Character,
    modifier: Modifier = Modifier,
) {
    val elementSize = with(LocalDensity.current) { 256.toDp() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(248.dp)
        ) {
            AsyncImage(
                model = character.background,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .size(elementSize)
                    .align(Alignment.Center)
            ) {
                // Тело персонажа (базовый слой)
                AsyncImage(
                    model = character.characterType,
                    contentDescription = "Тело",
                    contentScale = ContentScale.None,
                    modifier = Modifier.size(elementSize)
                )

                // Ноги
                AsyncImage(
                    model = character.legs,
                    contentDescription = "Ноги",
                    contentScale = ContentScale.None,
                    modifier = Modifier.size(elementSize)
                )

                // Одежда
                AsyncImage(
                    model = character.chestplate,
                    contentDescription = "Одежда",
                    contentScale = ContentScale.None,
                    modifier = Modifier.size(elementSize)
                )

                // Голова
                AsyncImage(
                    model = character.hair,
                    contentDescription = "Голова",
                    contentScale = ContentScale.None,
                    modifier = Modifier.size(elementSize)
                )

                // Обувь
                AsyncImage(
                    model = character.foots,
                    contentDescription = "Обувь",
                    contentScale = ContentScale.None,
                    modifier = Modifier.size(elementSize)
                )
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(color = buttonColor)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Box{
                    Text(
                        text = "Ур. ${character.level}",
                        fontSize = 16.sp,
                        color = mainColor
                    )
                }

                Spacer(Modifier.width(22.dp))

                Box(
                    modifier = Modifier.width(100.dp)
                ) {
                    CharacterStatBar(
                        icon = R.drawable.health,
                        label = R.string.emptyString,
                        color = Color.Red,
                        maxVal = character.maxHp,
                        curVal = character.currentHp
                    )
                }

                Spacer(Modifier.weight(1f))

                Box {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.coins),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            text = character.coins.toString()
                        )
                    }
                }
            }
        }
    }
}
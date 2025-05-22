package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.domain.model.character.Character
import java.time.OffsetDateTime
import java.time.OffsetTime

@Composable
fun CharacterCard(character: Character) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CharacterPreview(
                background = character.background,
                body = character.characterType,
                head = character.hair,
                legs = character.legs,
                shoes = character.foots,
                chest = character.chestplate
            )

            Column(
                modifier = Modifier.fillMaxSize().padding(top = 16.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CharacterStatBar(
                    icon = R.drawable.health,
                    label = R.string.health,
                    color = Color.Red,
                    maxVal = character.maxHp,
                    curVal = character.currentHp
                )

                CharacterStatBar(
                    icon = R.drawable.exp,
                    label = R.string.exp,
                    color = Color.Yellow,
                    maxVal = 50,
                    curVal = character.exp
                )

                CharacterStatBar(
                    icon = R.drawable.stress,
                    label = R.string.stress,
                    color = Color(0xFF48BAFF),
                    maxVal = 100,
                    curVal = character.mood
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Уровень ${character.level}"
                    )

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

@Preview
@Composable
private fun ccprev() {
    CharacterCard(
        Character(
            characterId = "TODO()",
            characterName = "TODO()",
            gender = false,
            hair = "TODO()",
            chestplate = "TODO()",
            foots = "TODO()",
            legs = "TODO()",
            background = "TODO()",
            characterType = "TODO()",
            level = 4,
            maxHp = 50,
            currentHp = 34,
            exp = 40,
            expToNextLvl = 50,
            coins = 400,
            mood = 16,
            stressCoef = 4,
            createdAt = OffsetDateTime.now(),
            deadAt = null
        )
    )
}


package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.app.Constants
import ru.mav26.vkrapp.domain.model.effects.ActiveEffect
import ru.mav26.vkrapp.presentation.theme.effectCard
import ru.mav26.vkrapp.presentation.theme.mainColor
import java.time.Duration
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun EffectCard(effect: ActiveEffect) {
    var currentTime by remember { mutableStateOf(OffsetDateTime.now()) }

    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.getDefault())
    val localDate = effect.endDate.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    val formattedDate = localDate.format(formatter)

    LaunchedEffect(Unit) {
        while (true) {
            delay(1_000)
            currentTime = OffsetDateTime.now()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(
                color = effectCard,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(10.dp)
    ) {
        Row {
            AsyncImage(
                model = Constants.BASE_URL + effect.effectIcon,
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = effect.effectName,
                    color = mainColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = effect.description,
                    color = mainColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )

                Spacer(Modifier
                    .fillMaxHeight()
                    .weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.calendar),
                        tint = mainColor.copy(alpha = 0.5f),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(Modifier.width(4.dp))

                    Text(
                        text = "Ефект закончится ${formattedDate}",
                        fontSize = 10.sp,
                        color = mainColor.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.mav26.vkrapp.app.Constants
import ru.mav26.vkrapp.domain.model.events.ActiveEvent
import ru.mav26.vkrapp.presentation.theme.backgroundColor
import ru.mav26.vkrapp.presentation.theme.eventCard
import ru.mav26.vkrapp.presentation.theme.mainColor
import java.time.OffsetDateTime

@Composable
fun EventCard(event: ActiveEvent) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(
                color = eventCard,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(10.dp)
    ) {
        Row {
            AsyncImage(
                model = Constants.BASE_URL + event.eventIcon,
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )

            Spacer(Modifier.width(4.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = event.eventName,
                    color = mainColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Text(
                    text = event.description,
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
                        val progress = if (event.criteriaValue > 0) {
                            event.progress.toFloat() / event.criteriaValue.toFloat()
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
                        text = "${event.progress}/${event.criteriaValue}",
                        color = mainColor,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}
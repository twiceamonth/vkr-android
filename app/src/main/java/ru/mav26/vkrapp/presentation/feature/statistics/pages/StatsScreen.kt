package ru.mav26.vkrapp.presentation.feature.statistics.pages

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.app.Constants
import ru.mav26.vkrapp.domain.model.character.Character
import ru.mav26.vkrapp.domain.model.statistics.AvgTimeStats
import ru.mav26.vkrapp.domain.model.statistics.DailyCount
import ru.mav26.vkrapp.domain.model.statistics.SummaryStats
import ru.mav26.vkrapp.presentation.feature.statistics.StatisticsViewModel
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.CharacterViewModel
import ru.mav26.vkrapp.presentation.theme.buttonColor
import java.time.format.DateTimeFormatter


data class StatsUiState(
    val summary: SummaryStats,
    val avgTime: AvgTimeStats,
    val daily: List<DailyCount>,
)

@Composable
fun StatisticsScreen(statsViewModel: StatisticsViewModel, characterViewModel: CharacterViewModel) {
    val state by statsViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        statsViewModel.totalByDifficulty()
        statsViewModel.avgTimeByDifficulty()
        statsViewModel.dailyCounts()
    }

    StatsScreen(
        uiState = StatsUiState(
            summary = state.summaryStats,
            avgTime = state.avgTimeStats,
            daily = state.dailyStats
        ),
        characterViewModel = characterViewModel
    )
}

@Composable
fun StatsScreen(
    uiState: StatsUiState,
    characterViewModel: CharacterViewModel,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item { SummaryCard(uiState.summary) }
        item { AvgTimeCard(uiState.avgTime) }
        item { DailyStatsChart(uiState.daily) }
        item { DifficultyHighlightRow(uiState.summary) }
        item { CharacterInfoSection(characterViewModel) }
    }
}

@Composable
fun SummaryCard(stats: SummaryStats, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val avg = stats.light + stats.medium + stats.hard
                Text("Всего задач", style = MaterialTheme.typography.titleLarge)
                Text(avg.toString(), style = MaterialTheme.typography.titleMedium)
            }

            Spacer(Modifier.height(8.dp))
            // breakdown by difficulty
            listOf(
                "Лёгкие" to stats.light,
                "Средние" to stats.medium,
                "Сложные" to stats.hard
            ).forEach { (label, count) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(label)
                    Text(count.toString(), style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun AvgTimeCard(avg: AvgTimeStats, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Среднее время выполнения (дни)", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
            listOf(
                "Лёгкие" to avg.lightDays,
                "Средние" to avg.mediumDays,
                "Сложные" to avg.hardDays
            ).forEach { (label, days) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(label)
                    Text("${"%.2f".format(days)}", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun DifficultyHighlightRow(stats: SummaryStats, modifier: Modifier = Modifier) {
    val easyColor = Color(0xFF71FF71)
    val mediumColor = Color(0xFFFDD38B)
    val hardColor = Color(0xFFFC9896)
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            DifficultyCard("Лёгкие", stats.light, easyColor)
        }
        Box(modifier = Modifier.weight(1f)) {
            DifficultyCard("Средние", stats.medium, mediumColor)
        }
        Box(modifier = Modifier.weight(1f)) {
            DifficultyCard("Сложные", stats.hard, hardColor)
        }
    }
}

@Composable
fun DifficultyCard(label: String, count: Int, bgColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                label,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                count.toString(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Composable
fun DailyStatsChart(daily: List<DailyCount>, modifier: Modifier = Modifier) {
    if (daily.isEmpty()) return

    Column(modifier = modifier.fillMaxWidth()) {
        Text("Ежедневная статистика", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        // chart frame with rounded corners
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, bottom = 40.dp, start = 40.dp, end = 16.dp)
            ) {
                val width = size.width
                val height = size.height
                val maxValue = daily.maxOf { maxOf(it.light, it.medium, it.hard) }
                    .takeIf { it > 0 }?.toFloat() ?: 1f
                val stepX = if (daily.size > 1) width / (daily.size - 1) else 0f

                // data points and lines
                val lightPoints = daily.mapIndexed { idx, v ->
                    Offset(
                        idx * stepX,
                        height * (1f - v.light / maxValue)
                    )
                }
                val mediumPoints = daily.mapIndexed { idx, v ->
                    Offset(
                        idx * stepX,
                        height * (1f - v.medium / maxValue)
                    )
                }
                val hardPoints = daily.mapIndexed { idx, v ->
                    Offset(
                        idx * stepX,
                        height * (1f - v.hard / maxValue)
                    )
                }
                val stroke = 4.dp.toPx()
                drawPoints(lightPoints, PointMode.Polygon, Color(0xFF71FF71), stroke)
                drawPoints(mediumPoints, PointMode.Polygon, Color(0xFFFDD38B), stroke)
                drawPoints(hardPoints, PointMode.Polygon, Color(0xFFFC9896), stroke)
                val radius = 5.dp.toPx()
                lightPoints.forEach { drawCircle(Color(0xFF71FF71), radius, it) }
                mediumPoints.forEach { drawCircle(Color(0xFFFDD38B), radius, it) }
                hardPoints.forEach { drawCircle(Color(0xFFFC9896), radius, it) }

                // grid and Y labels
                val textPaint = android.graphics.Paint()
                    .apply { textSize = 12.sp.toPx(); color = android.graphics.Color.DKGRAY }
                val steps = 5
                for (i in 0..steps) {
                    val y = height * i / steps
                    drawLine(
                        Color.LightGray,
                        Offset(0f, y),
                        Offset(width, y),
                        strokeWidth = 1.dp.toPx()
                    )
                    val value = ((steps - i) * maxValue / steps).toInt().toString()
                    drawContext.canvas.nativeCanvas.drawText(
                        value,
                        -24.dp.toPx(),
                        y + 4.dp.toPx(),
                        textPaint
                    )
                }

                // X-axis labels clamped
                daily.forEachIndexed { idx, v ->
                    val rawX = idx * stepX
                    val label = v.date.format(DateTimeFormatter.ofPattern("d MMM"))
                    val textWidth = textPaint.measureText(label)
                    val x = when (idx) {
                        0 -> 0f
                        daily.lastIndex -> width - textWidth
                        else -> rawX - textWidth / 2
                    }
                    drawContext.canvas.nativeCanvas.drawText(
                        label,
                        x,
                        height + 16.dp.toPx(),
                        textPaint
                    )
                }

                // axis titles
                drawContext.canvas.nativeCanvas.save()
                drawContext.canvas.nativeCanvas.rotate(-90f)
                drawContext.canvas.nativeCanvas.drawText(
                    "Количество задач",
                    -height / 2,
                    -32.dp.toPx(),
                    textPaint
                )
                drawContext.canvas.nativeCanvas.restore()
                drawContext.canvas.nativeCanvas.drawText(
                    "День месяца",
                    width / 2,
                    height + 32.dp.toPx(),
                    textPaint
                )
            }
        }
    }
}

@Composable
fun CharacterInfoSection(
    characterViewModel: CharacterViewModel,
) {
    val state by characterViewModel.state.collectAsState()
    val character = state.allCharacters.sortedBy { it.deadAt != null }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Мои персонажи", style = MaterialTheme.typography.titleMedium)
        // single character card; adapt if multiple
        character.forEach {
            CharacterInfoCard(it)
        }
    }
}

@Composable
fun CharacterInfoCard(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(
            containerColor = if (character.deadAt == null) buttonColor else buttonColor.copy(
                alpha = 0.5f
            )
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = Constants.BASE_URL + character.characterType,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(character.characterName, style = MaterialTheme.typography.titleMedium)
                Text("Уровень: ${character.level}", style = MaterialTheme.typography.bodyMedium)
                // days lived or total lived
                val daysLived = if (character.deadAt == null) {
                    java.time.Duration.between(
                        character.createdAt,
                        java.time.OffsetDateTime.now()
                    ).toDays()
                } else {
                    java.time.Duration.between(
                        character.createdAt,
                        character.deadAt
                    ).toDays()
                }
                val label = if (character.deadAt == null) "Дней в игре" else "Прожил"
                Text("$label: $daysLived", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}






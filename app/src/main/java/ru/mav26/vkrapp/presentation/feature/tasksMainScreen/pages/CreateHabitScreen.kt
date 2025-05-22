package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.domain.model.task.HabitCreate
import ru.mav26.vkrapp.presentation.components.CustomTextField
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.FieldType
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.SelectTextOption
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.TaskViewModel
import ru.mav26.vkrapp.presentation.theme.backgroundColor
import ru.mav26.vkrapp.presentation.theme.buttonColor
import ru.mav26.vkrapp.presentation.theme.easyCard
import ru.mav26.vkrapp.presentation.theme.hardCard
import ru.mav26.vkrapp.presentation.theme.mainColor
import ru.mav26.vkrapp.presentation.theme.mediumCard
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateHabitScreen(
    taskViewModel: TaskViewModel,
    onBack: () -> Unit
) {
    var habitTitle by remember { mutableStateOf("") }
    var habitDescription by remember { mutableStateOf("") }
    var habitTimer by remember { mutableStateOf<LocalTime?>(null) }

    val diffList = listOf(
        stringResource(R.string.lowText),
        stringResource(R.string.mediumText),
        stringResource(R.string.highText)
    )
    var selectedDiff by remember { mutableStateOf(diffList[0]) }

    val repList = listOf(
        stringResource(R.string.daylyA),
        stringResource(R.string.weeklyA),
        stringResource(R.string.monthlyA)
    )
    var selectedRep by remember { mutableStateOf(repList[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                modifier = Modifier,
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(R.drawable.back),
                            modifier = Modifier.size(36.dp),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    TextButton(onClick = {
                        val diff = when(selectedDiff) {
                            diffList[0] -> "low"
                            diffList[1] -> "medium"
                            diffList[2] -> "high"
                            else -> ""
                        }

                        val freq = when(selectedRep) {
                            repList[0] -> "day"
                            repList[1] -> "week"
                            repList[2] -> "month"
                            else -> ""
                        }

                        taskViewModel.createHabit(HabitCreate(
                            title = habitTitle,
                            difficulty = diff,
                            frequency = freq,
                            timerInterval = if(habitTimer != null) habitTimer.toString() else null,
                            description = habitDescription
                        ))
                    }) {
                        Text(
                            text = stringResource(R.string.save),
                            color = backgroundColor,
                            fontSize = 16.sp
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = mainColor,
                    scrolledContainerColor = backgroundColor,
                    navigationIconContentColor = backgroundColor,
                    titleContentColor = backgroundColor,
                    actionIconContentColor = backgroundColor
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            CustomTextField(
                value = habitTitle,
                onValueChange = { habitTitle = it },
                label = stringResource(R.string.habitName),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            CustomTextField(
                value = habitDescription,
                onValueChange = { habitDescription = it },
                label = stringResource(R.string.description),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.difficulty),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                diffList.forEach { diff ->
                    val color = when (diff) {
                        diffList[0] -> easyCard
                        diffList[1] -> mediumCard
                        diffList[2] -> hardCard
                        else -> buttonColor
                    }

                    SelectTextOption(
                        text = diff,
                        color = color,
                        option = diff,
                        isSelected = diff == selectedDiff,
                        onSelected = { selectedDiff = it }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.repeatTask),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                repList.forEach { rep ->
                    SelectTextOption(
                        text = rep,
                        color = buttonColor,
                        option = rep,
                        isSelected = rep == selectedRep,
                        onSelected = { selectedRep = it }
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = stringResource(R.string.taskTimer),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                if (habitTimer != null) {
                    TextButton(
                        contentPadding = ButtonDefaults.ContentPadding.apply { 0.dp },
                        onClick = { habitTimer = null }
                    ) {
                        Text(
                            text = stringResource(R.string.cansel),
                            fontSize = 16.sp,
                            color = mainColor,
                            fontWeight = FontWeight.W300
                        )
                    }
                }
            }
        }
    }
}
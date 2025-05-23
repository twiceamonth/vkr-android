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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.client.HttpClient
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.data.repository.TaskRepositoryImpl
import ru.mav26.vkrapp.domain.model.task.DetailsCreate
import ru.mav26.vkrapp.domain.model.task.TaskCreate
import ru.mav26.vkrapp.domain.usecase.TaskUseCase
import ru.mav26.vkrapp.presentation.components.CustomTextField
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.FieldType
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.LinksEditor
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.PrioritySelect
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.SelectTextOption
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.SubtasksEditor
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.TaskViewModel
import ru.mav26.vkrapp.presentation.theme.backgroundColor
import ru.mav26.vkrapp.presentation.theme.buttonColor
import ru.mav26.vkrapp.presentation.theme.easyCard
import ru.mav26.vkrapp.presentation.theme.hardCard
import ru.mav26.vkrapp.presentation.theme.mainColor
import ru.mav26.vkrapp.presentation.theme.mediumCard
import java.time.LocalTime
import java.time.OffsetDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    taskViewModel: TaskViewModel,
    onBack: () -> Unit,
) {
    val scroll = rememberScrollState()

    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var taskTimer by remember { mutableStateOf<LocalTime?>(null) }
    var taskEndTime by remember { mutableStateOf<OffsetDateTime?>(null) }
    var subtasksList by remember { mutableStateOf<List<String>>(emptyList()) }
    var linksList by remember { mutableStateOf<List<DetailsCreate>>(emptyList()) }

    val diffList = listOf(
        stringResource(R.string.lowText),
        stringResource(R.string.mediumText),
        stringResource(R.string.highText)
    )
    var selectedDiff by remember { mutableStateOf(diffList[0]) }

    val prioList = listOf(
        stringResource(R.string.lowPrio),
        stringResource(R.string.mediumPrio),
        stringResource(R.string.highPrio)
    )
    var selectedPrio by remember { mutableStateOf(prioList[0]) }

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
                        val diff = when (selectedDiff) {
                            diffList[0] -> "low"
                            diffList[1] -> "medium"
                            diffList[2] -> "high"
                            else -> ""
                        }

                        val prio = when (selectedPrio) {
                            prioList[0] -> "low"
                            prioList[1] -> "medium"
                            prioList[2] -> "high"
                            else -> ""
                        }

                        subtasksList = subtasksList.filter { it != "" }

                        taskViewModel.createTask(
                            TaskCreate(
                                title = taskTitle,
                                endTime = taskEndTime,
                                difficulty = diff,
                                priority = prio,
                                frequency = "none",
                                details = linksList,
                                timerInterval = taskTimer,
                                description = taskDescription,
                                subtasks = subtasksList
                            )
                        )
                        onBack()
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
                .verticalScroll(scroll)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            CustomTextField(
                value = taskTitle,
                onValueChange = { taskTitle = it },
                label = stringResource(R.string.taskName),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            CustomTextField(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                label = stringResource(R.string.description),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.subtasks),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(Modifier.height(8.dp))

            SubtasksEditor(
                subtasksList = subtasksList,
                onListChange = { subtasksList = it }
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
                text = stringResource(R.string.priority),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                prioList.forEach { prio ->
                    val icon = when (prio) {
                        prioList[0] -> stringResource(R.string.low)
                        prioList[1] -> stringResource(R.string.medium)
                        prioList[2] -> stringResource(R.string.high)
                        else -> ""
                    }

                    PrioritySelect(
                        text = prio,
                        icon = icon,
                        option = prio,
                        isSelected = prio == selectedPrio,
                        onSelect = { selectedPrio = it }
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
                    text = stringResource(R.string.doUntil),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                if (taskEndTime != null) {
                    TextButton(
                        contentPadding = ButtonDefaults.ContentPadding.apply { 0.dp },
                        onClick = { taskEndTime = null }
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

            CustomTextField(
                value = taskEndTime,
                onValueChange = { taskEndTime = it },
                label = stringResource(R.string.endTime),
                fieldType = FieldType.Date,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.links),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(Modifier.height(8.dp))

            LinksEditor(
                linksList = linksList,
                onListChange = { linksList = it }
            )

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

                if (taskTimer != null) {
                    TextButton(
                        contentPadding = ButtonDefaults.ContentPadding.apply { 0.dp },
                        onClick = { taskTimer = null }
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

            CustomTextField(
                value = taskTimer,
                label = stringResource(R.string.taskTimer),
                onValueChange = { taskTimer = it },
                fieldType = FieldType.Time,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

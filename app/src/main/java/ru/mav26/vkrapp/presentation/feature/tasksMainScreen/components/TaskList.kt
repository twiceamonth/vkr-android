package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mav26.vkrapp.domain.model.task.SubtaskEdit
import ru.mav26.vkrapp.domain.model.task.TaskEdit
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.TaskViewModel
import java.time.OffsetDateTime

@Composable
fun TaskList(
    taskViewModel: TaskViewModel,
    modifier: Modifier = Modifier,
) {
    val state by taskViewModel.state.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(state.tasks) { task ->
            TaskCard(
                task = task,
                onStatusChange = {
                    taskViewModel.editTask(
                        TaskEdit(
                            status = it,
                            finishedAt = if(it) OffsetDateTime.now() else null
                        ), task.taskId
                    )
                    taskViewModel.getTasks()
                },
                onSubStatusChange = { isChecked, subtaskId ->
                    taskViewModel.editSubtask(SubtaskEdit(status = isChecked), subtaskId)
                    taskViewModel.getTasks()
                },
                onTimerStart = {
                    /*TODO*/
                },
                onCardClick = { /*TODO*/ }
            )

            Spacer(Modifier.height(12.dp))
        }
    }
}
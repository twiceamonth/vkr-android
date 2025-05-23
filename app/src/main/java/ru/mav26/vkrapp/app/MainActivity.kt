package ru.mav26.vkrapp.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.koinInject
import ru.mav26.vkrapp.app.navigation.AppNavigation
import ru.mav26.vkrapp.domain.model.task.HabitEdit
import ru.mav26.vkrapp.domain.model.task.TaskEdit
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.TaskViewModel
import ru.mav26.vkrapp.presentation.theme.VkrappTheme
import java.time.OffsetDateTime

class MainActivity : ComponentActivity() {
    private val taskViewModel: TaskViewModel by viewModel()

    val timerReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val isTask = intent?.getBooleanExtra("is_task", true) ?: true
            val itemId = intent?.getStringExtra("item_id") ?: return

            if (isTask) {
                taskViewModel.editTask(
                    TaskEdit(status = true, finishedAt = OffsetDateTime.now()),
                    itemId
                )
            } else {
                taskViewModel.finishHabitFromTimer(itemId)
            }

            // И можно обновить списки
            taskViewModel.getTasks()
        }
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter("ru.mav26.vkrapp.TIMER_FINISHED").apply {
            // Указываем, что ресивер НЕ экспортирован (т.е. принимает только локальные события)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY)
            }
        }
        registerReceiver(timerReceiver, intentFilter, Context.RECEIVER_NOT_EXPORTED)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(timerReceiver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VkrappTheme {
                AppNavigation(
                    navController = rememberNavController(),
                    tokenManager = koinInject(),
                    authViewModel = koinViewModel(),
                    createCharacterViewModel = koinViewModel(),
                    characterViewModel = koinViewModel(),
                    taskViewModel = taskViewModel,
                    activitiesViewModel = koinViewModel(),
                    storeViewModel = koinViewModel(),
                    achievementsViewModel = koinViewModel(),
                    statisticsViewModel = koinViewModel(),
                    inventoryViewModel = koinViewModel()
                )
            }
        }
    }
}
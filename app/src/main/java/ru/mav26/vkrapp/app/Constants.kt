package ru.mav26.vkrapp.app

import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.NavTab

object Constants {
    const val BASE_URL = "http://127.0.0.1:8080"

    /*          Bottom Navigation Panel         */

    val topTabs = listOf(
        NavTab("Задачи", R.drawable.task_list, Tabs.TASKS),
        NavTab("Привычки", R.drawable.calendar, Tabs.HABITS)
    )

    val topTabs2 = listOf(
        NavTab("Трофеи", R.drawable.trophy, Tabs.ACHIEVEMENTS),
        NavTab("Статистика", R.drawable.statistics, Tabs.STATISTICS)
    )

    val bottomTabs = listOf(
        NavTab("Задания", R.drawable.task_tab, Tabs.ASSIGNMENTS),
        NavTab("Инвентарь", R.drawable.work, Tabs.INVENTORY)
    )

    object Tabs {
        const val TASKS = "tasks"
        const val HABITS = "habits"
        const val ASSIGNMENTS = "assignments"
        const val INVENTORY = "inventory"
        const val STATISTICS = "statistics"
        const val ACHIEVEMENTS = "achievements"
    }

    /*          Tasks and Habits properties          */

    const val DIFFICULTY_EASY = "easy"
    const val DIFFICULTY_MEDIUM = "medium"
    const val DIFFICULTY_HARD = "hard"

    const val PRIORITY_LOW = "low"
    const val PRIORITY_MEDIUM = "medium"
    const val PRIORITY_HIGH = "high"

    const val FREQUENCY_DAILY = "day"
    const val FREQUENCY_WEEKLY = "week"
    const val FREQUENCY_MONTHLY = "month"
}
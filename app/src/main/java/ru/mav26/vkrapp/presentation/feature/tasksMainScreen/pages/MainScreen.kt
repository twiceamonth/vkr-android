package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.app.Constants
import ru.mav26.vkrapp.domain.model.task.Habit
import ru.mav26.vkrapp.domain.model.task.Task
import ru.mav26.vkrapp.presentation.feature.achievements.AchievementsViewModel
import ru.mav26.vkrapp.presentation.feature.achievements.pages.AchievementsScreen
import ru.mav26.vkrapp.presentation.feature.inventory.InventoryViewModel
import ru.mav26.vkrapp.presentation.feature.inventory.pages.InventoryScreen
import ru.mav26.vkrapp.presentation.feature.statistics.StatisticsViewModel
import ru.mav26.vkrapp.presentation.feature.statistics.pages.StatisticsScreen
import ru.mav26.vkrapp.presentation.feature.store.StoreViewModel
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.NavTab
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.BossCard
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.BottomNavBar
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.CharacterCard
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.EffectCard
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.EventCard
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.HabitList
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components.TaskList
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.ActivitiesViewModel
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.CharacterViewModel
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.TaskViewModel
import ru.mav26.vkrapp.presentation.theme.backgroundColor
import ru.mav26.vkrapp.presentation.theme.mainColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    activityViewModel: ActivitiesViewModel,
    characterViewModel: CharacterViewModel,
    taskViewModel: TaskViewModel,
    achievementsViewModel: AchievementsViewModel,
    storeViewModel: StoreViewModel,
    inventoryViewModel: InventoryViewModel,
    statisticsViewModel: StatisticsViewModel,
    onAddBtn: (NavTab) -> Unit,
    onLogout: () -> Unit,
) {
    val activityState by activityViewModel.state.collectAsState()
    val characterState by characterViewModel.state.collectAsState()
    val taskState by taskViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        taskViewModel.getTasks()
        taskViewModel.getHabits()
        activityViewModel.getActiveBoss()
        activityViewModel.getActiveEffect()
        activityViewModel.getActiveEvent()
    }

    var selectedMainTab by remember { mutableStateOf<NavTab>(Constants.bottomTabs[0]) }
    var selectedTab by remember { mutableStateOf<NavTab>(Constants.topTabs[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    val name =
                        if (characterState.character != null) characterState.character!!.characterName else "Имя персонажа"
                    Text(
                        text = name,
                        fontSize = 16.sp,
                        color = backgroundColor
                    )
                },
                colors = TopAppBarColors(
                    containerColor = mainColor,
                    scrolledContainerColor = backgroundColor,
                    navigationIconContentColor = backgroundColor,
                    titleContentColor = backgroundColor,
                    actionIconContentColor = backgroundColor
                ),
                actions = {
                    IconButton(onClick = {/*TODO: сделать скритие/показ выполненных задач*/ }) {
                        Icon(
                            painter = painterResource(R.drawable.filter),
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = onLogout) {
                        Icon(
                            painter = painterResource(R.drawable.logout),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            Box(
                Modifier.padding(16.dp)
            ) {
                BottomNavBar(
                    topTabs = Constants.topTabs,
                    topTabs2 = Constants.topTabs2,
                    bottomTabs = Constants.bottomTabs,
                    selectedTab = selectedTab,
                    selectedBottomTab = selectedMainTab,
                    onTabSelected = {
                        when (it.id) {
                            Constants.Tabs.ASSIGNMENTS -> {
                                selectedMainTab = it
                                selectedTab = Constants.topTabs[0] // "Задачи"
                            }

                            Constants.Tabs.TASKS, Constants.Tabs.HABITS -> {
                                selectedTab = it
                                selectedMainTab =
                                    Constants.bottomTabs[0] // "Задания" всегда выбрана
                            }

                            else -> {
                                selectedTab = it
                                selectedMainTab = it
                            }
                        }
                    },
                    onCenterClick = { onAddBtn(it) }
                )
            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = (innerPadding.calculateBottomPadding() - 24.dp)
                )
        ) {
            when {
                selectedMainTab.id == Constants.Tabs.INVENTORY -> {
                    InventoryScreen(
                        character = characterState.character!!,
                        onBuyHp = { inventoryViewModel.heal(characterState.character!!.characterId) },
                        padding = innerPadding,
                        storeViewModel = storeViewModel
                    )
                }

                selectedMainTab.id == Constants.Tabs.ASSIGNMENTS -> {
                    Column {
                        if (selectedTab == Constants.topTabs[0] || selectedTab == Constants.topTabs[1]) {
                            CharacterCard(character = characterState.character!!)
                        }
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            contentPadding = PaddingValues(vertical = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            activityState.boss?.let {
                                item {
                                    BossCard(boss = it)
                                }
                            }

                            activityState.event?.let {
                                item {
                                    EventCard(event = it)
                                }
                            }

                            activityState.effect?.let {
                                item {
                                    EffectCard(effect = it)
                                }
                            }

                            if (selectedTab == Constants.topTabs[0]) {
                                items(taskState.tasks) { task ->
                                    TaskList(taskViewModel = taskViewModel, task = task)
                                }
                            } else if (selectedTab == Constants.topTabs[1]) {
                                items(taskState.habits) { habit ->
                                    HabitList(taskViewModel = taskViewModel, habit = habit)
                                }
                            }
                        }
                    }
                }

                selectedMainTab.id == Constants.Tabs.ACHIEVEMENTS -> {
                    AchievementsScreen(
                        achievementsViewModel = achievementsViewModel
                    )
                }

                selectedMainTab.id == Constants.Tabs.STATISTICS -> {
                    characterViewModel.getAllCharacters()
                    StatisticsScreen(
                        statsViewModel = statisticsViewModel,
                        characterViewModel = characterViewModel
                    )
                }
            }
        }
    }
}
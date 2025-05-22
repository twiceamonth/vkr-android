package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    onAddBtn: (NavTab) -> Unit,
    onLogout: () -> Unit,
) {
    val activityState by activityViewModel.state.collectAsState()
    val characterState by characterViewModel.state.collectAsState()
    val taskState by taskViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        taskViewModel.getTasks()
        taskViewModel.getHabits()
    }

    var selected by remember { mutableStateOf<NavTab>(Constants.bottomTabs[0]) }

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
                    selectedTab = selected,
                    onTabSelected = { selected = it },
                    onCenterClick = { onAddBtn(it) }
                )
            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {
                CharacterCard(character = characterState.character!!)

                if (activityState.boss != null) {
                    Spacer(Modifier.height(12.dp))
                    BossCard(boss = activityState.boss!!)
                }

                if (activityState.event != null) {
                    Spacer(Modifier.height(12.dp))
                    EventCard(event = activityState.event!!)
                }

                if (activityState.effect != null) {
                    Spacer(Modifier.height(12.dp))
                    EffectCard(effect = activityState.effect!!)
                }

                if (selected == Constants.topTabs[0]) {
                    TaskList(taskViewModel)
                } else if (selected == Constants.topTabs[1]) {
                    HabitList(taskViewModel)
                }
            }
        }
    }
}
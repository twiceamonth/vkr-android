package ru.mav26.vkrapp.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.mav26.vkrapp.app.Constants
import ru.mav26.vkrapp.data.local.TokenDataStoreManager
import ru.mav26.vkrapp.presentation.feature.auth.AuthViewModel
import ru.mav26.vkrapp.presentation.feature.auth.pages.LoginPage
import ru.mav26.vkrapp.presentation.feature.auth.pages.RegisterPage
import ru.mav26.vkrapp.presentation.feature.auth.pages.StartPage
import ru.mav26.vkrapp.presentation.feature.createCharacter.CreateCharacterViewModel
import ru.mav26.vkrapp.presentation.feature.createCharacter.pages.NamePage
import ru.mav26.vkrapp.presentation.feature.createCharacter.pages.TypePage
import ru.mav26.vkrapp.presentation.feature.store.StoreViewModel
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.pages.CreateHabitScreen
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.pages.CreateTaskScreen
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.pages.MainScreen
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.ActivitiesViewModel
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.CharacterViewModel
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.TaskViewModel
import ru.mav26.vkrapp.presentation.theme.mainColor

@Composable
fun AppNavigation(
    tokenManager: TokenDataStoreManager,
    authViewModel: AuthViewModel,
    createCharacterViewModel: CreateCharacterViewModel,
    characterViewModel: CharacterViewModel,
    taskViewModel: TaskViewModel,
    activitiesViewModel: ActivitiesViewModel,
    storeViewModel: StoreViewModel,
    navController: NavHostController
) {
    val state2 by characterViewModel.state.collectAsState()


    NavHost(navController = navController, startDestination = NavRoutes.INITIAL_SCREEN) {
        composable(NavRoutes.SPLASH_SCREEN) {
            val accessToken by tokenManager.accessTokenFlow.collectAsState(initial = null)
            var hasStartedLoading by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier.background(color = mainColor).fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
                hasStartedLoading = true
            }

            LaunchedEffect(accessToken, state2.character) {
                if(!accessToken.isNullOrBlank()) {
                    characterViewModel.getCharacter()
                    if(hasStartedLoading) {
                        when {
                            state2.character != null -> {
                                navController.navigate(NavRoutes.MAIN_SCREEN) {
                                    popUpTo(0) { inclusive = true }
                                }
                                hasStartedLoading = false
                            }
                        }
                    }
                } else {
                    navController.navigate(NavRoutes.INITIAL_SCREEN) {
                        popUpTo(0)
                    }
                }
            }
        }


        composable(NavRoutes.INITIAL_SCREEN) {
            StartPage(
                onRegister = {
                    navController.navigate(NavRoutes.REGISTER_SCREEN) {
                        popUpTo(NavRoutes.INITIAL_SCREEN) { inclusive = true }
                    }
                },
                onLogin = {
                    navController.navigate(NavRoutes.LOGIN_SCREEN) {
                        popUpTo(NavRoutes.INITIAL_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.LOGIN_SCREEN) {
            var hasStartedLoading by remember { mutableStateOf(false) }

            LoginPage(
                authViewModel = authViewModel,
                onBack = {
                    navController.navigate(NavRoutes.INITIAL_SCREEN) {
                        popUpTo(NavRoutes.LOGIN_SCREEN) { inclusive = true }
                    }
                },
                onAuth = {
                    characterViewModel.getCharacter()
                    hasStartedLoading = true
                }
            )

            LaunchedEffect(state2.isLoading, state2.character) {
                if(hasStartedLoading) {
                    when {
                        state2.character != null -> {
                            navController.navigate(NavRoutes.MAIN_SCREEN) {
                                popUpTo(NavRoutes.TYPE_CHAR_SCREEN) { inclusive = true }
                            }
                            hasStartedLoading = false
                        }
                    }
                }
            }
        }

        composable(NavRoutes.REGISTER_SCREEN) {
            RegisterPage(
                authViewModel = authViewModel,
                onBack = {
                    navController.navigate(NavRoutes.INITIAL_SCREEN) {
                        popUpTo(NavRoutes.REGISTER_SCREEN) { inclusive = true }
                    }
                },
                onRegister = {
                    navController.navigate(NavRoutes.NAME_CHAR_SCREEN) {
                        popUpTo(NavRoutes.REGISTER_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.NAME_CHAR_SCREEN) {
            var hasStartedLoading by remember { mutableStateOf(false) }
            val state by createCharacterViewModel.state.collectAsState()

            NamePage(
                createCharacterViewModel = createCharacterViewModel,
                onNextClick = {
                    createCharacterViewModel.getTypes()
                    hasStartedLoading = true
                }
            )

            LaunchedEffect(state.isLoading, state.types) {
                if(hasStartedLoading) {
                    when {
                        state.types.isNotEmpty() -> {
                            navController.navigate(NavRoutes.TYPE_CHAR_SCREEN) {
                                popUpTo(NavRoutes.NAME_CHAR_SCREEN) { inclusive = true }
                            }
                            hasStartedLoading = false
                        }
                    }
                }
            }
        }

        composable(NavRoutes.TYPE_CHAR_SCREEN) {
            val state by createCharacterViewModel.state.collectAsState()
            var hasStartedLoading by remember { mutableStateOf(false) }

            TypePage(
                createCharacterViewModel = createCharacterViewModel,
                onContinue = {
                    when(state.isLoading) {
                        true -> {}
                        false -> {
                            characterViewModel.getCharacter()
                            hasStartedLoading = true
                        }
                    }
                }
            )

            LaunchedEffect(state2.isLoading, state2.character) {
                if(hasStartedLoading) {
                    when {
                        state2.character != null -> {
                            navController.navigate(NavRoutes.MAIN_SCREEN) {
                                popUpTo(NavRoutes.TYPE_CHAR_SCREEN) { inclusive = true }
                            }
                            hasStartedLoading = false
                        }
                    }
                }
            }
        }

        composable(NavRoutes.MAIN_SCREEN) {
            MainScreen(
                activityViewModel = activitiesViewModel,
                characterViewModel = characterViewModel,
                taskViewModel = taskViewModel,
                onAddBtn = { tab ->
                    when (tab.id) {
                        Constants.Tabs.TASKS -> navController.navigate(NavRoutes.CREATE_TASK) {
                            popUpTo(NavRoutes.MAIN_SCREEN)
                        }
                        Constants.Tabs.HABITS -> navController.navigate(NavRoutes.CREATE_HABIT) {
                            popUpTo(NavRoutes.MAIN_SCREEN)
                        }
                    }
                },
                onLogout = {
                    tokenManager.clearTokens()
                },
                storeViewModel = storeViewModel
            )
        }

        composable(NavRoutes.CREATE_HABIT) {
            CreateHabitScreen(
                taskViewModel = taskViewModel,
                onBack = {
                    navController.navigate(NavRoutes.MAIN_SCREEN) {
                        popUpTo(NavRoutes.CREATE_HABIT) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.CREATE_TASK) {
            CreateTaskScreen(
                taskViewModel = taskViewModel,
                onBack = {
                    navController.navigate(NavRoutes.MAIN_SCREEN) {
                        popUpTo(NavRoutes.CREATE_TASK) { inclusive = true }
                    }
                }
            )
        }
    }
}
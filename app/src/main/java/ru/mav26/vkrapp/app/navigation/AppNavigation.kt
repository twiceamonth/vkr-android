package ru.mav26.vkrapp.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import ru.mav26.vkrapp.presentation.feature.auth.pages.LoginPage
import ru.mav26.vkrapp.presentation.feature.auth.pages.RegisterPage
import ru.mav26.vkrapp.presentation.feature.auth.pages.StartPage
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.pages.CreateHabitScreen
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.pages.CreateTaskScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination =NavRoutes.CREATE_HABIT /*NavRoutes.INITIAL_SCREEN*/) {
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
            LoginPage(
                authViewModel = koinViewModel(),
                onBack = {
                    navController.navigate(NavRoutes.INITIAL_SCREEN) {
                        popUpTo(NavRoutes.LOGIN_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.REGISTER_SCREEN) {
            RegisterPage(
                authViewModel = koinViewModel(),
                onBack = {
                    navController.navigate(NavRoutes.INITIAL_SCREEN) {
                        popUpTo(NavRoutes.REGISTER_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.CREATE_HABIT) {
            CreateHabitScreen(
                taskViewModel = koinViewModel(),
                onBack = {
                    navController.navigate(NavRoutes.MAIN_SCREEN) {
                        popUpTo(NavRoutes.CREATE_HABIT) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.CREATE_TASK) {
            CreateTaskScreen(
                taskViewModel = koinViewModel(),
                onBack = {
                    navController.navigate(NavRoutes.MAIN_SCREEN) {
                        popUpTo(NavRoutes.CREATE_TASK) { inclusive = true }
                    }
                }
            )
        }
    }
}
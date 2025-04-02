package ru.mav26.vkrapp.presentation.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mav26.vkrapp.domain.model.auth.AuthRequest
import ru.mav26.vkrapp.domain.usecase.AuthUseCase

class AuthViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    fun login(authRequest: AuthRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.login(authRequest)
        }
    }

    fun register(authRequest: AuthRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.register(authRequest)
        }
    }

    fun refresh(refreshToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.refresh(refreshToken)
        }
    }
}
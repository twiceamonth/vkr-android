package ru.mav26.vkrapp.presentation.feature.auth.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.presentation.feature.auth.components.AuthButton
import ru.mav26.vkrapp.presentation.components.AuthTextField
import ru.mav26.vkrapp.presentation.feature.auth.AuthViewModel

@Composable
fun LoginPage(
    authViewModel: AuthViewModel,
    onBack: () -> Unit
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF042A2B))
            .padding(16.dp, 32.dp)
    ) {

        /* TODO: КАРТИНКА */

        AuthTextField(
            value = login,
            label = stringResource(R.string.loginLabel),
            onValueChange = { login = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        AuthTextField(
            value = password,
            label = stringResource(R.string.passwordLabel),
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 52.dp)
        )

        AuthButton(
            text = stringResource(R.string.login),
            modifier = Modifier.width(164.dp).padding(bottom = 2.dp)
        ) { authViewModel.login(login, password) }

        TextButton(onClick = onBack) {
            Text(
                text = stringResource(R.string.back),
                fontSize = 16.sp,
                lineHeight = 19.sp,
                color = Color(0xFFD0ECF6)
            )
        }
    }
}
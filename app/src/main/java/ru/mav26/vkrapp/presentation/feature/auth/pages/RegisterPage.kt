package ru.mav26.vkrapp.presentation.feature.auth.pages

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.presentation.feature.auth.components.AuthButton
import ru.mav26.vkrapp.presentation.components.CustomTextField
import ru.mav26.vkrapp.presentation.feature.auth.AuthViewModel

@Composable
fun RegisterPage(
    authViewModel: AuthViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordAgain by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF042A2B))
            .padding(16.dp)
    ) {
        /*TODO КАРТИНКА*/

        CustomTextField(
            value = login,
            label = stringResource(R.string.loginLabel),
            onValueChange = { login = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        CustomTextField(
            value = password,
            label = stringResource(R.string.passwordLabel),
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        CustomTextField(
            value = passwordAgain,
            label = stringResource(R.string.passwordAgainLabel),
            onValueChange = { passwordAgain = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 52.dp)
        )

        AuthButton(
            text = stringResource(R.string.register),
            modifier = Modifier.width(200.dp).padding(bottom = 2.dp)
        ) {
            if (password != passwordAgain) {
                Toast.makeText(context, R.string.toastPassword, Toast.LENGTH_SHORT).show()
                return@AuthButton
            }
            authViewModel.register(login, password)
        }

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
package ru.mav26.vkrapp.presentation.feature.auth.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.presentation.feature.auth.components.AuthButton

@Composable
fun StartPage(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF042A2B))
            .padding(16.dp, 32.dp)
    ) {

        /* TODO: КАРТИКА КАКАЙЯ-НИБУДЬ*/

        AuthButton(
            text = stringResource(R.string.register),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) { /*TODO*/ }

        AuthButton(
            text = stringResource(R.string.login),
            isFilled = false,
            modifier = Modifier.fillMaxWidth()
        ) { /*TODO*/ }
    }
}

@Preview
@Composable
private fun StartPagePreview() {
    StartPage()
}
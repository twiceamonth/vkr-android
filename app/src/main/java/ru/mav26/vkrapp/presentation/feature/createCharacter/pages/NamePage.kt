package ru.mav26.vkrapp.presentation.feature.createCharacter.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.presentation.feature.auth.components.AuthButton
import ru.mav26.vkrapp.presentation.components.CustomTextField
import ru.mav26.vkrapp.presentation.feature.createCharacter.CreateCharacterViewModel

@Composable
fun NamePage(createCharacterViewModel: CreateCharacterViewModel, modifier: Modifier = Modifier) {
    val radioOptions = listOf(stringResource(R.string.male), stringResource(R.string.female))
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    val state by createCharacterViewModel.state.collectAsState()

    var characterName by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF042A2B))
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.namePageTitle),
            fontSize = 24.sp,
            lineHeight = 29.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFFD0ECF6),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 78.dp)
        )

        CustomTextField(
            value = characterName,
            onValueChange = {
                characterName = it
                createCharacterViewModel.changeName(characterName)
            },
            label = stringResource(R.string.characterNameLabel),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(bottom = 80.dp)
                .fillMaxWidth()
        ) {
            radioOptions.forEach { text ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(50.dp)
                        .width(100.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                                when (text) {
                                    "Муж" -> createCharacterViewModel.changeGender(true)
                                    "Жен" -> createCharacterViewModel.changeGender(false)
                                }

                            },
                            role = Role.RadioButton
                        )
                        .background(
                            color = if (text == selectedOption) Color(0xFF95A9CE)
                            else Color(0xFF042A2B),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFF95A9CE),
                            shape = RoundedCornerShape(5.dp)
                        )
                ) {
                    Text(
                        text = text,
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        fontWeight = FontWeight(400),
                        color = if (text == selectedOption) Color(0xFF042A2B)
                        else Color(0xFF95A9CE)
                    )
                }
            }
        }

        AuthButton(
            text = stringResource(R.string.next)
        ) { /*TODO*/ }

    }
}

@Preview
@Composable
private fun NPrev() {
    //NamePage()
}
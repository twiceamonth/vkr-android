package ru.mav26.vkrapp.presentation.feature.createCharacter.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.domain.model.character.CharacterType
import ru.mav26.vkrapp.presentation.feature.createCharacter.CreateCharacterViewModel

@Composable
fun TypePage(
    createCharacterViewModel: CreateCharacterViewModel,
    modifier: Modifier = Modifier,
) {
    val state by createCharacterViewModel.state.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<CharacterType?>(null) }

    if (showDialog && selectedItem != null) {
        openDialog(
            type = selectedItem!!,
            onContinue = {
                createCharacterViewModel.changeType(selectedItem!!.characterType)
                createCharacterViewModel.createCharacter()
            },
            onDismiss = {
                showDialog = false
                selectedItem = null
            }
        )
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF042A2B))
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.typePageTitle),
            fontSize = 24.sp,
            lineHeight = 29.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFFD0ECF6),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 78.dp)
        )

        Row {
            Box(
                Modifier
                    .size(100.dp)
                    .background(color = Color.Cyan, shape = RoundedCornerShape(5.dp))
                    .clickable {
                        selectedItem = state.types[0]
                        showDialog = true
                    }) {
                AsyncImage(
                    model = state.types[0].imagePath,
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                )
            }

            Spacer(Modifier.width(40.dp))

            Box(
                Modifier
                    .size(100.dp)
                    .background(color = Color.Cyan, shape = RoundedCornerShape(5.dp))
                    .clickable {
                        selectedItem = state.types[1]
                        showDialog = true
                    }) {
                AsyncImage(
                    model = state.types[1].imagePath,
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                )
            }
        }

        Spacer(Modifier.height(40.dp))

        Row {
            Box(
                Modifier
                    .size(100.dp)
                    .background(color = Color.Cyan, shape = RoundedCornerShape(5.dp))
                    .clickable {
                        selectedItem = state.types[2]
                        showDialog = true
                    }) {
                AsyncImage(
                    model = state.types[2].imagePath,
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null
                )
            }

            Spacer(Modifier.width(40.dp))

            Box(
                Modifier
                    .size(100.dp)
                    .background(color = Color.Cyan, shape = RoundedCornerShape(5.dp))
                    .clickable {
                        selectedItem = state.types[3]
                        showDialog = true
                    }) {
                AsyncImage(
                    model = state.types[3].imagePath,
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                )
            }
        }
    }
}


@Composable
fun openDialog(
    type: CharacterType,
    onContinue: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = type.imagePath,
                    contentDescription = null,
                    modifier = Modifier.height(100.dp)
                )

                Text(
                    text = type.characterType,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF042A2B),
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = type.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF042A2B),
                    modifier = Modifier
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onContinue
                    ) {
                        Text(
                            text = stringResource(R.string.choose),
                            fontSize = 16.sp,
                            color = Color(0xFF042A2B)
                        )
                    }

                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(
                            text = stringResource(R.string.cansel),
                            fontSize = 16.sp,
                            color = Color(0xFF042A2B)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TPrev() {
    //TypePage()
}
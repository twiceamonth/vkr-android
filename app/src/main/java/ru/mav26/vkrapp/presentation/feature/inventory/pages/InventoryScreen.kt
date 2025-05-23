package ru.mav26.vkrapp.presentation.feature.inventory.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.domain.model.character.Character
import ru.mav26.vkrapp.domain.model.store.StoreItem
import ru.mav26.vkrapp.presentation.feature.inventory.InventoryUiState
import ru.mav26.vkrapp.presentation.feature.inventory.InventoryViewModel
import ru.mav26.vkrapp.presentation.feature.inventory.components.CharacterBigCard
import ru.mav26.vkrapp.presentation.feature.inventory.components.InventoryItems
import ru.mav26.vkrapp.presentation.feature.inventory.components.StoreItem
import ru.mav26.vkrapp.presentation.feature.store.StoreViewModel
import ru.mav26.vkrapp.presentation.theme.buttonColor
import ru.mav26.vkrapp.presentation.theme.mainColor

@Composable
fun InventoryScreen(
    storeViewModel: StoreViewModel,
    onBuyHp: () -> Unit,
    character: Character,
    padding: PaddingValues,
) {
    val uiState = remember { mutableStateOf<InventoryUiState>(InventoryUiState.ItemList) }
    val storeState by storeViewModel.state.collectAsState()
    var selectedItem by remember { mutableStateOf<StoreItem?>(null) }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize().padding(bottom = 24.dp)
    ) {
        CharacterBigCard(character)

        Column {
            when (val state = uiState.value) {
                is InventoryUiState.ItemList -> {
                    InventoryItems(character) { itemType ->
                        uiState.value = InventoryUiState.ItemType(itemType)
                    }

                    Button(
                        onClick = onBuyHp,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonColors(
                            containerColor = buttonColor,
                            contentColor = mainColor,
                            disabledContainerColor = buttonColor,
                            disabledContentColor = mainColor,
                        ),
                        modifier = Modifier
                            .padding(
                                start = padding.calculateStartPadding(LayoutDirection.Ltr),
                                end = padding.calculateEndPadding(LayoutDirection.Ltr),
                                top = padding.calculateTopPadding()
                            )
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            .height(100.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().fillMaxHeight()
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.coins),
                                contentDescription = null,
                                modifier = Modifier.size(35.dp)
                            )
                            Text(
                                text = "1500",
                                fontSize = 18.sp,
                                color = mainColor
                            )

                            Spacer(Modifier.width(24.dp))

                            Text(
                                text = "Восстановить здоровье",
                                fontSize = 15.sp,
                                color = mainColor
                            )
                        }
                    }
                }

                is InventoryUiState.ItemType -> {
                    storeViewModel.getItems(state.type)
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize(), // важно, чтобы грид мог занимать всю доступную высоту
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(storeState.items) { item ->
                                StoreItem(
                                    item = item,
                                    itemType = state.type,
                                    onClick = {
                                        selectedItem = it
                                    }
                                )
                            }
                        }

                        selectedItem?.let { item ->
                            AlertDialog(
                                onDismissRequest = { selectedItem = null },
                                title = { Text(text = item.title) },
                                text = {
                                    Column {
                                        Text(text = item.description)
                                        Spacer(Modifier.height(8.dp))
                                        Text("Цена: ${item.cost} монет")
                                        Text("Требуемый уровень: ${item.lvlToBuy}")
                                    }
                                },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            storeViewModel.buyItem(item.storeId)
                                            selectedItem = null
                                        },
                                        enabled = !item.isOwned
                                    ) {
                                        Text(if (item.isOwned) "Куплено" else "Купить")
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = { selectedItem = null }) {
                                        Text("Отмена")
                                    }
                                }
                            )
                        }

                        OutlinedButton(
                            onClick = { uiState.value = InventoryUiState.ItemList },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text("Назад")
                        }
                    }
                }
            }
        }
    }
}
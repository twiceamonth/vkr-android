package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.mav26.vkrapp.domain.model.task.DetailsCreate

@Composable
fun SubtasksEditor(
    subtasksList: List<String>,
    onListChange: (List<String>) -> Unit,
) {
    // Гарантируем хотя бы один пустой элемент
    val safeList = if (subtasksList.isEmpty()) listOf("") else subtasksList

    Column {
        safeList.forEachIndexed { index, item ->
            val isLast = index == safeList.lastIndex

            SubtaskRow(
                value = item,
                onValueChange = { newText ->
                    // Обновляем текст
                    val newList = safeList.toMutableList().apply {
                        this[index] = newText
                    }
                    // Если это последний элемент и он перешёл из пустого в непустой — добавляем новый пустой
                    if (isLast && newText.isNotBlank()) {
                        newList.add("")
                    }
                    onListChange(newList)
                },
                onRemove = {
                    val newList = safeList.toMutableList().apply {
                        removeAt(index)
                    }
                    onListChange(newList)
                },
                showRemoveIcon = item.isNotBlank()
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun SubtaskRow(
    value: String,
    onValueChange: (String) -> Unit,
    onRemove: () -> Unit,
    showRemoveIcon: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text("Новая подзадача") },
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .heightIn(min = 56.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF95A9CE),
                unfocusedContainerColor = Color(0xFF95A9CE),
                focusedLabelColor = Color(0xFF042A2B),
                unfocusedLabelColor = Color(0xFF042A2B),
                focusedTextColor = Color(0xFF042A2B),
                unfocusedTextColor = Color(0xFF042A2B),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        if (showRemoveIcon) {
            IconButton(onClick = onRemove) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Удалить подзадачу"
                )
            }
        }
    }
}






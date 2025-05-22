package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import ru.mav26.vkrapp.domain.model.task.DetailsCreate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun LinksEditor(
    linksList: List<DetailsCreate>,
    onListChange: (List<DetailsCreate>) -> Unit
) {
    val safeList = if (linksList.isEmpty()) listOf(DetailsCreate("", "")) else linksList

    Column {
        safeList.forEachIndexed { index, item ->
            val isLast = index == safeList.lastIndex

            LinkRow(
                name = item.linkName,
                url = item.linkUrl,
                onNameChange = { newName ->
                    val newList = safeList.toMutableList().apply {
                        this[index] = this[index].copy(linkName = newName)
                    }
                    if (isLast && (newName.isNotBlank() || item.linkUrl.isNotBlank())) {
                        newList.add(DetailsCreate("", ""))
                    }
                    onListChange(newList)
                },
                onUrlChange = { newUrl ->
                    val newList = safeList.toMutableList().apply {
                        this[index] = this[index].copy(linkUrl = newUrl)
                    }
                    if (isLast && (item.linkName.isNotBlank() || newUrl.isNotBlank())) {
                        newList.add(DetailsCreate("", ""))
                    }
                    onListChange(newList)
                },
                onRemove = {
                    val newList = safeList.toMutableList().apply {
                        removeAt(index)
                    }
                    onListChange(newList)
                },
                showRemoveIcon = item.linkName.isNotBlank() || item.linkUrl.isNotBlank()
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun LinkRow(
    name: String,
    url: String,
    onNameChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    onRemove: () -> Unit,
    showRemoveIcon: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp)
    ) {
        TextField(
            value = name,
            onValueChange = onNameChange,
            placeholder = { Text("Название") },
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .heightIn(min = 56.dp),
            singleLine = true,
            shape = RoundedCornerShape(5.dp),
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

        TextField(
            value = url,
            onValueChange = onUrlChange,
            placeholder = { Text("URL") },
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .heightIn(min = 56.dp),
            singleLine = true,
            shape = RoundedCornerShape(5.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),
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
                    contentDescription = "Удалить"
                )
            }
        } else {
            Spacer(modifier = Modifier.width(48.dp))
        }
    }
}
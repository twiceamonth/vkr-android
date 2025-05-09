package ru.mav26.vkrapp.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                fontSize = 12.sp,
                lineHeight = 15.sp
            )
        },
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
        ),
        modifier = modifier
            .height(56.dp)
    )

}

@Preview
@Composable
private fun AAPrev() {
    AuthTextField(value = "123", label = "Логин", onValueChange = {})
}
package ru.mav26.vkrapp.presentation.feature.auth.components


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthButton(
    text: String,
    isFilled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonColors(
            containerColor = if (isFilled) Color(0xFFD0ECF6) else Color(0xFF042A2B),
            contentColor = if (isFilled) Color(0xFF042A2B) else Color(0xFFD0ECF6),
            disabledContainerColor = Color(0xFFD0ECF6),
            disabledContentColor = Color(0xFFD0ECF6)
        ),
        modifier = modifier
            .let {
                if (!isFilled) it.border(
                    1.dp,
                    Color(0xFFD0ECF6),
                    RoundedCornerShape(5.dp)
                ) else it
            }
            .height(48.dp)
    ) {
        Text(
            text,
            fontSize = 25.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview
@Composable
private fun APrev() {
    AuthButton(
        "Вход",
        true,
        Modifier.fillMaxWidth()
    ){}
}
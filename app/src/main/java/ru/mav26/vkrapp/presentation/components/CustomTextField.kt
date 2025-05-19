package ru.mav26.vkrapp.presentation.components

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.R
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.FieldType
import ru.mav26.vkrapp.presentation.theme.mainColor
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
fun <T> CustomTextField(
    value: T?,
    onValueChange: (T) -> Unit,
    label: String,
    fieldType: FieldType = FieldType.Text,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val dateFormatter = remember { DateTimeFormatter.ofPattern("yyyy-MM-dd") }
    val timeFormatter = remember { DateTimeFormatter.ofPattern("HH:mm:ss") }

    when (fieldType) {
        FieldType.Text -> {
            TextField(
                value = value as? String ?: "",
                onValueChange = { onValueChange(it as T) },
                label = { Text(label, fontSize = 12.sp, lineHeight = 15.sp) },
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
                modifier = modifier.heightIn(min = 56.dp)
            )
        }

        FieldType.Date -> {
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                val current = (value as? OffsetDateTime)?.toLocalDate() ?: LocalDate.now()
                val dialog = DatePickerDialog(
                    context,
                    { _, y, m, d ->
                        val date = LocalDate.of(y, m + 1, d)
                        val time = (value as? OffsetDateTime)?.toLocalTime() ?: LocalTime.MIDNIGHT
                        val offset = (value as? OffsetDateTime)?.offset
                            ?: ZoneId.systemDefault().rules.getOffset(Instant.now())
                        onValueChange(OffsetDateTime.of(date, time, offset) as T)
                        showDialog = false
                    },
                    current.year, current.monthValue - 1, current.dayOfMonth
                )
                dialog.setOnCancelListener {
                    showDialog = false
                }
                dialog.setButton(
                    DialogInterface.BUTTON_NEGATIVE,
                    context.getString(android.R.string.cancel)
                ) { _, _ ->
                    showDialog = false
                }
                dialog.show()
            }
            Box(modifier = modifier
                .heightIn(min = 56.dp)
                .clickable { showDialog = true }) {
                TextField(
                    value = (value as? OffsetDateTime)?.format(dateFormatter) ?: "",
                    onValueChange = {},
                    label = { Text(label, fontSize = 12.sp, lineHeight = 15.sp) },
                    readOnly = true,
                    enabled = false,
                    shape = RoundedCornerShape(5.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF95A9CE),
                        unfocusedContainerColor = Color(0xFF95A9CE),
                        disabledContainerColor = Color(0xFF95A9CE),
                        focusedLabelColor = Color(0xFF042A2B),
                        unfocusedLabelColor = Color(0xFF042A2B),
                        disabledLabelColor = Color(0xFF042A2B),
                        focusedTextColor = Color(0xFF042A2B),
                        unfocusedTextColor = Color(0xFF042A2B),
                        disabledTextColor = Color(0xFF042A2B),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.matchParentSize(),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.calendar),
                            contentDescription = null,
                            tint = mainColor,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                )
            }
        }

        FieldType.Time -> {
            var showPicker by remember { mutableStateOf(false) }
            if (showPicker) {
                showTimeWithSecondsPicker(context, value as? LocalTime,
                    onDecline = { showPicker = false }, onTimeSelected = { picked ->
                        onValueChange(picked as T)
                        showPicker = false
                    })
            }
            Box(modifier = modifier
                .heightIn(min = 56.dp)
                .clickable { showPicker = true }) {
                TextField(
                    value = (value as? LocalTime)?.format(timeFormatter) ?: "",
                    onValueChange = {},
                    label = { Text(label, fontSize = 12.sp, lineHeight = 15.sp) },
                    readOnly = true,
                    enabled = false,
                    shape = RoundedCornerShape(5.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF95A9CE),
                        unfocusedContainerColor = Color(0xFF95A9CE),
                        disabledContainerColor = Color(0xFF95A9CE),
                        focusedLabelColor = Color(0xFF042A2B),
                        unfocusedLabelColor = Color(0xFF042A2B),
                        disabledLabelColor = Color(0xFF042A2B),
                        focusedTextColor = Color(0xFF042A2B),
                        unfocusedTextColor = Color(0xFF042A2B),
                        disabledTextColor = Color(0xFF042A2B),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.matchParentSize(),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.timer),
                            contentDescription = null,
                            tint = mainColor,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                )
            }
        }
    }
}

fun showTimeWithSecondsPicker(
    context: Context,
    initial: LocalTime?,
    onTimeSelected: (LocalTime) -> Unit,
    onDecline: () -> Unit,
) {
    val init = initial ?: LocalTime.now()

    // Создаем вертикальный контейнер
    val outerLayout = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(20, 20, 20, 20)
    }

    // Подписи
    val labelsLayout = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
    }
    listOf("Часы", "Минуты", "Секунды").forEach { label ->
        val tv = TextView(context).apply {
            text = label
            textSize = 14f
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
        }
        labelsLayout.addView(tv)
    }

    // Колесики выбора
    val pickersLayout = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
    }

    val hourPicker = NumberPicker(context).apply {
        minValue = 0
        maxValue = 23
        value = init.hour
        wrapSelectorWheel = true
        layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
    }

    val minutePicker = NumberPicker(context).apply {
        minValue = 0
        maxValue = 59
        value = init.minute
        wrapSelectorWheel = true
        layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
    }

    val secondPicker = NumberPicker(context).apply {
        minValue = 0
        maxValue = 59
        value = init.second
        wrapSelectorWheel = true
        layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
    }

    pickersLayout.addView(hourPicker)
    pickersLayout.addView(minutePicker)
    pickersLayout.addView(secondPicker)

    // Объединяем подписи и колесики
    outerLayout.addView(labelsLayout)
    outerLayout.addView(pickersLayout)

    // Строим диалог
    AlertDialog.Builder(context)
        .setView(outerLayout)
        .setPositiveButton("OK") { _, _ ->
            val picked = LocalTime.of(
                hourPicker.value,
                minutePicker.value,
                secondPicker.value
            )
            onTimeSelected(picked)
        }
        .setNegativeButton("Отмена") { _, _ ->
            onDecline()
        }
        .setOnCancelListener {
            onDecline()
        }
        .show()
}
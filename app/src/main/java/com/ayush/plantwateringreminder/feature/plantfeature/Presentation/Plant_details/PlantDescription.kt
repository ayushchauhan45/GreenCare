package com.ayush.plantwateringreminder.feature.plantfeature.Presentation.Plant_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 5
) {
    var expanded by remember { mutableStateOf(false) }

    val displayText = if (expanded) text else text.takeWhileIndexed { index, _ ->
        index < maxLines * 50
    } + if (!expanded) "..." else ""

    Column(modifier = modifier) {
        Text(
            text = displayText,
            maxLines = if (expanded) Int.MAX_VALUE else maxLines,
            overflow = TextOverflow.Ellipsis
        )
        if (!expanded) {
            Text(
                text = "Read More",
                color = Color(0xFF119176),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { expanded = true }
            )
        }
    }
}

inline fun String.takeWhileIndexed(predicate: (Int, Char) -> Boolean): String {
    val result = StringBuilder()
    for (index in indices) {
        if (!predicate(index, this[index])) break
        result.append(this[index])
    }
    return result.toString()
}

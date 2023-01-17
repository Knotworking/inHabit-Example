package com.knotworking.inhabit.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.knotworking.inhabit.model.HabitDisplayable
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HabitGridItem(
    modifier: Modifier = Modifier,
    habit: HabitDisplayable,
    deleteHabit: (habitId: UUID) -> Unit = {}
) {
    Card(onClick = {}, modifier = modifier.padding(0.dp), elevation = 5.dp) {
        Column(Modifier.padding(8.dp)) {
            Text(text = habit.name)
            Text(text = "${habit.entries.size} entries")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = { deleteHabit(habit.id) }) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Extra actions for habit"
                    )
                }
            }
        }
    }
}
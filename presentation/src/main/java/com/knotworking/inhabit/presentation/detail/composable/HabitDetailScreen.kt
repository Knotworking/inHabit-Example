package com.knotworking.inhabit.presentation.detail.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.knotworking.inhabit.presentation.common.LoadingContent
import com.knotworking.inhabit.presentation.detail.HabitDetailViewModel
import com.knotworking.inhabit.presentation.model.HabitDisplayable

@Composable
fun HabitDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: HabitDetailViewModel = hiltViewModel()
) {
    val viewState by viewModel.habitDetailViewStateFlow.collectAsState()

    if (viewState.habit == null || viewState.loading) {
        com.knotworking.inhabit.presentation.common.LoadingContent(modifier)
    } else {
        HabitDetailContent(
            modifier = modifier,
            habit = viewState.habit!!
        )
    }


}

@Composable
fun HabitDetailContent(
    modifier: Modifier = Modifier,
    habit: com.knotworking.inhabit.presentation.model.HabitDisplayable
) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = habit.name,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Text(text = "${habit.entries.size} entries")
    }
}
package com.knotworking.inhabit.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.knotworking.inhabit.presentation.BaseViewModel
import com.knotworking.inhabit.domain.usecase.GetHabitUseCase
import com.knotworking.inhabit.presentation.model.toDisplayable
import com.knotworking.inhabit.presentation.navigation.HabitDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HabitDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getHabitUseCase: GetHabitUseCase
) : BaseViewModel() {

    private val habitId =
        UUID.fromString(checkNotNull(savedStateHandle.get<String>(HabitDetail.habitIdArg)))

    val habitDetailViewStateFlow: StateFlow<HabitDetailViewState>
        get() = _habitDetailStateFlow
    private var _habitDetailStateFlow = MutableStateFlow(HabitDetailViewState())

    override val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        _habitDetailStateFlow.value = _habitDetailStateFlow.value.copy(hasError = true)
    }

    init {
        getHabit()
    }

    private fun getHabit() {
        launchInViewModelScope {
            getHabitUseCase(habitId).onStart {
                _habitDetailStateFlow.value = HabitDetailViewState(loading = true)
            }.catch {
                _habitDetailStateFlow.value = HabitDetailViewState(hasError = true)
            }.collect { result ->
                result.onSuccess { habit ->
                    _habitDetailStateFlow.value =
                        HabitDetailViewState(habit = habit.toDisplayable())
                }.onFailure {
                    _habitDetailStateFlow.value = HabitDetailViewState(hasError = true)
                }
            }
        }
    }
}
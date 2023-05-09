package ru.dmitryskor.notecryptography.core.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dmitryskor.notecryptography.core.domain.MVIUseCase

abstract class MVIViewModel
<State : UIState, Effect : UIEffect, Intent : UIIntent>
    (private val useCase: MVIUseCase<State, Effect, Intent>) : ViewModel() {

    init {
        viewModelScope.launch {
            useCase.listenIntents()
        }
        viewModelScope.launch {
            useCase.invoke()
        }
    }

    val state = useCase.state

    fun setIntent(intent: Intent) {
        viewModelScope.launch {
            useCase.setIntent(intent)
        }
    }
}

interface UIState
interface UIEffect
interface UIIntent
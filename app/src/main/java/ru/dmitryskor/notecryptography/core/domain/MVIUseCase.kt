package ru.dmitryskor.notecryptography.core.domain

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import ru.dmitryskor.notecryptography.core.ui.viewModel.UIEffect
import ru.dmitryskor.notecryptography.core.ui.viewModel.UIIntent
import ru.dmitryskor.notecryptography.core.ui.viewModel.UIState

abstract class MVIUseCase<State : UIState, Effect : UIEffect, Intent : UIIntent> {
    private val initState: State by lazy { createInitState() }
    abstract fun createInitState(): State

    protected val currentState: State get() = _state.value

    private val _state = MutableStateFlow(initState)
    val state = _state.asStateFlow()

    private val _intent = MutableSharedFlow<Intent>()
    protected val intent = _intent.asSharedFlow()

    private val _effect = Channel<Effect>()
    protected val effect = _effect.receiveAsFlow()

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _state.value = newState
    }

    suspend fun setIntent(intent: Intent) {
        _intent.emit(intent)
    }

    suspend fun setEffect(builder: () -> Effect) {
        val newEffect = builder()
        _effect.send(newEffect)
    }

    suspend fun listenIntents() {
        intent.map {
            handleIntent(it)
        }.collect()
    }

    abstract fun handleIntent(intent: Intent)

    abstract suspend fun invoke()
}
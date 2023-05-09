package ru.dmitryskor.notecryptography.mainScreen.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.dmitryskor.notecryptography.R
import ru.dmitryskor.notecryptography.StringService
import ru.dmitryskor.notecryptography.mainScreen.domain.GetStringsListNoteUseCase
import ru.dmitryskor.notecryptography.mainScreen.domain.ListNoteScreenUseCase

class ListNoteScreenViewModel(
    useCase: MVIUseCase<ListNoteContract.State, ListNoteContract.Effect, ListNoteContract.Intent> = ListNoteScreenUseCase(
        stringRes = GetStringsListNoteUseCase(StringService)
    )
) : MVIViewModel<ListNoteContract.State, ListNoteContract.Effect, ListNoteContract.Intent>(useCase) {

}

class ListNoteContract {
    sealed class Effect : UIEffect {

    }

    sealed class Intent : UIIntent {

    }

    data class State(
        val title: String = "",
        val floatImage: Int = R.drawable.ic_edit,
        val floatContentDescription: String = "",
    ) : UIState
}

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

interface UIState
interface UIEffect
interface UIIntent

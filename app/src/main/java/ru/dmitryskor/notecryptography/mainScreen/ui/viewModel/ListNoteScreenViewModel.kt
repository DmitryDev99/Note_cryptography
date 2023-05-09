package ru.dmitryskor.notecryptography.mainScreen.ui.viewModel

import ru.dmitryskor.notecryptography.R
import ru.dmitryskor.notecryptography.core.domain.MVIUseCase
import ru.dmitryskor.notecryptography.core.ui.viewModel.MVIViewModel
import ru.dmitryskor.notecryptography.core.ui.viewModel.UIEffect
import ru.dmitryskor.notecryptography.core.ui.viewModel.UIIntent
import ru.dmitryskor.notecryptography.core.ui.viewModel.UIState
import ru.dmitryskor.notecryptography.mainScreen.data.StringResListNoteServiceImpl
import ru.dmitryskor.notecryptography.mainScreen.domain.ListNoteScreenUseCase

class ListNoteScreenViewModel(
    useCase: MVIUseCase<ListNoteContract.State, ListNoteContract.Effect, ListNoteContract.Intent> =
        ListNoteScreenUseCase(stringService = StringResListNoteServiceImpl())
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

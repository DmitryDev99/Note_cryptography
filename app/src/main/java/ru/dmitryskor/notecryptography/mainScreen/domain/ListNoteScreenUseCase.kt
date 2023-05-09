package ru.dmitryskor.notecryptography.mainScreen.domain

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import ru.dmitryskor.notecryptography.mainScreen.ui.viewModel.ListNoteContract
import ru.dmitryskor.notecryptography.mainScreen.ui.viewModel.MVIUseCase

class ListNoteScreenUseCase(
    private val stringRes: GetStringsListNoteUseCase
) : MVIUseCase<ListNoteContract.State, ListNoteContract.Effect, ListNoteContract.Intent>() {
    override fun createInitState() = ListNoteContract.State()

    override suspend fun invoke() {
        stringRes.invoke().map {
            setState {
                copy(title = it.listNotesTitle, floatContentDescription = it.listNotesNewNote)
            }
        }.collect()
    }

    override fun handleIntent(intent: ListNoteContract.Intent) {

    }
}
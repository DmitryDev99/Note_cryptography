package ru.dmitryskor.notecryptography.mainScreen.domain

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.dmitryskor.notecryptography.core.domain.MVIUseCase
import ru.dmitryskor.notecryptography.mainScreen.domain.services.StringResListNoteService
import ru.dmitryskor.notecryptography.mainScreen.ui.viewModel.ListNoteContract

class ListNoteScreenUseCase(
    private val stringService: StringResListNoteService
) : MVIUseCase<ListNoteContract.State, ListNoteContract.Effect, ListNoteContract.Intent>() {
    override fun createInitState() = ListNoteContract.State()

    override suspend fun invoke() {

        stringService.listNotesTitle.combine(stringService.listNotesNewNote) { title, newNote ->
            setState {
                copy(title = title, floatContentDescription = newNote)
            }
        }.collect()
    }

    override fun handleIntent(intent: ListNoteContract.Intent) {

    }
}
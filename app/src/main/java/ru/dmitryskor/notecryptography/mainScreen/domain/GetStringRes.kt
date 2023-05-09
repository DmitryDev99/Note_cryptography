package ru.dmitryskor.notecryptography.mainScreen.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ru.dmitryskor.notecryptography.mainScreen.domain.entity.ListNoteStrings
import ru.dmitryskor.notecryptography.mainScreen.domain.services.StringResListNoteService

class GetStringsListNoteUseCase(
    private val service: StringResListNoteService
) {
    fun invoke(): Flow<ListNoteStrings> {
        return service.listNotesTitle.combine(service.listNotesNewNote) { title, newNote ->
            ListNoteStrings(title, newNote)
        }
    }
}
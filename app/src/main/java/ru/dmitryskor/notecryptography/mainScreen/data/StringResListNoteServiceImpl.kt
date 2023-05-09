package ru.dmitryskor.notecryptography.mainScreen.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.dmitryskor.notecryptography.MainActivity
import ru.dmitryskor.notecryptography.R
import ru.dmitryskor.notecryptography.mainScreen.domain.services.StringResListNoteService

class StringResListNoteServiceImpl : StringResListNoteService {

    override val listNotesNewNote: Flow<String>
        get() = MainActivity.contextFlow.map {
            it?.getString(R.string.list_notes_new_note) ?: ""
        }

    override val listNotesTitle: Flow<String> = MainActivity.contextFlow.map {
        it?.getString(R.string.list_note_title) ?: ""
    }
}
package ru.dmitryskor.notecryptography.mainScreen.domain.services

import kotlinx.coroutines.flow.Flow

interface StringResListNoteService {
    val listNotesTitle: Flow<String>
    val listNotesNewNote: Flow<String>
}
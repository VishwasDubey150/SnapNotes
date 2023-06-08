package com.example.snapnote

import androidx.lifecycle.LiveData

class NotesRepository(private val noteDao: NoteDao) {

    val allNoteDao : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note)
    {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note)
    {
        noteDao.delete(note)
    }

    suspend fun update(note: Note)
    {
        noteDao.update(note)
    }

}
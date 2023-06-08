package com.example.snapnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteVIewModel(application: Application) : AndroidViewModel(application){
    private val repository : NotesRepository
    val allnotes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository= NotesRepository(dao)
        allnotes = repository.allNoteDao
    }

    fun deleteNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.delete(note)
    }

    fun addNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.update(note)
    }
}
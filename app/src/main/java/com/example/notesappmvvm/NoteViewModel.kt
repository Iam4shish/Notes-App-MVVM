package com.example.notesappmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {
    private val repository = NoteRepository()
    private val _notes = MutableLiveData<List<Note>>()
    val notes: MutableLiveData<List<Note>> = _notes
    init{
        refreshNotes()
    }

    private fun refreshNotes() {
            _notes.value = repository.getAllNotes()
    }

    fun addNote(title: String, content: String){
        repository.addNote(title, content)
        refreshNotes()
    }

    fun updateNote(id: Int, title: String, content: String){
        repository.updateNote(id, title, content)
        refreshNotes()
    }

    fun deleteNote(id: Int){
        repository.deleteNote(id)
        refreshNotes()
    }
}
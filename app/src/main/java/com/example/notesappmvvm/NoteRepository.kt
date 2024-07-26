package com.example.notesappmvvm

import android.os.Build
import androidx.annotation.RequiresApi

class NoteRepository {
    private val notes = ArrayList<Note>()
    private var lastId = 0

    fun getAllNotes(): List<Note> = notes

    fun addNote(title: String, content: String) {
        lastId++
        notes.add(Note(lastId, title, content))
    }

    fun updateNote(id: Int, title: String, content: String) {
        val note = notes.find { it.id == id }
        note?.let {
            it.title = title
            it.content = content
        }
    }


    fun deleteNote(id: Int) {
        notes.removeAll{ it.id == id }
    }
}
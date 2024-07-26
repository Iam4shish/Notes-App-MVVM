package com.example.notesappmvvm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

//        adapter = NoteAdapter(emptyList(),this:: onNoteClick)
        adapter = NoteAdapter(
            emptyList(),
            onItemClick = this::onNoteClick,
            onDeleteClick = this::onNoteDelete
        )

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.notes.observe(this,Observer { notes ->
            adapter.updateNotes(notes)
        })

        val addButton: FloatingActionButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            showAddNoteDialog()
        }




    }

    private fun onNoteDelete(note: Note) {
        viewModel.deleteNote(note.id)
    }

    private fun showAddNoteDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_note, null)
        val titleEditText = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val contentEditText = dialogView.findViewById<EditText>(R.id.editTextContent)

        AlertDialog.Builder(this)
            .setTitle("Add New Note")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val title = titleEditText.text.toString()
                val content = contentEditText.text.toString()
                if (title.isNotBlank() || content.isNotBlank()) {
                    viewModel.addNote(title, content)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun onNoteClick(note: Note) {
        // Handle note click
    }
}
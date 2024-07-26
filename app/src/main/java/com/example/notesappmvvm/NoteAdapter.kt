package com.example.notesappmvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private var notes: List<Note>,
    private val onItemClick: (Note) -> Unit,
    private val onDeleteClick: (Note) -> Unit

) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val contentTextView: TextView = view.findViewById(R.id.contentTextView)
        val buttonDelete : Button = view.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
        holder.itemView.setOnClickListener { onItemClick(note) }
        holder.buttonDelete.setOnClickListener {
                println("I'm clicked")
            onDeleteClick(note)
        }
    }



    override fun getItemCount() = notes.size

    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }

}
package com.example.notesappproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappproject.R
import com.example.notesappproject.databinding.ItemNoteBinding
import com.example.notesappproject.models.Note

class NotesAdapter :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    var notesList: MutableList<Note> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) = binding.apply {
            notsText.text = note.title
            descriptionText.text = note.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        val binding = ItemNoteBinding.bind(view)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int = notesList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notesList[position])
    }



    fun updateNote(position: Int,newNote: Note){
        notesList[position] = newNote
        notifyItemChanged(position)
    }
}
interface ItemOnClickLIstener {

    fun showCustom(position: Int, note: Note)
}


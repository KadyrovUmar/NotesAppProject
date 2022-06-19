package com.example.notesappproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notesappproject.databinding.ActivityNoteBinding
import com.example.notesappproject.models.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NoteActivity : AppCompatActivity() {

    private val binding: ActivityNoteBinding by lazy {
        ActivityNoteBinding.inflate(layoutInflater)
    }

    private var notesList = mutableListOf<Note>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadData()
        binding.floatingActionButton3.setOnClickListener {
            addNewNote()
        }
    }

    private fun loadData() {
        val sharedPrefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPrefs.getString("MySaveData", null)
        val type = object : TypeToken<MutableList<Note>>() {}.type
        val loadNotesList: MutableList<Note>? = gson.fromJson(json, type)
        notesList = loadNotesList ?: mutableListOf()
    }

    private fun addNewNote() {
        binding.apply {
            when {
                notesET.text.isBlank() -> showToast()
                descriptionET.text.isBlank() -> showToast()

                else -> {
                    val Title = notesET.text.toString().toInt()
                    val description = descriptionET.text.toString().toInt()
                    val newNote = Note(
                        title = notesET.text.toString(),
                        description = descriptionET.text.toString(),
                    )
                    saveData(newNote)
                }
            }
        }
    }

    private fun saveData(note: Note) {
        notesList.add(note)
        val sharedPrefs =
            getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        val gson = Gson()
        val saveObj: String = gson.toJson(notesList)
        editor.putString("MySaveData", saveObj)
        editor.apply()
        Toast.makeText(this, "Успешно сохранен", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun showToast() {
        Toast.makeText(this, "Сначала заполните все поля", Toast.LENGTH_SHORT).show()
    }
}
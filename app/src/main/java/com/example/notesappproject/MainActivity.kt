package com.example.notesappproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notesappproject.adapter.ItemOnClickLIstener
import com.example.notesappproject.adapter.NotesAdapter
import com.example.notesappproject.databinding.ActivityMainBinding
import com.example.notesappproject.databinding.ItemNoteBinding
import com.example.notesappproject.models.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(),ItemOnClickLIstener {
    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter: NotesAdapter by lazy {
        NotesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainRV.adapter = adapter

        loadData()

        binding.floatingActionButton.setOnClickListener{
             startActivity(Intent(this, NoteActivity::class.java))
        }
    }


    private fun loadData() {
        val sharedPrefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPrefs.getString("MySaveData", null)
        val type = object : TypeToken<MutableList<String>>() {}.type
        val loadNotesList: MutableList<Note>? = gson.fromJson(json,type)
        adapter.notesList = (loadNotesList ?: listOf()).toMutableList()

        }

    override fun showCustom(position: Int, note: Note) {
        startActivity(Intent(this,NoteActivity::class.java))
    }
}
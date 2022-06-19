package com.example.notesappproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = Random().nextInt(1000),
    val title:String,
    val description:String,
):Serializable
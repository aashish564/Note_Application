package com.example.noteapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.Dao.NoteDao
import com.example.noteapp.Entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NotesDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}

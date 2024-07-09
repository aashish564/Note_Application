package com.example.noteapp.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.Entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getSpecificNote(id: Int): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note: NoteEntity)

    @Delete
    suspend fun deleteNotes(note: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteSpecificNote(id: Int)

    @Update
    suspend fun updateNotes(note: NoteEntity)
}

package com.example.noteapp.Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.Entities.NoteEntity
import com.example.noteapp.Repository.NotesRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateNoteViewModel @Inject constructor(private val notesRepo: NotesRepo) : ViewModel() {

    val noteId = MutableStateFlow<Int?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val note = noteId.flatMapLatest {
        val note = it?.let { notesRepo.getNote(it) }
        flowOf(note)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun setNoteId(id:Int) = viewModelScope.launch {
        noteId.emit(id)
    }

    suspend fun updateNote(noteEntity: NoteEntity) = notesRepo.updateNotes(noteEntity)

    suspend fun saveNote(noteEntity: NoteEntity) = notesRepo.insertNote(noteEntity)

    suspend fun deleteNote() = noteId.value?.let { notesRepo.deleteNoteById(it) }

}
package com.example.googlenotesclone.data

import com.example.googlenotesclone.data.ROOM.Note
import com.example.googlenotesclone.data.ROOM.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dao : NoteDao) {

    suspend fun addNote(note: Note) = dao.insert(note)
    suspend fun getNoteById(id: Int) = dao.getNoteById(id.toString())
    suspend fun updateNote(note: Note) = dao.upsert(note)
    suspend fun deleteNote(note: Note) = dao.deleteNote(note)
    suspend fun deleteAllNotes() = dao.deleteAll()
    //uzywac buffer collectLatest czy conflate???? -> poczytac
    fun getAllNotes(): Flow<List<Note>> = dao.getNotes().flowOn(Dispatchers.IO)

}
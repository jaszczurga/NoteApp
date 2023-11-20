package com.example.googlenotesclone.screens.NoteScreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlenotesclone.data.NoteRepository
import com.example.googlenotesclone.data.ROOM.Note
import com.example.googlenotesclone.navigation.NoteDestinationArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


data class NoteUiState(
    val note: Note = Note(title = "", description = ""),
    val loading: Boolean = false,
    val isTaskSaved: Boolean = false,
    val userMessage: String? = null
)
@HiltViewModel
class NoteScreenViewModel @Inject constructor(private val repository : NoteRepository,savedStateHandle : SavedStateHandle) : ViewModel() {

    val noteid : String? = savedStateHandle[NoteDestinationArgs.NOTE_ID]
    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()
    init {
        //Log.d("NoteScreenViewModel", "noteId: ${savedStateHandle.keys()}  $noteid")
        //dziwna sprawa :*
        if (noteid != "null") {
            //Log.d("NoteScreenViewModel", "noteId: wykonalo sie jak?????")
            loadNote(noteid.toString())
        }
    }
    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun insertNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

    fun updateTitle(newTitle: String) {
        _uiState.update {
            it.copy(note = it.note.copy(title = newTitle))
        }
    }

    fun updateDescription(newDescription: String) {
        _uiState.update {
            it.copy(note = it.note.copy(description = newDescription))
        }
    }


    private fun loadNote(noteId: String) {
        _uiState.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {
            repository.getNoteById(noteId.toInt()).let { note ->
                _uiState.update {
                    it.copy(
                        note=note,
                        loading = false
                    )
                }
            }
        }
    }

    fun saveNote() {
        Log.d("saveNote()log" , "saveNote: wywolanie saveNote")
        Log.d("saveNote()log" , "saveNote: ${uiState.value.note.title} ${uiState.value.note.id}")
        if (uiState.value.note.title.isEmpty() || uiState.value.note.description.isEmpty()) {
            _uiState.update {
                it.copy(userMessage = "note cannot be empty")
            }
            return
        }

        if (noteid == null) {
            createNewNote()
        } else {
            updateTask()
        }
    }

    private fun createNewNote() = viewModelScope.launch {
        repository.addNote(Note(title=uiState.value.note.title, description = uiState.value.note.description))
        _uiState.update {
            it.copy(isTaskSaved = true)
        }
    }

    private fun updateTask() {
        Log.d("saveNoteUpd" , "updateTask: ${uiState.value.note.title} ${uiState.value.note.id}")
        if (noteid == null) {
            throw RuntimeException("updateNote() was called but note is new.")
        }
        viewModelScope.launch {
            Log.d("saveupdate" , "updateTask: wywolanie updateTask")
            repository.updateNote(
                Note(
                    id = uiState.value.note.id,
                    title = uiState.value.note.title,
                    description = uiState.value.note.description,
                )
            )
            _uiState.update {
                it.copy(isTaskSaved = true)
            }
        }
    }


}
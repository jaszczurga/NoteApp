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


data class NoteUiState(
    val note: Note = Note(0,"",""),
    val loading: Boolean = false,
)
@HiltViewModel
class NoteScreenViewModel @Inject constructor(private val repository : NoteRepository,savedStateHandle : SavedStateHandle) : ViewModel() {

    val noteid : String = savedStateHandle[NoteDestinationArgs.NOTE_ID]!!
    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()
    init {
        Log.d("NoteScreenViewModel", "noteId: ${savedStateHandle.keys()}  $noteid")
        if (noteid != null) {
            loadTask(noteid.toInt())
        }
    }
    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun insertNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

    private fun loadTask(noteId: Int) {
        _uiState.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {
            repository.getNoteById(noteId).let { note ->
                _uiState.update {
                    it.copy(
                        note=note,
                        loading = false
                    )
                }
            }
        }
    }

}
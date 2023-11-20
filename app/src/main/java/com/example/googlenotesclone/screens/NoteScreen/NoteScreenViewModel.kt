package com.example.googlenotesclone.screens.NoteScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlenotesclone.data.NoteRepository
import com.example.googlenotesclone.data.ROOM.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class NoteUiState(
    val note: Note = Note(0,"",""),
    val loading: Boolean = false,
)
@HiltViewModel
class NoteScreenViewModel @Inject constructor(private val repository : NoteRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState : StateFlow<NoteUiState> = _uiState
    fun setId(id: Int){
        _uiState.value = NoteUiState(loading = true)
        viewModelScope.launch {
            _uiState.value = NoteUiState(repository.getNoteById(id))
            Log.d("NoteViewModel vm" , "setId: ${_uiState.value.note.title}")
            _uiState.value = NoteUiState(loading = false)
        }
    }
    fun getNote() = uiState.value
    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun insertNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

}
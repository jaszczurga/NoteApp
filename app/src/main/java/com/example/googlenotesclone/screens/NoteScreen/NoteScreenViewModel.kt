package com.example.googlenotesclone.screens.NoteScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlenotesclone.data.NoteRepository
import com.example.googlenotesclone.data.ROOM.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


data class NoteUiState(
    val note: Note = Note(-1,"",""),
)
@HiltViewModel
class NoteScreenViewModel @Inject constructor(private val repository : NoteRepository) : ViewModel() {


    //zrobic to xd
    private var id =-1
    private val _note = MutableStateFlow(NoteUiState())
    val uiState : StateFlow<NoteUiState> = _note

    fun setId(id: Int){
        this.id = id
        viewModelScope.launch {
            _note.value = NoteUiState(repository.getNoteById(id))
        }
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

}
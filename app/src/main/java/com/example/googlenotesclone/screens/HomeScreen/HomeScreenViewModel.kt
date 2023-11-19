package com.example.googlenotesclone.screens.HomeScreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlenotesclone.data.NoteRepository
import com.example.googlenotesclone.data.ROOM.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val items: List<Note> = emptyList(),
)
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val NoteRepository: NoteRepository,
    private val savedStateHandle : SavedStateHandle
) :ViewModel(){

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            NoteRepository.getAllNotes()
                .collect { listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()) {
                        Log.d("GetNoteList", ": Empty list")
                    }else {
                        _noteList.value = listOfNotes
                    }
                }
        }
    }


}



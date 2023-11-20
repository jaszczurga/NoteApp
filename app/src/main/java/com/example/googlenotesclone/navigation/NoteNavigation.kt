package com.example.googlenotesclone.navigation

import androidx.navigation.NavHostController
import com.example.googlenotesclone.navigation.NoteDestinationArgs.NOTE_ID
import com.example.googlenotesclone.navigation.NoteScreens.NOTE_SCREEN

//nie uzywamy enum
//enum class NoteNavigation {
//    HomeScreen,
//    NoteScreen,
//    SearchScreen,
//    SettingsScreen,
//    DeletedScreen
//}

//przejscie z klasy enum do objektu

private object NoteScreens{
    const val HOME_SCREEN="home"
    const val NOTE_SCREEN = "note"
    const val SEARCH_SCREEN = "search"
    const val SETTINGS_SCREEN = "search"
    const val DELETED_SCREEN = "deleted"
}

object NoteDestinationArgs{
    const val NOTE_ID = "noteId"
}

object NoteDestinations{
    const val HOME_ROUTE = NoteScreens.HOME_SCREEN
    const val NOTE_ROUTE = "$NOTE_SCREEN/{$NOTE_ID}"
    const val SEARCH_ROUTE = NoteScreens.SEARCH_SCREEN
    const val SETTINGS_ROUTE = NoteScreens.SETTINGS_SCREEN
    const val DELETED_ROUTE = NoteScreens.DELETED_SCREEN
}

// navigation actions

class NoteNavigationActions(private val navController: NavHostController){
    //action of passing id of note from home screen to note screen
    fun navigateToNoteScreen(noteId : Int?){
        navController.navigate("$NOTE_SCREEN/$noteId")
    }


}
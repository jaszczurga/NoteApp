package com.example.googlenotesclone.navigation

import androidx.navigation.NavHostController

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
    //arguments
}

object NoteDestinations{
    const val HOME_ROUTE = NoteScreens.HOME_SCREEN
    const val NOTE_ROUTE = NoteScreens.NOTE_SCREEN
    const val SEARCH_ROUTE = NoteScreens.SEARCH_SCREEN
    const val SETTINGS_ROUTE = NoteScreens.SETTINGS_SCREEN
    const val DELETED_ROUTE = NoteScreens.DELETED_SCREEN
}

// navigation actions

class NoteNavigationActions(private val navController: NavHostController){

}
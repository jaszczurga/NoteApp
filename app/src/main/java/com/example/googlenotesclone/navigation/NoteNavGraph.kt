package com.example.googlenotesclone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.googlenotesclone.MainViewModel
import com.example.googlenotesclone.screens.DeletedScreen
import com.example.googlenotesclone.screens.HomeScreen.HomeScreen
import com.example.googlenotesclone.screens.NoteScreen.NoteScreen
import com.example.googlenotesclone.screens.SearchScreen
import com.example.googlenotesclone.screens.SettingsScreen

@Composable
fun NotesAppNavigation(viewModel : MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NoteDestinations.HOME_ROUTE){
        composable(NoteDestinations.HOME_ROUTE){
            HomeScreen(navController=navController,viewModel=viewModel)
        }
        composable(NoteDestinations.NOTE_ROUTE){
            NoteScreen(navController=navController, viewModel=viewModel)
        }
        composable(NoteDestinations.DELETED_ROUTE){
            DeletedScreen(navController=navController)
        }
        composable(NoteDestinations.SEARCH_ROUTE){
            SearchScreen(navController=navController)
        }
        composable(NoteDestinations.SETTINGS_ROUTE){
            SettingsScreen(navController=navController)
        }
    }

}
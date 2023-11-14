package com.example.googlenotesclone.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
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
    NavHost(navController = navController, startDestination = NotesAppScreens.HomeScreen.name){
        composable(NotesAppScreens.HomeScreen.name){
            HomeScreen(navController=navController,viewModel=viewModel)
        }
        composable(NotesAppScreens.NoteScreen.name){
            NoteScreen(navController=navController, viewModel=viewModel)
        }
        composable(NotesAppScreens.DeletedScreen.name){
            DeletedScreen(navController=navController)
        }
        composable(NotesAppScreens.SearchScreen.name){
            SearchScreen(navController=navController)
        }
        composable(NotesAppScreens.SettingsScreen.name){
            SettingsScreen(navController=navController)
        }
    }

}
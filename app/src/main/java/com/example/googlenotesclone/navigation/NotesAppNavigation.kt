package com.example.googlenotesclone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.googlenotesclone.screens.DeletedScreen
import com.example.googlenotesclone.screens.HomeScreen.HomeScreen
import com.example.googlenotesclone.screens.NoteScreen
import com.example.googlenotesclone.screens.SearchScreen
import com.example.googlenotesclone.screens.SettingsScreen

@Composable
fun NotesAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NotesAppScreens.HomeScreen.name){
        composable(NotesAppScreens.HomeScreen.name){
            HomeScreen(navController=navController)
        }
        composable(NotesAppScreens.NoteScreen.name){
            NoteScreen(navController=navController)
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
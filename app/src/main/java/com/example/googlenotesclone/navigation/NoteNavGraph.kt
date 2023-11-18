package com.example.googlenotesclone.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.googlenotesclone.MainViewModel
import com.example.googlenotesclone.screens.DeletedScreen
import com.example.googlenotesclone.screens.HomeScreen.HomeScreen
import com.example.googlenotesclone.screens.NoteScreen.NoteScreen
import com.example.googlenotesclone.screens.SearchScreen
import com.example.googlenotesclone.screens.SettingsScreen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesAppNavigation(modifier : Modifier = Modifier,
                       navController : NavHostController = rememberNavController(),
                       coroutineScope : CoroutineScope = rememberCoroutineScope(),
                       drawerState : DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
                       startDestination : String = NoteDestinations.HOME_ROUTE,
                       navActions : NoteNavigationActions = remember(navController){
                           NoteNavigationActions(navController)
                       }
    ) {

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination
/

    NavHost(navController = navController,
        startDestination = NoteDestinations.HOME_ROUTE,
        modifier=modifier){
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
package com.example.googlenotesclone.screens.HomeScreen

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.Segment
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController : NavHostController = NavHostController(LocalContext.current)) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState ,
        drawerContent = {
            ModalDrawerSheet { /* TODO(drawer content) */ }
        } ,
    ) {
        HomeContent(drawerState = drawerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class , ExperimentalComposeUiApi::class)
@Composable
fun HomeContent(drawerState : DrawerState , modifier : Modifier = Modifier) {
    Scaffold() { contentPadding ->
        Log.d("HomeScreenPadding" , "HomeScreen padding:${contentPadding} ")
        // Screen content
        Column(modifier.fillMaxSize()) {
            SearchForm(drawerState = drawerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun SearchForm(
    modifier : Modifier = Modifier ,
    loading : Boolean = false ,
    hint : String = "Search" ,
    drawerState : DrawerState ,
    onSearch : (String) -> Unit = {}
) {

    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()
        }
        val focusManager = LocalFocusManager.current

        InputField(valueState = searchQueryState ,
            labelId = "Search" ,
            enabled = true ,
            onAction = KeyboardActions {
                if(! valid) {
                    focusManager.clearFocus()
                    return@KeyboardActions
                }
                onSearch(searchQueryState.value.trim())
                Log.d("searchQueryState" , "SearchForm: ${searchQueryState.value.trim()}")
                searchQueryState.value = ""
                keyboardController?.hide()
                focusManager.clearFocus()

            } ,
            onLeadingIconClick = {
                scope.launch {
                    drawerState.apply {
                        if(isClosed) open() else close()
                    }
                }
            },
            onTrailingIconCompositionClick = {
                TODO("composition icon")
            },
            onTrailingIconProfileClick = {
                TODO("PROFILE ICON")
            })

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier : Modifier = Modifier ,
    valueState : MutableState<String> ,
    labelId : String ,
    enabled : Boolean ,
    isSingleLine : Boolean = true ,
    keyboardType : KeyboardType = KeyboardType.Text ,
    imeAction : ImeAction = ImeAction.Next ,
    onAction : KeyboardActions = KeyboardActions.Default ,
    onLeadingIconClick : () -> Unit = {},
    onTrailingIconCompositionClick : () -> Unit ={},
    onTrailingIconProfileClick : () -> Unit ={}
) {
    OutlinedTextField(
        value = valueState.value ,
        onValueChange = { valueState.value = it } ,
        label = { Text(text = labelId) } ,
        singleLine = isSingleLine ,
        textStyle = TextStyle(fontSize = 18.sp , color = MaterialTheme.colorScheme.onBackground) ,
        modifier =modifier
            .padding(10.dp , 10.dp , 10.dp)
            .fillMaxWidth() ,
        enabled = enabled ,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType , imeAction = imeAction) ,
        keyboardActions = onAction ,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Dehaze ,
                contentDescription = "menu " ,
                modifier = modifier.clickable {
                    onLeadingIconClick.invoke()
                })
        },
        trailingIcon = {
            Row {
                Icon(
                    imageVector=Icons.Filled.Dataset ,
                    contentDescription="notes composition" ,
                    modifier=Modifier
                        .padding(top=15.dp , bottom=15.dp)
                        .clickable {
                            onTrailingIconCompositionClick.invoke()
                        }
                )
                Icon(
                    imageVector=Icons.Filled.AccountCircle ,
                    contentDescription="profile" ,
                    modifier=Modifier
                        .padding(
                            top=15.dp ,
                            bottom=15.dp ,
                            end=15.dp ,
                            start=20.dp
                        )
                        .clickable {
                            onTrailingIconProfileClick.invoke()
                        }
                )
            }
        }
    )
}

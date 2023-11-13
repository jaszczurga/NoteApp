package com.example.googlenotesclone.screens.HomeScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.googlenotesclone.components.AddButton
import com.example.googlenotesclone.components.NoteCard
import com.example.googlenotesclone.navigation.NotesAppScreens
import com.google.android.material.bottomappbar.BottomAppBar.FabAlignmentMode
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
        HomeContent(drawerState = drawerState, navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class , ExperimentalComposeUiApi::class)
@Composable
fun HomeContent(drawerState : DrawerState ,navController : NavHostController, modifier : Modifier = Modifier)  {
    val notes = listOf(
        "Notatka 1: To jest długa notatka o czymś ważnym. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eget ante vel purus cursus fermentum. Sed vitae fermentum dolor. Vestibulum auctor, libero et fermentum scelerisque, nisl ligula hendrerit risus, in venenatis libero turpis ut sem.",
        "Notatka 2: Krótka notatka.",
        "Notatka 3: Inna ważna notatka. Vivamus at urna ligula. In hac habitasse platea dictumst. Proin facilisis odio non lacus venenatis, eu consectetur odio gravida. Sed eu malesuada elit.",
        "Notatka 4: To jest jeszcze jedna długa notatka na temat czegoś. Quisque vitae orci ut odio luctus varius. Nulla facilisi. Curabitur vel tellus vel lacus feugiat vulputate.",
        "Notatka 4: To jest jeszcze jedna długa notatka na temat czegoś. Quisque vitae orci ut odio luctus varius. Nulla facilisi. Curabitur vel tellus vel lacus feugiat vulputate.",
        "Notatka 4: To jest jeszcze jedna długa notatka na temat czegoś. Quisque vitae orci ut odio luctus varius. Nulla facilisi. Curabitur vel tellus vel lacus feugiat vulputate.",
        "Notatka 4: To jest jeszcze jedna długa notatka na temat czegoś. Quisque vitae orci ut odio luctus varius. Nulla facilisi. Curabitur vel tellus vel lacus feugiat vulputate.",
        "Notatka 5: Krótka notatka.",
        "Notatka 6: Notatka o czymś.",
        "Notatka 7: To jest bardzo krótka notatka. Duis vehicula mauris vel lectus dignissim varius. Fusce suscipit augue non bibendum ullamcorper. Suspendisse potenti. Integer et bibendum quam.",
        "Notatka 8: Długa notatka z ważnymi informacjami. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean fermentum facilisis eros a tincidunt.",
        "Notatka 9: Jeszcze jedna krótka notatka. Sed auctor risus nec diam luctus, sit amet sollicitudin justo fermentum. Nullam in metus et risus suscipit ultricies. Suspendisse potenti."
    )
    Scaffold(
        bottomBar = {
            BottomAppBar()
        },
        floatingActionButton = {
            AddButton(onClickButton = {
                navController.navigate(NotesAppScreens.NoteScreen.name)
            })
        }
    ) { contentPadding ->
        Log.d("HomeScreenPadding" , "HomeScreen padding:${contentPadding} ")
        // Screen content
        Column(modifier.fillMaxSize()) {
            SearchForm(drawerState = drawerState)
            Spacer(modifier = Modifier.height(15.dp))
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 7.dp,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                content = {
                    items(items=notes) { t ->
                        NoteCard(text = t)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            )
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
        modifier = modifier
            .padding(10.dp , 10.dp , 10.dp)
            .fillMaxWidth(),
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
                    modifier= Modifier
                        .padding(top = 15.dp , bottom = 15.dp)
                        .clickable {
                            onTrailingIconCompositionClick.invoke()
                        }
                )
                Icon(
                    imageVector=Icons.Filled.AccountCircle ,
                    contentDescription="profile" ,
                    modifier= Modifier
                        .padding(
                            top = 15.dp ,
                            bottom = 15.dp ,
                            end = 15.dp ,
                            start = 20.dp
                        )
                        .clickable {
                            onTrailingIconProfileClick.invoke()
                        }
                )
            }
        }
    )
}

@Composable
fun BottomAppBar(
    onClickCheckBoxIcon : ()->Unit = {},
    onClickBrushIcon : ()->Unit = {},
    onClickMicIcon : ()->Unit = {},
    onClickPhotoIcon : ()->Unit = {},
) {
    BottomAppBar(
        modifier = Modifier.height(50.dp),
        actions = {
            IconButton(onClick = { onClickCheckBoxIcon.invoke() }) {
                Icon(Icons.Filled.CheckBox,
                    contentDescription = "checkbox note")
            }
            IconButton(onClick = { onClickBrushIcon.invoke() }) {
                Icon(
                    Icons.Filled.Brush,
                    contentDescription = "draw some note",
                )
            }
            IconButton(onClick = { onClickMicIcon.invoke() }) {
                Icon(
                    Icons.Filled.Mic,
                    contentDescription = "micro",
                )
            }
            IconButton(onClick = { onClickPhotoIcon.invoke() }) {
                Icon(
                    Icons.Filled.Photo,
                    contentDescription = "picture",
                )
            }

        }
    )
}




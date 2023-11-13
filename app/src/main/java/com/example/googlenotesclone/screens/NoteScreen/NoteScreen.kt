package com.example.googlenotesclone.screens.NoteScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.TypeSpecimen
import androidx.compose.material.icons.filled.UTurnLeft
import androidx.compose.material.icons.filled.UTurnRight
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(navController : NavHostController) {
    Scaffold(
        topBar = {
                 NoteTopAppBar()
        },
        bottomBar = {
                    NoteBottomBar()
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val list = (0..75).map { it.toString() }
                items(count = list.size) {
                    Text(
                        text = list[it],
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTopAppBar() {
    TopAppBar(
        title = {
            Text(
                "Simple TopAppBar" ,
                maxLines = 1 ,
                overflow = TextOverflow.Ellipsis
            )
        } ,
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu ,
                    contentDescription = "Localized description"
                )
            }
        } ,
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Favorite ,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Preview
@Composable
fun NoteBottomBar(
    onClickAddBoxIcon : ()->Unit = {},
    onClickPaletteIcon : ()->Unit = {},
    onClickMicIcon : ()->Unit = {},
    onClickPhotoIcon : ()->Unit = {},
    onClickButton : ()->Unit={}
) {
    BottomAppBar(
        modifier = Modifier.height(50.dp),
        actions = {
            IconButton(onClick = { onClickAddBoxIcon.invoke() }) {
                Icon(Icons.Filled.AddBox,
                    contentDescription = "add some fancy functions like micro")
            }
            IconButton(onClick = { onClickPaletteIcon.invoke() }) {
                Icon(
                    Icons.Filled.Palette,
                    contentDescription = "some color change",
                )
            }
            IconButton(onClick = { onClickMicIcon.invoke() }) {
                Icon(
                    Icons.Filled.TypeSpecimen,
                    contentDescription = "typography changes",
                )
            }
            Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                IconButton(
                    onClick = { onClickPhotoIcon.invoke() }) {
                    Icon(
                        Icons.Filled.UTurnLeft ,
                        contentDescription = "undochanges" ,
                        modifier = Modifier.graphicsLayer(rotationZ = 90f)
                    )
                }
                IconButton(
                    onClick = { onClickPhotoIcon.invoke() }) {
                    Icon(
                        Icons.Filled.UTurnRight ,
                        contentDescription = "undochanges" ,
                        modifier = Modifier.graphicsLayer(rotationZ = 270f)
                    )
                }
            }

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClickButton.invoke() },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }
    )
}
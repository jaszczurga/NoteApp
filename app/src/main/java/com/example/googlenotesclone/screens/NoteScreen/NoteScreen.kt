package com.example.googlenotesclone.screens.NoteScreen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.NotificationAdd
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.TypeSpecimen
import androidx.compose.material.icons.filled.UTurnLeft
import androidx.compose.material.icons.filled.UTurnRight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.googlenotesclone.MainViewModel
import com.example.googlenotesclone.data.ROOM.Note
import com.example.googlenotesclone.navigation.NoteDestinations


//
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(navController : NavHostController = NavHostController(LocalContext.current),viewModel : NoteScreenViewModel= hiltViewModel(),noteId : Int) {
    Log.d("NoteViewModel" , "NoteScreen comp: ${noteId}")

    /////////wrocic tuu
    if(noteId!=-1) {
        viewModel.setId(noteId)
    }
    val nt = viewModel.getNote().note.title
    val nd = viewModel.getNote().note.description
    Log.d("ntandnd" , "NoteScreen: ${nt} ${nd}")

    val noteText = remember{mutableStateOf(nt)}
    val noteName = remember{mutableStateOf(nd)}


    BackHandler(noteName.value.toString().trim().isNotEmpty() || noteText.value.toString().trim().isNotEmpty()){
        navController.navigate(NoteDestinations.HOME_ROUTE)
            if(noteId!=-1)
                viewModel.insertNote(
                    Note(
                        id = noteId.toLong() ,
                        title = noteName.value.toString() ,
                        description = noteText.value.toString()
                    )
                )
            else
                viewModel.addNote(
                    Note(
                        title = noteName.value.toString() ,
                        description = noteText.value.toString()
                    )
                )
    }
    Scaffold(
        topBar = {
            NoteTopAppBar(onBackArrowClick = {
                navController.navigate(NoteDestinations.HOME_ROUTE)
                if(noteName.value.toString().trim().isNotEmpty() || noteText.value.toString().trim().isNotEmpty() ) {
                    if(noteId!=-1)
                        viewModel.updateNote(
                            Note(
                                id = noteId.toLong() ,
                                title = noteName.value.toString() ,
                                description = noteText.value.toString()
                            )
                        )
                    else
                        viewModel.addNote(
                            Note(
                                title = noteName.value.toString() ,
                                description = noteText.value.toString()
                            )
                        )
                }
            })
        } ,
        bottomBar = {
            NoteBottomBar()
        } ,
        content = { innerPadding ->
            Log.d("NoteScreenPadding" , "NoteScreen: padding ${innerPadding}")
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                NoteInput(
                    valueState = noteName ,
                    enabled = true , modifier = Modifier.padding(top = innerPadding.calculateTopPadding()) ,
                    placeholder = {
                        Text(
                            text = noteName.value ,
                            fontSize = 25.sp
                        )
                    } ,
                    textsize = 24.sp,
                    imeAction = ImeAction.Next
                )
                NoteInput(
                    valueState = noteText ,
                    enabled = true ,
                    placeholder = {
                        Text(
                            text = noteText.value ,
                            fontSize = 17.sp
                        )
                    } ,
                    textsize = 17.sp
                )
            }
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTopAppBar(
    onBackArrowClick : ()->Unit={},
    onPinClick : ()->Unit={},
    onNotificationClick : ()->Unit={},
    onArchiveClick : ()->Unit={}
) {
    TopAppBar(
        title = {} ,
        navigationIcon = {
            IconButton(onClick = { onBackArrowClick.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardBackspace ,
                    contentDescription = "Localized description"
                )
            }
        } ,
        actions = {
            IconButton(onClick = { onPinClick.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.PushPin ,
                    contentDescription = "pinned"
                )
            }
            IconButton(onClick = { onNotificationClick.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.NotificationAdd ,
                    contentDescription = "add notification"
                )
            }
            IconButton(onClick = { onArchiveClick.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.Archive ,
                    contentDescription = "archive"
                )
            }
        }
    )
}

@Preview
@Composable
fun NoteBottomBar(
    onClickAddBoxIcon : () -> Unit={} ,
    onClickPaletteIcon : () -> Unit={} ,
    onClickTypeSpecimen : () -> Unit={} ,
    onClickUnDo : () -> Unit={} ,
    onClickReDo : () -> Unit={} ,
    onClickMore : () -> Unit={} ,
) {
    BottomAppBar(
        modifier=Modifier.height(50.dp) ,
        actions={
            Row {
                IconButton(onClick={ onClickAddBoxIcon.invoke() }) {
                    Icon(
                        Icons.Filled.AddBox ,
                        contentDescription="add some fancy functions like micro"
                    )
                }
                IconButton(onClick={ onClickPaletteIcon.invoke() }) {
                    Icon(
                        Icons.Filled.Palette ,
                        contentDescription="some color change" ,
                    )
                }
                IconButton(onClick={ onClickTypeSpecimen.invoke() }) {
                    Icon(
                        Icons.Filled.TypeSpecimen ,
                        contentDescription="typography changes" ,
                    )
                }
                Row(modifier=Modifier.padding(horizontal=20.dp)) {
                    IconButton(
                        onClick={ onClickUnDo.invoke() }) {
                        Icon(
                            Icons.Filled.UTurnLeft ,
                            contentDescription="undochanges" ,
                            modifier=Modifier.graphicsLayer(rotationZ=90f)
                        )
                    }
                    IconButton(
                        onClick={ onClickReDo.invoke() }) {
                        Icon(
                            Icons.Filled.UTurnRight ,
                            contentDescription="redo changes" ,
                            modifier=Modifier.graphicsLayer(rotationZ=270f)
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick={ onClickMore.invoke() }) {
                        Icon(
                            Icons.Default.MoreVert ,
                            contentDescription="miedzy innymi usun",
                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteInput(
    modifier : Modifier = Modifier ,
    valueState : MutableState<String> ,
    placeholder : @Composable ()->Unit=  {Text(text="PLACEHOLDER")} ,
    enabled : Boolean ,
    isSingleLine : Boolean = false ,
    keyboardType : KeyboardType= KeyboardType.Text ,
    imeAction : ImeAction= ImeAction.None ,
    onAction : KeyboardActions= KeyboardActions.Default ,
    textsize : TextUnit = 20.sp
) {
    TextField(
        value = valueState.value ,
        onValueChange = { valueState.value = it } ,
        placeholder = {
            placeholder()
                      },
        singleLine = isSingleLine ,
        textStyle = TextStyle(fontSize = textsize , color = MaterialTheme.colorScheme.onBackground) ,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        enabled = enabled ,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType , imeAction = imeAction) ,
        keyboardActions = onAction,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor=Color.White ,
            containerColor=MaterialTheme.colorScheme.background ,
            unfocusedIndicatorColor=MaterialTheme.colorScheme.background ,
            focusedIndicatorColor=MaterialTheme.colorScheme.background
        )
    )
}
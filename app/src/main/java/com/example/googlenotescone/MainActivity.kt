package com.example.googlenotescone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.googlenotescone.ui.theme.GoogleNotesConeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleNotesConeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize() ,
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name : String , modifier : Modifier = Modifier) {
    Text(
        text = "Hello $name!" ,
        modifier = modifier
    )
}


@Composable
fun NotesApp() {
    GoogleNotesConeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier=Modifier.fillMaxSize() , color=MaterialTheme.colorScheme.background
        ) {
            Greeting(
                "Android"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoogleNotesConeTheme {
        Greeting("Android")
    }
}
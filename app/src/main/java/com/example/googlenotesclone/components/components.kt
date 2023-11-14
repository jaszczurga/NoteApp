package com.example.googlenotesclone.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddButton(onClickButton : ()->Unit={}) {
    FloatingActionButton(
        onClick = { onClickButton.invoke() },
        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),

        ) {
        Icon(Icons.Filled.Add, "Localized description")
    }
}
@Composable
fun NoteCard(name:String,description:String) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface ,
        ) ,
        border = BorderStroke(1.dp , Color.Gray) ,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            text = name.trim(),
            modifier = Modifier
                .padding(16.dp) ,
            textAlign = TextAlign.Center ,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 16.sp,
            fontSize = 18.sp
        )
        Text(
            text = description.trim(),
            modifier = Modifier
                .padding(16.dp) ,
            textAlign = TextAlign.Center ,
            maxLines = 9,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 14.sp,
            fontSize = 13.sp
        )
    }
}

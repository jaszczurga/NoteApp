package com.example.googlenotesclone.data.ROOM

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
data class Note(

    /// przemyslec dodanie UUID oraz jakas zmiana date wtedy typeconverter w databse
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long=0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    //TODO(dodac date converter!!!)
//    @ColumnInfo(name = "date")
//    val date: Date=Date()


)
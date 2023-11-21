package com.example.googlenotesclone.data.ROOM

import android.icu.util.Calendar
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date


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

    @ColumnInfo(name = "is_archived")
    val isArchived: Boolean=false,

    @ColumnInfo(name = "is_deleted")
    val isDeleted: Boolean=false,

    //TODO("dodac do UI")
    @ColumnInfo(name = "date")
    val date :String= Calendar.getInstance().time.toString()


)
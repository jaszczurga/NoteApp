package com.example.googlenotesclone.ROOM

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database
(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val dao: NoteDao
}
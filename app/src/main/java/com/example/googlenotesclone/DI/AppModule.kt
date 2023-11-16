package com.example.googlenotesclone.DI

import android.content.Context
import androidx.room.Room
import com.example.googlenotesclone.data.ROOM.NoteDao
import com.example.googlenotesclone.data.ROOM.NoteDatabase
import com.example.googlenotesclone.data.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase : NoteDatabase) : NoteDao = noteDatabase.dao

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context : Context) : NoteDatabase =
        Room.databaseBuilder(context , NoteDatabase::class.java , "notes_db")
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepository(noteDao)

}
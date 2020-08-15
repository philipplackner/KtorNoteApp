package com.androiddevs.ktornoteapp.data.local

import androidx.room.Database
import androidx.room.TypeConverters
import com.androiddevs.ktornoteapp.data.local.entities.Note

@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NotesDatabase {

    abstract fun noteDao(): NoteDao
}
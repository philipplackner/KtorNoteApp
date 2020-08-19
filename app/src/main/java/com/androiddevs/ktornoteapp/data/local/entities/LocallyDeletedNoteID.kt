package com.androiddevs.ktornoteapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locally_deleted_note_ids")
data class LocallyDeletedNoteID(
    @PrimaryKey(autoGenerate = false)
    val deletedNoteID: String
)
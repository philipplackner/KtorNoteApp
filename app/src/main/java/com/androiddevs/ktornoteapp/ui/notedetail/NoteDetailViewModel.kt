package com.androiddevs.ktornoteapp.ui.notedetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.androiddevs.ktornoteapp.repositories.NoteRepository

class NoteDetailViewModel @ViewModelInject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    fun observeNoteByID(noteID: String) = repository.observeNoteByID(noteID)
}
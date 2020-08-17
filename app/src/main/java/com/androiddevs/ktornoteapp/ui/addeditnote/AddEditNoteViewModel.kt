package com.androiddevs.ktornoteapp.ui.addeditnote

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.ktornoteapp.data.local.entities.Note
import com.androiddevs.ktornoteapp.other.Event
import com.androiddevs.ktornoteapp.other.Resource
import com.androiddevs.ktornoteapp.repositories.NoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddEditNoteViewModel @ViewModelInject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _note = MutableLiveData<Event<Resource<Note>>>()
    val note: LiveData<Event<Resource<Note>>> = _note

    fun insertNote(note: Note) = GlobalScope.launch {
        repository.insertNote(note)
    }

    fun getNoteById(noteID: String) = viewModelScope.launch {
        _note.postValue(Event(Resource.loading(null)))
        val note = repository.getNoteById(noteID)
        note?.let {
            _note.postValue(Event(Resource.success(it)))
        } ?: _note.postValue(Event(Resource.error("Note not found", null)))
    }
}

















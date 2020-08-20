package com.androiddevs.ktornoteapp.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.androiddevs.ktornoteapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import kotlinx.android.synthetic.main.fragment_note_detail.*

class AddOwnerDialog : DialogFragment() {

    private var positiveListener: ((String) -> Unit)? = null

    fun setPositiveListener(listener: (String) -> Unit) {
        positiveListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val addOwnerEditText = LayoutInflater.from(requireContext()).inflate(
            R.layout.edit_text_email,
            clNoteContainer,
            false
        ) as TextInputLayout
        return MaterialAlertDialogBuilder(requireContext())
            .setIcon(R.drawable.ic_add_person)
            .setTitle("Add Owner to Note")
            .setMessage("Enter an E-Mail of a person you want to share the note with." +
                    "This person will be able to read and edit the note.")
            .setView(addOwnerEditText)
            .setPositiveButton("Add") { _, _ ->
                val email = addOwnerEditText.findViewById<EditText>(R.id.etAddOwnerEmail).text.toString()
                positiveListener?.let { yes ->
                    yes(email)
                }
            }
            .setNegativeButton("Cancel") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
    }
}















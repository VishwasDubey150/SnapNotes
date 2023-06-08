package com.example.snapnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.snapnote.databinding.ActivityAddNotesBinding
import java.text.SimpleDateFormat
import java.util.*

class add_notes : AppCompatActivity() {
    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var note: Note
    private lateinit var old_note: Note
    lateinit var viewModel: NoteVIewModel
    var isUpdate = false
    var noteID=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteVIewModel::class.java)

        val noteType = intent.getStringExtra("noteType")

        if(noteType.equals("Edit"))
        {
            val noteTitle = intent.getStringExtra("noteTitle")
            val notedesc = intent.getStringExtra("noteDescription")
            noteID=intent.getIntExtra("noteID",-1)
            binding.etTitle.setText(noteTitle)
            binding.etDes.setText(notedesc)

        }
        binding.done.setOnClickListener {
            val note_title = binding.etTitle.text.toString()
            val note_desc = binding.etDes.text.toString()

            if (title.isNotEmpty() || note_desc.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM, yyyy -HH:mm ")

                val currentDate: String = sdf.format(Date())
                viewModel.addNote(Note(note_title, note_desc, currentDate))
                Toast.makeText(this,"Note added..", Toast.LENGTH_SHORT).show()
            }

            startActivity(Intent(applicationContext,MainActivity::class.java))
            this
        }


        fun back(view: View) {
            startActivity(Intent(this@add_notes,MainActivity::class.java))
        }
    }
}
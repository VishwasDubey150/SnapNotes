package com.example.snapnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.snapnote.databinding.ActivityAddNotesBinding.inflate
import com.example.snapnote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),NoteClickDeleteInterface, NoteClickInterface {
    lateinit var binding:ActivityMainBinding
    private lateinit var database: NoteDatabase
    lateinit var adapter: NotesAdapter
    lateinit var selectedNote: Note
    lateinit var viewModel: NoteVIewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        adapter = NotesAdapter(this, this,this)
        binding.rv.adapter = adapter



        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteVIewModel::class.java)

        viewModel.allnotes.observe(this) { list ->
            list?.let {
                adapter.updateList(list)
            }
        }

        database = NoteDatabase.getDatabase(this)

    }


    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"Note Deleted..", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(note: Note) {
        val intent= Intent(this@MainActivity,add_notes::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.notTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteId",note.id)
        startActivity(intent)
        this.finish()
    }

    fun add(view: View) {
        startActivity(Intent(this@MainActivity,add_notes::class.java))
        finish()
    }
}
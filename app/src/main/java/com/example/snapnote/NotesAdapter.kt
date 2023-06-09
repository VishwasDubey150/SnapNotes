package com.example.snapnote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter (private val context : Context,val noteClickInterface: NoteClickInterface,
val noteClickDeleteInterface: NoteClickDeleteInterface) :
RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val deleteIV=itemView.findViewById<ImageView>(R.id.delete)
        val noteTV=itemView.findViewById<TextView>(R.id.title)
        val desTV=itemView.findViewById<TextView>(R.id.desciption)
        val timeTV=itemView.findViewById<TextView>(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.noteTV.text=currentNote.notTitle

        holder.timeTV.text=currentNote.timestamp
        holder.desTV.text=currentNote.noteDescription




        holder.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }


    fun updateList(newList:List<Note>)
    {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface NoteClickDeleteInterface{
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface{
    fun onNoteClick(note: Note)

}
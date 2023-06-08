package com.example.snapnote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class Note(
    @ColumnInfo(name = "title") val notTitle : String?,
    @ColumnInfo(name = "description") val noteDescription : String?,
    @ColumnInfo(name = "timestamp") val timestamp : String?
): java.io.Serializable
{
    @PrimaryKey(autoGenerate = true) var id: Int =0
}
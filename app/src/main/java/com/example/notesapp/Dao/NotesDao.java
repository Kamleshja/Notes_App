package com.example.notesapp.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesapp.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_Database")
    LiveData<List<Notes>>  getallNotes();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority DESC")
    LiveData<List<Notes>>  highToLow();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority ASC")
    LiveData<List<Notes>>  lowToHigh();

    @Insert
    void insertNotes(Notes... notes);

    @Query("Delete from Notes_Database where id=:id")
    void deleteNote(int id);

    @Update
    void updatesNotes(Notes notes);

}

package com.example.notesapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesapp.Dao.NotesDao;
import com.example.notesapp.Database.NotesDatabase;
import com.example.notesapp.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;

    public LiveData<List<Notes>> getallNotes;

    public NotesRepository(Application application){
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getallNotes = notesDao.getallNotes();
    }

    public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }
    public void DeleteNotes(int id){
        notesDao.deleteNote(id);
    }
    public void updateNotes(Notes notes){
        notesDao.updatesNotes(notes);
    }
}

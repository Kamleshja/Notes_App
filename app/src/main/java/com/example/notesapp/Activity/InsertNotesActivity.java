package com.example.notesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.notesapp.R;
import com.example.notesapp.ViewModel.NotesViewModal;
import com.example.notesapp.databinding.ActivityInsertNotesBinding;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title,subtitle,notes;
    NotesViewModal notesViewModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModal = ViewModelProviders.of(this).get(NotesViewModal.class);

        binding.doneNotesBtn.setOnClickListener(v ->{

            title = binding.notesTitle.getText().toString();
            subtitle = binding.subTitle.getText().toString();
            notes = binding.notesData.getText().toString();
        });
    }
}
package com.example.notesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.notesapp.Model.Notes;
import com.example.notesapp.R;
import com.example.notesapp.ViewModel.NotesViewModal;
import com.example.notesapp.databinding.ActivityInsertNotesBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title,subtitle,notes;
    NotesViewModal notesViewModal;
    String priority="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){

            setTheme(R.style.NightMode);
        }else{
            setTheme(R.style.Theme_NotesApp);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModal = ViewModelProviders.of(this).get(NotesViewModal.class);

        binding.greenPriority.setOnClickListener(view -> {

            binding.greenPriority.setImageResource(R.drawable.done);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            priority = "1";
        });
        binding.yellowPriority.setOnClickListener(view -> {

            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.done);
            binding.redPriority.setImageResource(0);
            priority = "2";
        });
        binding.redPriority.setOnClickListener(view -> {

            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.done);
            priority = "3";
        });

        binding.doneNotesBtn.setOnClickListener(v ->{

            title = binding.notesTitle.getText().toString();
            subtitle = binding.subTitle.getText().toString();
            notes = binding.notesData.getText().toString();

            CreateNotes(title,subtitle,notes);
        });

//        if(AppCompatDelegate.getDefaultNightMode() ==AppCompatDelegate.MODE_NIGHT_YES){
//            binding.ThemeSwitch.setImageResource(R.drawable.light);
//        }else{
//            binding.ThemeSwitch.setImageResource(R.drawable.night);
//        }
//
//        binding.ThemeSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(AppCompatDelegate.getDefaultNightMode() ==AppCompatDelegate.MODE_NIGHT_YES){
//                    binding.ThemeSwitch.setImageResource(R.drawable.light);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                }else{
//                    binding.ThemeSwitch.setImageResource(R.drawable.night);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                }
//                restartApp();
//            }
//        });
//

    }

    private void CreateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence =  DateFormat.getDateInstance().format(date.getTime());
        //String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubTitle = subtitle;
        notes1.notes = notes;
        notes1.notesPriority = priority;
        notes1.notesDate = sequence.toString();
        notesViewModal.insertNote(notes1);
        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();
        Log.d("MY",notes1.toString());
        finish();
    }

//    private void restartApp(){
//        //Go to the same class as Intent
//        //With setFlags, the application is prevented from being a black screen.
//        Intent i = new Intent(getApplicationContext(), InsertNotesActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(i);
//        finish();
//
//
//    }
}
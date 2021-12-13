package com.example.notesapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.Model.Notes;
import com.example.notesapp.R;
import com.example.notesapp.ViewModel.NotesViewModal;
import com.example.notesapp.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    String priority = "1";
    String stitle,ssubtitle,snotes,sPriority;
    NotesViewModal notesViewModal;
    int iid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModal = ViewModelProviders.of(this).get(NotesViewModal.class);

        iid = getIntent().getIntExtra("id",0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        snotes = getIntent().getStringExtra("notes");
        sPriority = getIntent().getStringExtra("priority");

        binding.upTitle.setText(stitle);
        binding.upsubTitle.setText(ssubtitle);
        binding.upnotes.setText(snotes);

        if (sPriority.equals("1")){
            binding.greenPriority.setImageResource(R.drawable.done);
        }else if(sPriority.equals("2")){
            binding.yellowPriority.setImageResource(R.drawable.done);
        }else if(sPriority.equals("3")){
            binding.redPriority.setImageResource(R.drawable.done);
        }


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

        binding.updateNoteBtn.setOnClickListener(view -> {

           String title = binding.upTitle.getText().toString();
           String subtitle = binding.upsubTitle.getText().toString();
           String notes = binding.upnotes.getText().toString();

            UpdateNotes(title,subtitle,notes);
        });
    }

    private void UpdateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence =  DateFormat.getDateInstance().format(date.getTime());
        Notes updateNotes = new Notes();
        updateNotes.id = iid;
        updateNotes.notesTitle = title;
        updateNotes.notesSubTitle = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesPriority = priority;
        updateNotes.notesDate = sequence.toString();
        notesViewModal.updateNote(updateNotes);
        Toast.makeText(this, "Notes Updated Successfully", Toast.LENGTH_SHORT).show();
        Log.d("MY",updateNotes.toString());
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.ic_delete){
            Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotesActivity.this,R.style.BottomSheetStyle);
            View view = LayoutInflater.from(UpdateNotesActivity.this)
                    .inflate(R.layout.delete_bottom_sheet,(LinearLayout) findViewById(R.id.bottomSheet));

            sheetDialog.setContentView(view);

            TextView yes,no;

            yes = view.findViewById(R.id.delete_yes);
            no = view.findViewById(R.id.delete_no);

            yes.setOnClickListener(view1 -> {
                notesViewModal.deleteNote(iid);
                finish();

            });
            no.setOnClickListener(view1 -> {
                sheetDialog.dismiss();
            });

            sheetDialog.show();
        }

        return true;
    }
}
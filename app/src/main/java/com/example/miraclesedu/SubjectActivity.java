package com.example.miraclesedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btnAddSubjects;

    DatabaseClass db;

    ArrayList<String> subjectCode, subjectName, subjectMarks, creditValue;
    CustomAdapter customAdapter;

    @Override
    protected void onResume() {
        super.onResume();

        customAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(SubjectActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        recyclerView = findViewById(R.id.rvSubjectList);
        btnAddSubjects = findViewById(R.id.btnAddSubjects);

        db = new DatabaseClass(SubjectActivity.this);
        subjectCode = new ArrayList<>();
        subjectName = new ArrayList<>();
        subjectMarks = new ArrayList<>();
        creditValue = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(this, subjectCode, subjectName, subjectMarks, creditValue);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAddSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubjectActivity.this, AddSubjectActivity.class);
                startActivity(intent);
                finish();

            }
        });



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    btnAddSubjects.hide();
                } else {
                    btnAddSubjects.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }


    public void storeDataInArrays() {
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(SubjectActivity.this, "No Data", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                subjectCode.add(cursor.getString(0));
                subjectName.add(cursor.getString(1));
                subjectMarks.add(cursor.getString(2));
                creditValue.add(cursor.getString(3));

            }

        }
    }



}
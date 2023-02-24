package com.example.miraclesedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSubjectActivity extends AppCompatActivity {

    EditText subjectCode, subjectName, subjectMarks, creditValue;
    Button btnInsert;


    @Override
    public void onBackPressed() {
        super.onBackPressed();


        Intent intent = new Intent(AddSubjectActivity.this, SubjectActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsubject);

        subjectCode = findViewById(R.id.txtSubjectCode);
        subjectName = findViewById(R.id.txtSubjectName);
        subjectMarks = findViewById(R.id.txtSubjectMarkse);
        creditValue = findViewById(R.id.txtCreditValue);
        btnInsert = findViewById(R.id.btnInsertSubject);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(subjectCode.getText().toString()) || TextUtils.isEmpty(subjectName.getText().toString()) ||
                        TextUtils.isEmpty(subjectMarks.getText().toString()) ||TextUtils.isEmpty(creditValue.getText().toString())){
                    Toast.makeText(AddSubjectActivity.this, "Please Enter Data.", Toast.LENGTH_SHORT).show();

                }
                else {

                    DatabaseClass db = new DatabaseClass(AddSubjectActivity.this);
                    db.addSubjects(subjectCode.getText().toString().trim(),
                            subjectName.getText().toString().trim(),
                            subjectMarks.getText().toString().trim(),
                            Integer.valueOf(creditValue.getText().toString().trim()));

                    subjectCode.setText(null);
                    subjectName.setText(null);
                    subjectMarks.setText(null);
                    creditValue.setText(null);
                    subjectCode.requestFocus();

                }

            }



        });




    }
}
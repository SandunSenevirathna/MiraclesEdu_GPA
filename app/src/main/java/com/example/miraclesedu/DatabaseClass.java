package com.example.miraclesedu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DatabaseClass extends SQLiteOpenHelper {

    private  Context context;
    private  static final String DatabaseName = "MiraclesEdu.db";
    private static final int DatabaseVersion = 1;
    private  static final String TableName = "SubjectTable";
    private  static final String SubjectCode = "sCode";
    private  static final String SubjectName = "sName";
    private  static final String SubjectMarks = "sMarks";
    private  static final String CreditValue = "cValues";

    public DatabaseClass(@Nullable Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String quary = "CREATE TABLE " + TableName +
                        " (" + SubjectCode + " TEXT PRIMARY KEY, " +
                        SubjectName + " TEXT, " +
                        SubjectMarks + " TEXT, " +
                        CreditValue + " INTEGER);";
        db.execSQL(quary);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    public void deleteRow(String sCode) {
        SQLiteDatabase db = getWritableDatabase();
        long result = db.delete(TableName, "sCode=?", new String[]{String.valueOf(sCode)});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();

        }

        db.close();

    }


    void addSubjects(String subjectCode, String subjectName, String subjectMarks, Integer creditValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SubjectCode, subjectCode);
        cv.put(SubjectName, subjectName);
        cv.put(SubjectMarks, subjectMarks);
        cv.put(CreditValue, creditValue);

        long result = db.insert(TableName,null, cv);
        if(result == -1){
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Insert Successfully", Toast.LENGTH_SHORT).show();


        }

    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

}

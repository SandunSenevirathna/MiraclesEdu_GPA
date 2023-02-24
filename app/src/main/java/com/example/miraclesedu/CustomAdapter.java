package com.example.miraclesedu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import javax.security.auth.Subject;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList subjectCode, subjectName, subjectMarks, creditValue;



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView lblSubjectCode, lblSubjectName, lblSubjectMarks, lblCreditValue;
        LinearLayout linearLayout;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            lblSubjectCode = itemView.findViewById(R.id.lblSubjectCode);
            lblSubjectName = itemView.findViewById(R.id.lblSubjectName);
            lblSubjectMarks = itemView.findViewById(R.id.lblSubjectMarks);
            lblCreditValue = itemView.findViewById(R.id.lblCreditValue);
            linearLayout = itemView.findViewById(R.id.mainLayout);
            cardView = itemView.findViewById(R.id.CardLayout);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

        }
    }
   CustomAdapter(Context context, ArrayList subjectCode, ArrayList subjectName, ArrayList subjectMarks, ArrayList creditValue){

        this.context = context;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectMarks = subjectMarks;
        this.creditValue = creditValue;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_row, parent, false);
        return  new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.lblSubjectCode.setText(String.valueOf(subjectCode.get(position)));
        holder.lblSubjectName.setText(String.valueOf(subjectName.get(position)));
        holder.lblSubjectMarks.setText(String.valueOf(subjectMarks.get(position)));
        holder.lblCreditValue.setText(String.valueOf(creditValue.get(position)));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectCode.size();
    }



    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete item");
        builder.setMessage("Are you sure to delete this " + subjectName + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseClass database = new DatabaseClass(context);
                database.deleteRow(String.valueOf(subjectCode.get(position)));
                subjectCode.remove(position);
                subjectName.remove(position);
                subjectMarks.remove(position);
                creditValue.remove(position);
                notifyItemRemoved(position);
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }




   }

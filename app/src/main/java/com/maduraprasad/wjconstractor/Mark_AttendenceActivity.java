package com.maduraprasad.wjconstractor;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.maduraprasad.wjconstractor.DataBase.Admin;
import com.maduraprasad.wjconstractor.DataBase.DataHandler;
import com.maduraprasad.wjconstractor.DataBase.Mark_Attendance;

import java.util.Calendar;

import static maes.tech.intentanim.CustomIntent.customType;

public class Mark_AttendenceActivity extends AppCompatActivity {
    TextInputLayout masonry_name, masonry_attendance;
    TextInputEditText edit_masonry_name, edit_attendance;
    String id, name;
    DataHandler dataHandler = new DataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark__attendence);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //initialize variable
        masonry_name = findViewById(R.id.masonry_name2);
        masonry_attendance = findViewById(R.id.masonry_attendance2);
        edit_masonry_name = findViewById(R.id.edit_masonry_name2);
        edit_attendance = findViewById(R.id.edit_masonry_attendance2);
        getAndSetIntentDataMasonry();

        //database
        dataHandler.openDB();

        //calender
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edit_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Mark_AttendenceActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        edit_attendance.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    void getAndSetIntentDataMasonry() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");

            //Setting Intent Data
            edit_masonry_name.setText(name);

        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    //name validation
    private boolean validUsername() {
        String fname = masonry_name.getEditText().getText().toString().trim();
        String vname = "^[A-Za-z]\\w{5,29}$";

        if (fname.isEmpty()) {
            masonry_name.setError("Username is Empty.");
            return false;
        } else if (!fname.matches(vname)) {
            masonry_name.setError("Invalid Name.");
            return false;
        } else {
            masonry_name.setError(null);
            return true;
        }

    }

    //attendance validation
    private boolean validAttendance() {
        String attendance = masonry_attendance.getEditText().getText().toString().trim();


        if (attendance.isEmpty()) {
            masonry_attendance.setError("Attendance is Empty.");
            return false;
        } else {
            masonry_attendance.setError(null);
            return true;
        }

    }

    public void mark(View view) {
        if (!validUsername() | !validAttendance()) {
            return;
        }
        String fname = masonry_name.getEditText().getText().toString().trim();
        String attendance = masonry_attendance.getEditText().getText().toString().trim();

        //if (!dataHandler.checkAttendanceName(name)) {
            Mark_Attendance mark_attendance = new Mark_Attendance(fname,attendance );
            try {
                dataHandler.Mark_Attendance(mark_attendance);
                successDialog();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        //} else {
          //  nameAlreadyUsed();
        //}

    }
    private void nameAlreadyUsed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Error");
        alertDialogBuilder
                .setMessage(name+"'s Attendance Already Marked.")
                .setCancelable(false)
                .setPositiveButton("Try Again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent=new Intent(Mark_AttendenceActivity.this,Attendence_Activity.class);
                                startActivity(intent);
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Button Pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Pbutton.setTextColor(Color.BLACK);
    }

    private void successDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Successfully");
        alertDialogBuilder
                .setMessage(name+"'s Attendance Mark Successfully!")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Button Pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Pbutton.setTextColor(Color.BLACK);
    }


    @Override
    public void finish() {
        super.finish();
        customType(Mark_AttendenceActivity.this, "fadein-to-fadeout");
    }
}
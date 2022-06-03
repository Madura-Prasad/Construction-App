package com.maduraprasad.wjconstractor;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maduraprasad.wjconstractor.CustomAdapter.CustomAdpterAttendence;
import com.maduraprasad.wjconstractor.DataBase.DBConnector;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class Attendence_Activity extends AppCompatActivity {

    RecyclerView rview;
    ImageView nodata;
    TextView not;
    Button clear;

    DBConnector db;
    ArrayList<String> masonry_id, masonry_name;
    CustomAdpterAttendence customAdpterAttendence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //initialize variable
        rview = findViewById(R.id.recyclerViewMasonry);
        nodata = findViewById(R.id.empty_imageview);
        not = findViewById(R.id.no_data);
        clear = findViewById(R.id.clear_attendance);

        db = new DBConnector(Attendence_Activity.this);
        masonry_id = new ArrayList<>();
        masonry_name = new ArrayList<>();


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteDialog();
            }
        });

        storeDataInArrays();

        customAdpterAttendence = new CustomAdpterAttendence(Attendence_Activity.this, this, masonry_id, masonry_name);
        rview.setAdapter(customAdpterAttendence);
        rview.setLayoutManager(new LinearLayoutManager(Attendence_Activity.this));

    }

    void storeDataInArrays() {
        Cursor cursor = db.readAllDataMasonry();
        if (cursor.getCount() == 0) {
            nodata.setVisibility(View.VISIBLE);
            not.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                masonry_id.add(cursor.getString(0));
                masonry_name.add(cursor.getString(1));

            }
            nodata.setVisibility(View.GONE);
            not.setVisibility(View.GONE);
        }
    }

    void confirmDeleteDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete All?");
        alertDialogBuilder
                .setMessage("Are you sure clear this month all Data ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                DBConnector dbConnector = new DBConnector(Attendence_Activity.this);
                                dbConnector.deleteAttendanceAll();
                                //Refresh Activity
                                Intent intent = new Intent(Attendence_Activity.this, Attendence_Activity.class);
                                startActivity(intent);
                                customType(Attendence_Activity.this, "fadein-to-fadeout");
                                finish();
                            }
                        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Button Pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Pbutton.setTextColor(Color.BLACK);
        Button Nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        Nbutton.setTextColor(Color.BLACK);

    }

    @Override
    public void finish() {
        super.finish();
        customType(Attendence_Activity.this, "fadein-to-fadeout");
    }
}
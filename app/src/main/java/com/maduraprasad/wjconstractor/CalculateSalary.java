package com.maduraprasad.wjconstractor;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maduraprasad.wjconstractor.CustomAdapter.CustomAdapterSalary;
import com.maduraprasad.wjconstractor.CustomAdapter.CustomAdpterAttendence;
import com.maduraprasad.wjconstractor.DataBase.DBConnector;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class CalculateSalary extends AppCompatActivity {

    RecyclerView rview;
    ImageView nodata;
    TextView not;

    DBConnector db;
    ArrayList<String> masonry_id, masonry_name,masonry_attendance;
    CustomAdapterSalary customAdapterSalary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_salary);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //initialize variable
        rview = findViewById(R.id.recyclerViewMasonry);
        nodata = findViewById(R.id.empty_imageview);
        not = findViewById(R.id.no_data);

        db = new DBConnector(CalculateSalary.this);
        masonry_id = new ArrayList<>();
        masonry_name = new ArrayList<>();
        masonry_attendance = new ArrayList<>();

        storeDataInArrays();
        customAdapterSalary = new CustomAdapterSalary(CalculateSalary.this, this, masonry_id, masonry_name,masonry_attendance);
        rview.setAdapter(customAdapterSalary);
        rview.setLayoutManager(new LinearLayoutManager(CalculateSalary.this));
    }

    void storeDataInArrays() {
        Cursor cursor = db.AttendancereadAllData();
        if (cursor.getCount() == 0) {
            nodata.setVisibility(View.VISIBLE);
            not.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                masonry_id.add(cursor.getString(0));
                masonry_name.add(cursor.getString(1));
                masonry_attendance.add(cursor.getString(2));

            }
            nodata.setVisibility(View.GONE);
            not.setVisibility(View.GONE);
        }
    }


    public void calculate(View view){
        Intent intent=new Intent(CalculateSalary.this,CalculateSalaryType.class);
        startActivity(intent);
        customType(CalculateSalary.this, "fadein-to-fadeout");
    }

    @Override
    public void finish() {
        super.finish();
        customType(CalculateSalary.this, "fadein-to-fadeout");
    }
}
package com.maduraprasad.wjconstractor;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maduraprasad.wjconstractor.CustomAdapter.CustomAdapterAddUpdateDeleteSupervisor;
import com.maduraprasad.wjconstractor.DataBase.DBConnector;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class AdminSupervisorActivity extends AppCompatActivity {

    RecyclerView rview;
    FloatingActionButton add;
    ImageView nodata;
    TextView not;

    DBConnector db;
    ArrayList<String> supervisor_id, supervisor_name, supervisor_email, supervisor_password, supervisor_cpassword;
    CustomAdapterAddUpdateDeleteSupervisor customAdapterAddUpadateDeleteSupervisor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_supervisor);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //initialize variable
        rview = findViewById(R.id.recyclerViewSupervisor);
        add = findViewById(R.id.add_button);
        nodata = findViewById(R.id.empty_imageview);
        not = findViewById(R.id.no_data);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSupervisorActivity.this, _Supervisor_AddActivity.class);
                startActivity(intent);
                customType(AdminSupervisorActivity.this, "fadein-to-fadeout");
            }
        });


        db = new DBConnector(AdminSupervisorActivity.this);
        supervisor_id = new ArrayList<>();
        supervisor_name = new ArrayList<>();
        supervisor_email = new ArrayList<>();
        supervisor_password = new ArrayList<>();
        supervisor_cpassword = new ArrayList<>();

        storeDataInArrays();

        customAdapterAddUpadateDeleteSupervisor = new CustomAdapterAddUpdateDeleteSupervisor(AdminSupervisorActivity.this, AdminSupervisorActivity.this, supervisor_id, supervisor_name, supervisor_email, supervisor_password, supervisor_cpassword);
        rview.setAdapter(customAdapterAddUpadateDeleteSupervisor);
        rview.setLayoutManager(new LinearLayoutManager(AdminSupervisorActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = db.SupervisorreadAllData();
        if (cursor.getCount() == 0) {
            nodata.setVisibility(View.VISIBLE);
            not.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                supervisor_id.add(cursor.getString(0));
                supervisor_name.add(cursor.getString(1));
                supervisor_email.add(cursor.getString(2));
                supervisor_password.add(cursor.getString(3));
                supervisor_cpassword.add(cursor.getString(4));

            }
            nodata.setVisibility(View.GONE);
            not.setVisibility(View.GONE);
        }
    }

    @Override
    public void finish() {
        super.finish();
        customType(AdminSupervisorActivity.this, "fadein-to-fadeout");
    }
}
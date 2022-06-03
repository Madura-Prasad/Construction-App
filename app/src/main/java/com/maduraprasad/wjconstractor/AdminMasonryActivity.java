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
import com.maduraprasad.wjconstractor.CustomAdapter.CustomAdapterAddUpdateDeleteMasonry;
import com.maduraprasad.wjconstractor.DataBase.DBConnector;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class AdminMasonryActivity extends AppCompatActivity {

    RecyclerView rview;
    FloatingActionButton add;
    ImageView nodata;
    TextView not;

    DBConnector db;
    ArrayList<String> masonry_id, masonry_name, masonry_address, masonry_nic, masonry_age,masonrysname;
    CustomAdapterAddUpdateDeleteMasonry customAdapterAddUpdateDeleteMasonry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_masonry);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //initialize variable
        rview = findViewById(R.id.recyclerViewMasonry);
        add = findViewById(R.id.add_button);
        nodata = findViewById(R.id.empty_imageview);
        not = findViewById(R.id.no_data);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMasonryActivity.this, _Masonry_AddActivity.class);
                startActivity(intent);
                customType(AdminMasonryActivity.this, "fadein-to-fadeout");
            }
        });


        db = new DBConnector(AdminMasonryActivity.this);
        masonry_id = new ArrayList<>();
        masonry_name = new ArrayList<>();
        masonry_address = new ArrayList<>();
        masonry_nic = new ArrayList<>();
        masonry_age = new ArrayList<>();
        masonrysname = new ArrayList<>();

        storeDataInArrays();

        customAdapterAddUpdateDeleteMasonry = new CustomAdapterAddUpdateDeleteMasonry(AdminMasonryActivity.this, this, masonry_id, masonry_name, masonry_address, masonry_nic, masonry_age,masonrysname);
        rview.setAdapter(customAdapterAddUpdateDeleteMasonry);
        rview.setLayoutManager(new LinearLayoutManager(AdminMasonryActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
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
                masonry_address.add(cursor.getString(2));
                masonry_nic.add(cursor.getString(3));
                masonry_age.add(cursor.getString(4));
                masonrysname.add(cursor.getString(5));

            }
            nodata.setVisibility(View.GONE);
            not.setVisibility(View.GONE);
        }
    }


    @Override
    public void finish() {
        super.finish();
        customType(AdminMasonryActivity.this, "fadein-to-fadeout");
    }
}
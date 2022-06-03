package com.maduraprasad.wjconstractor;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maduraprasad.wjconstractor.CustomAdapter.CustomAdapterMasonryView;
import com.maduraprasad.wjconstractor.DataBase.DBConnector;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class AdminView_MasonryActivity extends AppCompatActivity {
    RecyclerView rview;
    ImageView nodata;
    TextView not;

    DBConnector db;
    ArrayList<String> masonry_id, masonry_name, masonry_address, masonry_nic, masonry_age,masonry_sname;
    CustomAdapterMasonryView customAdapterMasonryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view__masonry);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //initialize variable
        rview = findViewById(R.id.recyclerViewMasonry);
        nodata = findViewById(R.id.empty_imageview);
        not = findViewById(R.id.no_data);

        db = new DBConnector(AdminView_MasonryActivity.this);
        masonry_id = new ArrayList<>();
        masonry_name = new ArrayList<>();
        masonry_address = new ArrayList<>();
        masonry_nic = new ArrayList<>();
        masonry_age = new ArrayList<>();
        masonry_sname = new ArrayList<>();

        storeDataInArrays();

        customAdapterMasonryView = new CustomAdapterMasonryView(AdminView_MasonryActivity.this, this, masonry_id, masonry_name, masonry_address, masonry_nic, masonry_age,masonry_sname);
        rview.setAdapter(customAdapterMasonryView);
        rview.setLayoutManager(new LinearLayoutManager(AdminView_MasonryActivity.this));
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
                masonry_sname.add(cursor.getString(5));

            }
            nodata.setVisibility(View.GONE);
            not.setVisibility(View.GONE);
        }
    }

    @Override
    public void finish() {
        super.finish();
        customType(AdminView_MasonryActivity.this, "fadein-to-fadeout");
    }
}
package com.maduraprasad.wjconstractor;

import android.content.Intent;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maduraprasad.wjconstractor.CustomAdapter.CustomAdapterAddSite;
import com.maduraprasad.wjconstractor.DataBase.DBConnector;
import com.maduraprasad.wjconstractor.DataBase.DataHandler;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class AdminAddSiteActivity extends AppCompatActivity {

    RecyclerView rview;
    FloatingActionButton add;
    ImageView nodata;
    TextView not;

    DBConnector db;
    ArrayList<String> site_id, site_name, site_supervisor_name, site_location;
    CustomAdapterAddSite customAdapterAddSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_site);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //initialize variable
        rview = findViewById(R.id.recyclerViewAdd);
        add = findViewById(R.id.add_button);
        nodata = findViewById(R.id.empty_imageview);
        not = findViewById(R.id.no_data);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddSiteActivity.this, Admin_Add_Site_TypeActivity.class);
                startActivity(intent);
                customType(AdminAddSiteActivity.this, "fadein-to-fadeout");
            }
        });


        db = new DBConnector(AdminAddSiteActivity.this);
        site_id = new ArrayList<>();
        site_name = new ArrayList<>();
        site_name = new ArrayList<>();
        site_supervisor_name = new ArrayList<>();
        site_location = new ArrayList<>();

        storeDataInArrays();

        customAdapterAddSite = new CustomAdapterAddSite(AdminAddSiteActivity.this, site_id, site_name, site_supervisor_name, site_location);
        rview.setAdapter(customAdapterAddSite);
        rview.setLayoutManager(new LinearLayoutManager(AdminAddSiteActivity.this));
    }

    void storeDataInArrays() {
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0) {
            nodata.setVisibility(View.VISIBLE);
            not.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                site_id.add(cursor.getString(0));
                site_name.add(cursor.getString(1));
                site_supervisor_name.add(cursor.getString(2));
                site_location.add(cursor.getString(3));

            }
            nodata.setVisibility(View.GONE);
            not.setVisibility(View.GONE);
        }
    }


    @Override
    public void finish() {
        super.finish();
        customType(AdminAddSiteActivity.this, "fadein-to-fadeout");
    }
}
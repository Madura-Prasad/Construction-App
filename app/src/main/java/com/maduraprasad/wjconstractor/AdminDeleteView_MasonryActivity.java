package com.maduraprasad.wjconstractor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.maduraprasad.wjconstractor.CustomAdapter.CustomAdapterAddUpdateDeleteMasonry;
import com.maduraprasad.wjconstractor.CustomAdapter.CustomAdpterMasonryDelete;
import com.maduraprasad.wjconstractor.CustomAdapter.CustomAdpterSupervisorDelete;
import com.maduraprasad.wjconstractor.DataBase.DBConnector;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class AdminDeleteView_MasonryActivity extends AppCompatActivity {
    RecyclerView rview;
    ImageView nodata;
    TextView not;
    Button deleteall;

    DBConnector db;
    ArrayList<String> masonry_id, masonry_name, masonry_address, masonry_nic, masonry_age,masonrysname;
    CustomAdpterMasonryDelete customAdpterMasonryDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_view__masonry);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //initialize variable
        rview = findViewById(R.id.recyclerViewMasonry);
        nodata = findViewById(R.id.empty_imageview);
        not = findViewById(R.id.no_data);
        deleteall=findViewById(R.id.delete_all_btn);

        deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteDialog();
            }
        });

        db = new DBConnector(AdminDeleteView_MasonryActivity.this);
        masonry_id = new ArrayList<>();
        masonry_name = new ArrayList<>();
        masonry_address = new ArrayList<>();
        masonry_nic = new ArrayList<>();
        masonry_age = new ArrayList<>();
        masonrysname = new ArrayList<>();

        storeDataInArrays();

        customAdpterMasonryDelete = new CustomAdpterMasonryDelete(AdminDeleteView_MasonryActivity.this, this, masonry_id, masonry_name, masonry_address, masonry_nic, masonry_age,masonrysname);
        rview.setAdapter(customAdpterMasonryDelete);
        rview.setLayoutManager(new LinearLayoutManager(AdminDeleteView_MasonryActivity.this));
    }

    void confirmDeleteDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete All?");
        alertDialogBuilder
                .setMessage("Are you sure you want to delete all Data ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                DBConnector dbConnector = new DBConnector(AdminDeleteView_MasonryActivity.this);
                                dbConnector.deleteMasonryAll();
                                //Refresh Activity
                                Intent intent = new Intent(AdminDeleteView_MasonryActivity.this, AdminDeleteView_MasonryActivity.class);
                                startActivity(intent);
                                customType(AdminDeleteView_MasonryActivity.this, "fadein-to-fadeout");
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
        customType(AdminDeleteView_MasonryActivity.this, "fadein-to-fadeout");
    }
}
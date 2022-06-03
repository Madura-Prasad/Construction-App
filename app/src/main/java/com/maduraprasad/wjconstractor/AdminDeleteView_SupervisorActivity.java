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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maduraprasad.wjconstractor.CustomAdapter.CustomAdpterSupervisorDelete;
import com.maduraprasad.wjconstractor.DataBase.DBConnector;
import com.maduraprasad.wjconstractor.DataBase.DataHandler;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class AdminDeleteView_SupervisorActivity extends AppCompatActivity {
    RecyclerView rview;
    ImageView nodata;
    TextView not;
    Button deleteall;

    DBConnector db;
    ArrayList<String> supervisor_id, supervisor_name, supervisor_email, supervisor_password, supervisor_cpassword;
    CustomAdpterSupervisorDelete customAdpterSupervisorDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_view__supervisor);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //initialize variable
        rview = findViewById(R.id.recyclerViewSupervisor);
        nodata = findViewById(R.id.empty_imageview);
        not = findViewById(R.id.no_data);
        deleteall = findViewById(R.id.delete_all_btn);

        deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteDialog();

            }
        });


        db = new DBConnector(AdminDeleteView_SupervisorActivity.this);
        supervisor_id = new ArrayList<>();
        supervisor_name = new ArrayList<>();
        supervisor_email = new ArrayList<>();
        supervisor_password = new ArrayList<>();
        supervisor_cpassword = new ArrayList<>();

        storeDataInArrays();

        customAdpterSupervisorDelete = new CustomAdpterSupervisorDelete(AdminDeleteView_SupervisorActivity.this, AdminDeleteView_SupervisorActivity.this, supervisor_id, supervisor_name, supervisor_email, supervisor_password, supervisor_cpassword);
        rview.setAdapter(customAdpterSupervisorDelete);
        rview.setLayoutManager(new LinearLayoutManager(AdminDeleteView_SupervisorActivity.this));
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

    void confirmDeleteDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete All?");
        alertDialogBuilder
                .setMessage("Are you sure you want to delete all Data ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                DBConnector dbConnector = new DBConnector(AdminDeleteView_SupervisorActivity.this);
                                dbConnector.deleteSupervisorAll();
                                //Refresh Activity
                                Intent intent = new Intent(AdminDeleteView_SupervisorActivity.this, AdminDeleteView_SupervisorActivity.class);
                                startActivity(intent);
                                customType(AdminDeleteView_SupervisorActivity.this, "fadein-to-fadeout");
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
        customType(AdminDeleteView_SupervisorActivity.this, "fadein-to-fadeout");
    }
}
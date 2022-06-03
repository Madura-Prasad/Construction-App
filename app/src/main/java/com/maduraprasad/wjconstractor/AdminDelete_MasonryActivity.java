package com.maduraprasad.wjconstractor;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.maduraprasad.wjconstractor.DataBase.DBConnector;

import static maes.tech.intentanim.CustomIntent.customType;

public class AdminDelete_MasonryActivity extends AppCompatActivity {
    TextInputLayout masonry_name, masonry_address, masonry_nic, masonry_age, masonry_sname;
    TextInputEditText edit_masonry_name, edit_masonry_address, edit_masonry_nic, edit_masonry_age, edit_masonry_sname;
    String id, name, address, nic, age, sname;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete__masonry);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //initialize variable
        masonry_name = findViewById(R.id.masonry_name2);
        masonry_address = findViewById(R.id.masonry_address2);
        masonry_nic = findViewById(R.id.masonry_nic2);
        masonry_age = findViewById(R.id.masonry_age2);
        masonry_sname = findViewById(R.id.supervisor_name2);
        edit_masonry_name = findViewById(R.id.edit_masonry_name2);
        edit_masonry_address = findViewById(R.id.edit_masonry_address2);
        edit_masonry_nic = findViewById(R.id.edit_masonry_nic2);
        edit_masonry_age = findViewById(R.id.edit_masonry_age2);
        edit_masonry_sname = findViewById(R.id.edit_supervisor_name2);
        delete = findViewById(R.id.delete_btn);
        getAndSetIntentDataMasonry();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteDialog();
            }
        });
    }

    void getAndSetIntentDataMasonry() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("address")
                && getIntent().hasExtra("nic") && getIntent().hasExtra("age") && getIntent().hasExtra("sname")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            address = getIntent().getStringExtra("address");
            nic = getIntent().getStringExtra("nic");
            age = getIntent().getStringExtra("age");
            sname = getIntent().getStringExtra("sname");

            //Setting Intent Data
            edit_masonry_name.setText(name);
            edit_masonry_address.setText(address);
            edit_masonry_nic.setText(nic);
            edit_masonry_age.setText(age);
            edit_masonry_sname.setText(sname);

        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDeleteDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete " + name + " ?");
        alertDialogBuilder
                .setMessage("Are you sure you want to delete " + name + " ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                DBConnector dbConnector = new DBConnector(AdminDelete_MasonryActivity.this);
                                dbConnector.deleteOneRowMasonry(id);
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

    @Override
    public void finish() {
        super.finish();
        customType(AdminDelete_MasonryActivity.this, "fadein-to-fadeout");
    }
}
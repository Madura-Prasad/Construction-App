package com.maduraprasad.wjconstractor;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.maduraprasad.wjconstractor.DataBase.DBConnector;

import static maes.tech.intentanim.CustomIntent.customType;

public class AdminDelete_SupervisorActivity extends AppCompatActivity {
    TextInputLayout supervisor_name, supervisor_email, supervisor_password, supervisor_cpassword;
    TextInputEditText edit_supervisor_name, edit_supervisor_email, edit_supervisor_password, edit_supervisor_cpassword;
    String id, name, email, password, cpassword;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete__supervisor);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //initialize variable
        supervisor_name = findViewById(R.id.supervisor_name2);
        supervisor_email = findViewById(R.id.supervisor_email2);
        supervisor_password = findViewById(R.id.supervisor_password2);
        supervisor_cpassword = findViewById(R.id.supervisor_cpassword2);
        edit_supervisor_name = findViewById(R.id.edit_supervisor_name2);
        edit_supervisor_email = findViewById(R.id.edit_supervisor_email2);
        edit_supervisor_password = findViewById(R.id.edit_supervisor_password2);
        edit_supervisor_cpassword = findViewById(R.id.edit_supervisor_cpassword2);
        delete = findViewById(R.id.delete_btn);
        getAndSetIntentDataSupervisor();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteDialog();
            }
        });
    }

    void getAndSetIntentDataSupervisor() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("email")
                && getIntent().hasExtra("password") && getIntent().hasExtra("cpassword")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            email = getIntent().getStringExtra("email");
            password = getIntent().getStringExtra("password");
            cpassword = getIntent().getStringExtra("cpassword");

            //Setting Intent Data
            edit_supervisor_name.setText(name);
            edit_supervisor_email.setText(email);
            edit_supervisor_password.setText(password);
            edit_supervisor_cpassword.setText(cpassword);

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
                                DBConnector dbConnector = new DBConnector(AdminDelete_SupervisorActivity.this);
                                dbConnector.deleteOneRowSupervisor(id);
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
        customType(AdminDelete_SupervisorActivity.this, "fadein-to-fadeout");
    }
}
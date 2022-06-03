package com.maduraprasad.wjconstractor;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.maduraprasad.wjconstractor.DataBase.DataHandler;

import static maes.tech.intentanim.CustomIntent.customType;

public class SupervisorSignInActivity extends AppCompatActivity {

    TextInputLayout username, password;
    DataHandler dataHandler = new DataHandler(this);

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "my_pref_super";
    private static final String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_sign_in);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //initialize variable
        username = findViewById(R.id.uname);
        password = findViewById(R.id.password);

        //database
        dataHandler.openDB();

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        if (name != null) {
            Intent intent = new Intent(getApplicationContext(),Supervisor_MainHomeActivity.class);
            startActivity(intent);
            customType(SupervisorSignInActivity.this, "fadein-to-fadeout");
        }
    }

    //username empty
    private boolean validUsername() {
        String name = username.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            username.setError("Username is Empty.");
            return false;
        } else {
            username.setError(null);
            return true;
        }

    }

    //password empty
    private boolean validPassword() {
        String pass = password.getEditText().getText().toString().trim();

        if (pass.isEmpty()) {
            password.setError("Password is Empty.");
            return false;
        } else {
            password.setError(null);
            return true;
        }

    }

    public void sigin(View view) {
        String name = username.getEditText().getText().toString().trim();
        String pass = password.getEditText().getText().toString().trim();

        if (!validUsername() | !validPassword()) {
            return;
        }

        try {
            Boolean checkUsernameSupervisor = dataHandler.checkUsernamePasswordSupervisor(name, pass);
            if (checkUsernameSupervisor == true) {
                successDialog();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, username.getEditText().getText().toString());
                editor.apply();
            } else {
                errorDialog();
            }
        } catch (Exception e) {

        }
    }

    private void errorDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Failed");
        alertDialogBuilder
                .setMessage("Incorrect Username or Password. Please Try Again.")
                .setCancelable(false)
                .setPositiveButton("Try Again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Button Pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Pbutton.setTextColor(Color.BLACK);
    }


    private void successDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Successfully");
        alertDialogBuilder
                .setMessage("Supervisor Logged Successfully!")
                .setCancelable(false)
                .setPositiveButton("Get Start",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(getApplicationContext(), Supervisor_MainHomeActivity.class);
                                startActivity(intent);
                                customType(SupervisorSignInActivity.this, "fadein-to-fadeout");
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Button Pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Pbutton.setTextColor(Color.BLACK);
    }

    @Override
    public void finish() {
        super.finish();
        customType(SupervisorSignInActivity.this, "fadein-to-fadeout");
    }
}
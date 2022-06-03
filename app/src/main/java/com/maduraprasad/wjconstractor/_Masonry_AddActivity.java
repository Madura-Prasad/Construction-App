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
import com.maduraprasad.wjconstractor.DataBase.DataHandler;
import com.maduraprasad.wjconstractor.DataBase.Masonry;

import static maes.tech.intentanim.CustomIntent.customType;

public class _Masonry_AddActivity extends AppCompatActivity {
    TextInputLayout masonry_name, masonry_address, masonry_nic, masonry_age, masonry_sname;
    TextInputEditText edit_masonry_name, edit_masonry_address, edit_masonry_nic, edit_masonry_age, edit_masonry_sname;
    DataHandler dataHandler = new DataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity___masonry__add);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //initialize variable
        masonry_name = findViewById(R.id.masonry_name);
        masonry_address = findViewById(R.id.masonry_address);
        masonry_nic = findViewById(R.id.masonry_nic);
        masonry_age = findViewById(R.id.masonry_age);
        masonry_sname = findViewById(R.id.masonry_supervisor_name);
        edit_masonry_name = findViewById(R.id.edit_masonry_name);
        edit_masonry_address = findViewById(R.id.edit_masonry_address);
        edit_masonry_nic = findViewById(R.id.edit_masonry_nic);
        edit_masonry_age = findViewById(R.id.edit_masonry_age);
        edit_masonry_sname = findViewById(R.id.site_supervisorname);

        //database
        dataHandler.openDB();
    }


    //name validation
    private boolean validUsername() {
        String fname = masonry_name.getEditText().getText().toString().trim();
        String vname = "^[A-Za-z]\\w{5,29}$";

        if (fname.isEmpty()) {
            masonry_name.setError("Username is Empty.");
            return false;
        } else if (!fname.matches(vname)) {
            masonry_name.setError("Invalid Name.");
            return false;
        } else {
            masonry_name.setError(null);
            return true;
        }

    }

    //address validation
    private boolean validAddress() {
        String address = masonry_address.getEditText().getText().toString().trim();


        if (address.isEmpty()) {
            masonry_address.setError("Address is Empty.");
            return false;
        } else {
            masonry_address.setError(null);
            return true;
        }

    }

    //nic validation
    private boolean validNic() {
        String nic = masonry_nic.getEditText().getText().toString().trim();


        if (nic.isEmpty()) {
            masonry_nic.setError("Nic is Empty.");
            return false;
        } else {
            masonry_nic.setError(null);
            return true;
        }

    }

    //age validation
    private boolean validAge() {
        String age = masonry_age.getEditText().getText().toString().trim();


        if (age.isEmpty()) {
            masonry_age.setError("Age is Empty.");
            return false;
        } else {
            masonry_age.setError(null);
            return true;
        }

    }

    //address validation
    private boolean validSname() {
        String SName = masonry_sname.getEditText().getText().toString().trim();


        if (SName.isEmpty()) {
            masonry_sname.setError("Address is Empty.");
            return false;
        } else {
            masonry_sname.setError(null);
            return true;
        }

    }


    public void addMasonry(View view) {
        if (!validUsername() | !validAddress() | !validNic() | !validAge() | !validSname()) {
            return;
        }

        String fname = masonry_name.getEditText().getText().toString().trim();
        String address = masonry_address.getEditText().getText().toString().trim();
        String nic = masonry_nic.getEditText().getText().toString().trim();
        String age = masonry_age.getEditText().getText().toString().trim();
        String SName = masonry_sname.getEditText().getText().toString().trim();

        if (!dataHandler.checkUsernameMasonry(fname)) {
            Masonry masonry = new Masonry(fname, address, nic, age, SName);
            try {
                dataHandler.Masonry_create(masonry);
                successDialog();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        } else {
            nameAlreadyUsed();
            edit_masonry_name.setText("");
        }


    }

    private void nameAlreadyUsed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Failed");
        alertDialogBuilder
                .setMessage("Username Already Taken.")
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
                .setMessage("Masonry Added Successfully!")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edit_masonry_name.setText("");
                                edit_masonry_address.setText("");
                                edit_masonry_nic.setText("");
                                edit_masonry_age.setText("");
                                edit_masonry_sname.setText("");
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
        customType(_Masonry_AddActivity.this, "fadein-to-fadeout");
    }
}
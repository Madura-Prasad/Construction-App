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
import com.maduraprasad.wjconstractor.DataBase.Supervisor;

import static maes.tech.intentanim.CustomIntent.customType;

public class _Supervisor_AddActivity extends AppCompatActivity {

    TextInputLayout supervisor_name, supervisor_email, supervisor_password, supervisor_cpassword;
    TextInputEditText edit_supervisor_name, edit_supervisor_email, edit_supervisor_password, edit_supervisor_cpassword;
    DataHandler dataHandler = new DataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__supervisor__add);

        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //initialize variable
        supervisor_name = findViewById(R.id.supervisor_name);
        supervisor_email = findViewById(R.id.supervisor_email);
        supervisor_password = findViewById(R.id.supervisor_password);
        supervisor_cpassword = findViewById(R.id.supervisor_cpassword);
        edit_supervisor_name = findViewById(R.id.edit_supervisor_name);
        edit_supervisor_email = findViewById(R.id.edit_supervisor_email);
        edit_supervisor_password = findViewById(R.id.edit_supervisor_password);
        edit_supervisor_cpassword = findViewById(R.id.edit_supervisor_cpassword);

        //database
        dataHandler.openDB();
    }

    //name validation
    private boolean validUsername() {
        String fname = supervisor_name.getEditText().getText().toString().trim();
        String vname = "^[A-Za-z]\\w{5,29}$";

        if (fname.isEmpty()) {
            supervisor_name.setError("Username is Empty.");
            return false;
        } else if (!fname.matches(vname)) {
            supervisor_name.setError("Invalid Name.");
            return false;
        } else {
            supervisor_name.setError(null);
            return true;
        }

    }

    //email validation
    private boolean validEmail() {
        String mail = supervisor_email.getEditText().getText().toString().trim();
        String vemail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (mail.isEmpty()) {
            supervisor_email.setError("Email is Empty.");
            return false;
        } else if (!mail.matches(vemail)) {
            supervisor_email.setError("Invalid Email Address.");
            return false;
        } else {
            supervisor_email.setError(null);
            return true;
        }

    }

    //password validation
    private boolean validPassword() {
        String pass = supervisor_password.getEditText().getText().toString().trim();
        String cpass = supervisor_cpassword.getEditText().getText().toString().trim();
        String vpassword = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        if (pass.isEmpty()) {
            supervisor_password.setError("Password is Empty.");
            return false;
        } else if (!pass.matches(vpassword)) {
            supervisor_password.setError("Use Strong Password.");
            return false;
        } else if (!pass.matches(cpass)) {
            supervisor_password.setError("Please Use same Password");
            return false;
        } else {
            supervisor_password.setError(null);
            return true;
        }

    }

    //confirm password validation
    private boolean validCPassword() {
        String cpass = supervisor_cpassword.getEditText().getText().toString().trim();
        String pass = supervisor_password.getEditText().getText().toString().trim();

        if (cpass.isEmpty()) {
            supervisor_cpassword.setError("Confirm Password is Empty.");
            return false;
        } else if (!pass.matches(cpass)) {
            supervisor_cpassword.setError("Please Use same Password");
            return false;
        } else {
            supervisor_cpassword.setError(null);
            return true;
        }

    }

    public void addSupervisor(View view) {
        if (!validUsername() | !validEmail() | !validPassword() | !validCPassword()) {
            return;
        }

        String fname = supervisor_name.getEditText().getText().toString().trim();
        String mail = supervisor_email.getEditText().getText().toString().trim();
        String pass = supervisor_password.getEditText().getText().toString().trim();
        String cpass = supervisor_cpassword.getEditText().getText().toString().trim();


        if (!dataHandler.checkUsernameSupervisor(fname)) {
            Supervisor supervisor = new Supervisor(fname, mail, pass, cpass);
            try {
                dataHandler.Supervisor_create(supervisor);
                successDialog();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        } else {
            emailAlreadyUsed();
            edit_supervisor_name.setText("");
        }
    }

    private void emailAlreadyUsed() {
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
                .setMessage("Supervisor Added Successfully!")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edit_supervisor_name.setText("");
                                edit_supervisor_email.setText("");
                                edit_supervisor_password.setText("");
                                edit_supervisor_cpassword.setText("");
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
        customType(_Supervisor_AddActivity.this, "fadein-to-fadeout");
    }

}
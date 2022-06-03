package com.maduraprasad.wjconstractor;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.maduraprasad.wjconstractor.DataBase.Admin;
import com.maduraprasad.wjconstractor.DataBase.DataHandler;

import static maes.tech.intentanim.CustomIntent.customType;

public class AdminSignUpActivity extends AppCompatActivity {

    TextInputLayout name, email, password, cpassword;
    TextInputEditText editaname, editemail, editpass, editcpass;
    DataHandler dataHandler = new DataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //initialize variable
        name = findViewById(R.id.fname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        editaname = findViewById(R.id.editname);
        editemail = findViewById(R.id.editemail);
        editpass = findViewById(R.id.edit_password);
        editcpass = findViewById(R.id.edit_cpassword);

        //database
        dataHandler.openDB();

    }


    //name validation
    private boolean validUsername() {
        String fname = name.getEditText().getText().toString().trim();
        String vname = "^[A-Za-z]\\w{5,29}$";

        if (fname.isEmpty()) {
            name.setError("Username is Empty.");
            return false;
        } else if (!fname.matches(vname)) {
            name.setError("Invalid Name.");
            return false;
        } else {
            name.setError(null);
            return true;
        }

    }

    //email validation
    private boolean validEmail() {
        String mail = email.getEditText().getText().toString().trim();
        String vemail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (mail.isEmpty()) {
            email.setError("Email is Empty.");
            return false;
        } else if (!mail.matches(vemail)) {
            email.setError("Invalid Email Address.");
            return false;
        } else {
            email.setError(null);
            return true;
        }

    }

    //password validation
    private boolean validPassword() {
        String pass = password.getEditText().getText().toString().trim();
        String cpass = cpassword.getEditText().getText().toString().trim();
        String vpassword = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        if (pass.isEmpty()) {
            password.setError("Password is Empty.");
            return false;
        } else if (!pass.matches(vpassword)) {
            password.setError("Use Strong Password.");
            return false;
        } else if (!pass.matches(cpass)) {
            password.setError("Please Use same Password");
            return false;
        } else {
            password.setError(null);
            return true;
        }

    }

    //confirm password validation
    private boolean validCPassword() {
        String cpass = cpassword.getEditText().getText().toString().trim();
        String pass = password.getEditText().getText().toString().trim();

        if (cpass.isEmpty()) {
            cpassword.setError("Confirm Password is Empty.");
            return false;
        } else if (!pass.matches(cpass)) {
            cpassword.setError("Please Use same Password");
            return false;
        } else {
            cpassword.setError(null);
            return true;
        }

    }


    public void signup(View view) {
        String fullname = name.getEditText().getText().toString().trim();
        String emailaddress = email.getEditText().getText().toString().trim();
        String pass = password.getEditText().getText().toString().trim();
        String cpass = cpassword.getEditText().getText().toString().trim();

        if (!validUsername() | !validEmail() | !validPassword() | !validCPassword()) {
            return;
        }

        if (!dataHandler.checkUsername(fullname)) {
            Admin admin = new Admin(fullname, emailaddress, pass, cpass);
            try {
                dataHandler.Admin_Create(admin);
                successDialog();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        } else {
            emailAlreadyUsed();
            editaname.setText("");
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
                .setMessage("Administrator Account created Successfully!")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(getApplicationContext(), AdminSignInActivity.class);
                                startActivity(intent);
                                customType(AdminSignUpActivity.this, "fadein-to-fadeout");
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
        customType(AdminSignUpActivity.this, "fadein-to-fadeout");
    }
}
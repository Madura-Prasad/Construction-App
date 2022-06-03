package com.maduraprasad.wjconstractor;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.maduraprasad.wjconstractor.DataBase.Add_Site;
import com.maduraprasad.wjconstractor.DataBase.DataHandler;

import static maes.tech.intentanim.CustomIntent.customType;

public class Admin_Add_Site_TypeActivity extends AppCompatActivity {

    TextInputLayout sitename,sitesupervisor, sitelocation;
    AutoCompleteTextView autoCompleteTextView;
    DataHandler dataHandler = new DataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add__site__type);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //initialize variable
        sitename = findViewById(R.id.site_name);
        sitelocation = findViewById(R.id.site_location);
        sitesupervisor = findViewById(R.id.site_supervisor_name);
        autoCompleteTextView = findViewById(R.id.dropdown_text);


        //database
        dataHandler.openDB();


        //dropdown
        String[] items = new String[]{
                "Ampara",
                "Anuradhapura",
                "Badulla",
                "Batticaloa",
                "Colombo",
                "Galle",
                "Gampaha",
                "Hambantota",
                "Jaffna",
                "Kalutara",
                "Kandy",
                "Kegalle",
                "Kilinochchi",
                "Kurunegala",
                "Mannar",
                "Matale",
                "Matara",
                "Moneragala",
                "Mullaitivu",
                "Nuwara Eliya",
                "Polonnaruwa",
                "Puttalam",
                "Ratnapura",
                "Trincomalee",
                "Vavuniya"

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                Admin_Add_Site_TypeActivity.this,
                R.layout.dropdown_item,
                items
        );

        autoCompleteTextView.setAdapter(adapter);

    }

    //name empty
    private boolean validName() {
        String name = sitename.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            sitename.setError("Site Name is Empty.");
            return false;
        } else {
            sitename.setError(null);
            return true;
        }

    }
    //name empty
    private boolean validSName() {
        String sname = sitesupervisor.getEditText().getText().toString().trim();

        if (sname.isEmpty()) {
            sitesupervisor.setError("Supervisor Name is Empty.");
            return false;
        } else {
            sitesupervisor.setError(null);
            return true;
        }

    }

    //location empty
    private boolean validLocation() {
        String location = sitelocation.getEditText().getText().toString().trim();

        if (location.isEmpty()) {
            sitelocation.setError("Site Location is Empty.");
            return false;
        } else {
            sitelocation.setError(null);
            return true;
        }

    }


    public void addSite(View view) {
        String name = sitename.getEditText().getText().toString().trim();
        String sname = sitesupervisor.getEditText().getText().toString().trim();
        String location = sitelocation.getEditText().getText().toString().trim();


        if (!validName() | !validLocation() | !validSName()) {
            return;
        }

        Add_Site add_site = new Add_Site(name,sname, location);
        try {
            dataHandler.Add_Constraction_Site(add_site);
            successDialog();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
    }

    private void successDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Successfully");
        alertDialogBuilder
                .setMessage("Location Added Successfully!")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

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
        customType(Admin_Add_Site_TypeActivity.this, "fadein-to-fadeout");
    }

    public static class Masonary_View_Activity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin__add__site__type);
        }
    }
}
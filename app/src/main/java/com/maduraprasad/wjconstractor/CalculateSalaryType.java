package com.maduraprasad.wjconstractor;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static maes.tech.intentanim.CustomIntent.customType;

public class CalculateSalaryType extends AppCompatActivity {
    TextInputLayout masonry_name, masonry_attendance;
    TextInputEditText edit_masonry_name, edit_attendance;
    String id, name, attendance;
    TextView cname, mname, mnametxt, day, daytxt, perday, perdaytxt, total, totaltxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_salary_type);
        //transparent navigation bar
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        masonry_name = findViewById(R.id.masonry_name2);
        masonry_attendance = findViewById(R.id.masonry_attendance2);
        edit_masonry_name = findViewById(R.id.edit_masonry_name2);
        edit_attendance = findViewById(R.id.edit_masonry_attendance2);
        //getAndSetIntentDataMasonry();

        cname = findViewById(R.id.companyname);
        mname = findViewById(R.id.name);
        mnametxt = findViewById(R.id.nametxt);
        day = findViewById(R.id.days);
        daytxt = findViewById(R.id.daystxt);
        perday = findViewById(R.id.perday);
        perdaytxt = findViewById(R.id.perdaytxt);
        total = findViewById(R.id.total);
        totaltxt = findViewById(R.id.totaltxt);

    }

    //username empty
    private boolean validUsername() {
        String name = masonry_name.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            masonry_name.setError("Username is Empty.");
            return false;
        } else {
            masonry_name.setError(null);
            return true;
        }

    }

    //password empty
    private boolean validDays() {
        String days = masonry_attendance.getEditText().getText().toString().trim();

        if (days.isEmpty()) {
            masonry_attendance.setError("Days are Empty.");
            return false;
        } else {
            masonry_attendance.setError(null);
            return true;
        }

    }

   /* void getAndSetIntentDataMasonry() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name")&& getIntent().hasExtra("attendance")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            attendance = getIntent().getStringExtra("attendance");

            //Setting Intent Data
            edit_masonry_name.setText(name);
            edit_attendance.setText(attendance);

        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }*/


    public void calculate(View view) {

        if (!validUsername() | !validDays()) {
            return;
        }
        String masonname = masonry_name.getEditText().getText().toString();
        String masonday = masonry_attendance.getEditText().getText().toString();

        int salary = 3000;
        int days = Integer.parseInt(masonry_attendance.getEditText().getText().toString());


        cname.setText("W J Construction");
        mname.setText("Name :");
        mnametxt.setText(masonname);
        day.setText("Days :");
        daytxt.setText(masonday);
        perday.setText("Per Day :");
        perdaytxt.setText("3000");
        total.setText("Total :");
        totaltxt.setText("" + days * salary);


    }

    @Override
    public void finish() {
        super.finish();
        customType(CalculateSalaryType.this, "fadein-to-fadeout");
    }
}